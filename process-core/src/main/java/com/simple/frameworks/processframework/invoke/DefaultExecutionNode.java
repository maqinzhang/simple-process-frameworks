/**
 * 
 */
package com.simple.frameworks.processframework.invoke;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.simple.frameworks.processframework.Execute;
import com.simple.frameworks.processframework.Script;
import com.simple.frameworks.processframework.Validate;
import com.simple.frameworks.processframework.bind.annotations.Attribute;
import com.simple.frameworks.processframework.bind.support.AnnotationHandler;
import com.simple.frameworks.processframework.bind.support.AnnotationHandlers;
import com.simple.frameworks.processframework.bind.support.ValueResolver;
import com.simple.frameworks.processframework.bind.support.ValueResolvers;
import com.simple.frameworks.processframework.config.ConfigurationException;
import com.simple.frameworks.processframework.config.DefaultConfigurationManager;
import com.simple.frameworks.processframework.config.NodeConfig;
import com.simple.frameworks.processframework.converter.InjectTypeConverter;
import com.simple.frameworks.processframework.factory.ObjectFactory;
import com.simple.frameworks.processframework.result.Result;
import com.simple.frameworks.processframework.script.DefaultScriptExecutorFactory;
import com.simple.frameworks.processframework.script.ScriptExecutor;
import com.simple.frameworks.processframework.script.ScriptExecutorFactory;
import com.simple.frameworks.processframework.utils.AnnotationUtils;
import com.simple.frameworks.processframework.utils.Assert;
import com.simple.frameworks.processframework.utils.ReflectionUtils;
import com.simple.frameworks.processframework.utils.StringUtils;

/**
 * @author luolishu
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class DefaultExecutionNode implements ExecutionNode {
	static final Object[] EMPTY_OBJECTS = new Object[] {};
	static final ScriptExecutorFactory scriptExecutorFactory=new DefaultScriptExecutorFactory();
	protected NodeConfig nodeConfig;
	protected Object target;
	protected Method[] validationMethods;
	protected Method[] executionMethods;
	protected Method[] scriptMethods;
	protected ObjectFactory objectFactory;
	protected Map<String,Object> scriptStaticExecuteContext=new HashMap<String,Object>();
	private Map<Method,ScriptExecutor> scriptExecutors=new HashMap<Method,ScriptExecutor>();
	protected InjectTypeConverter injectTypeConverter=DefaultConfigurationManager.getInstance().getInjectTypeConverter();
	
	public DefaultExecutionNode(NodeConfig nodeConfig,
			ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
		this.nodeConfig = nodeConfig;
		
	}

	private Object getNodeTarget() {
		return objectFactory.buildNode(nodeConfig);
	}

	private Method[] getValidationMethods() {
		Set<Method> methodsList=new LinkedHashSet<Method>();
		Method[] methods=AnnotationUtils.findAnnotaionMethods(target, Validate.class);
		methodsList.addAll(Arrays.asList(methods));
		Method[] validateNameMethods=ReflectionUtils.getMethodsByName("validate", target.getClass());
		if(validateNameMethods!=null&&validateNameMethods.length>0){
			methodsList.addAll(Arrays.asList(validateNameMethods));
		}
		return methodsList.toArray(new Method[]{});
	}

	private Method[] getExecutionMethods() { 
		Set<Method> methodsList=new LinkedHashSet<Method>();
		Method[] methods=AnnotationUtils.findAnnotaionMethods(target, Execute.class);
		methodsList.addAll(Arrays.asList(methods));
		Method[] validateNameMethods=ReflectionUtils.getMethodsByName("execute", target.getClass());
		if(validateNameMethods!=null&&validateNameMethods.length>0){
			methodsList.addAll(Arrays.asList(validateNameMethods));
		}
		return methodsList.toArray(new Method[]{});
	}
	private Method[] getScriptMethods() { 
		Set<Method> methodsList=new LinkedHashSet<Method>();
		Method[] methods=AnnotationUtils.findAnnotaionMethods(target, Script.class);
		if(methods!=null&&methods.length>0){
			for(Method method:methods){
				if(!method.getReturnType().equals(String.class)){
					throw new ConfigurationException("Script method ="+method+" is invalid!return type should be "+String.class);
				}
				Script scriptAnn=AnnotationUtils.getAnnotation(method, Script.class);
				ScriptExecutor scriptExecutor=scriptExecutorFactory.create(scriptAnn);
				scriptExecutors.put(method, scriptExecutor);
			} 
		}
		methodsList.addAll(Arrays.asList(methods));
		Method[] scriptMethods=ReflectionUtils.getMethodsByName("script", target.getClass());
		if(scriptMethods!=null&&scriptMethods.length>0){
			for(Method method:scriptMethods){
				if(!method.getReturnType().equals(String.class)){
					throw new ConfigurationException("Script method ="+method+" is invalid!return type should be "+String.class);
				}
				Script scriptAnn=AnnotationUtils.getAnnotation(method, Script.class);
				ScriptExecutor scriptExecutor=scriptExecutorFactory.create(scriptAnn);
				scriptExecutors.put(method, scriptExecutor);
			}
			methodsList.addAll(Arrays.asList(scriptMethods));
		}
		
		return methodsList.toArray(new Method[]{});
	}
	private void initScriptStaticContext(){
		if(scriptExecutors.isEmpty()){
			return;
		}
		Field []fields=ReflectionUtils.getAllDeclaredFields(target.getClass(), true);
		for(Field field:fields){
			ReflectionUtils.makeAccessible(field);
			scriptStaticExecuteContext.put(field.getName(), ReflectionUtils.getField(field, target));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simple.frameworks.processframework.Node#validate(com.simple.frameworks.
	 * processframework.ExecuteContext)
	 */
	@Override
	public boolean validate(DelegatingExecuteContext context) throws Exception{
		for (Method method : validationMethods) {
			boolean result = this.doValidate(method, context);
			if (!result) {
				return result;
			}
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.simple.frameworks.processframework.Node#execute(com.simple.frameworks.
	 * processframework.ExecuteContext)
	 */
	@Override
	public Object execute(DelegatingExecuteContext context) throws Exception{
		Result scriptResult=this.runScriptMethods(context);//先执行脚本
		if(scriptResult!=null){
			return scriptResult;
		}
		for (Method method : executionMethods) {
			context.setLastExecuteNode(this);
			Object result = this.doExecute(method, context);
			
			if (result != null && result instanceof Result) {
				return result;
			}
		}
		return null;
	}
	private Result runScriptMethods(DelegatingExecuteContext context){
		if(scriptMethods!=null){
			for(Method method:scriptMethods){
				ScriptExecutor scriptExecutor=scriptExecutors.get(method);
				if(scriptExecutor!=null){
					Object args[] = this.buildExecuteMethodArguments(method, context);
					Object value = ReflectionUtils.invokeMethod(method, target, args);
					if(value==null){
						continue;
					}
					Object result=scriptExecutor.execute(value.toString(), context,this.scriptStaticExecuteContext);
					if(result!=null){
						if(result instanceof Result){
							return (Result) result;
						}else {
							Attribute ann = method.getAnnotation(Attribute.class);
							if (ann != null) {
								AnnotationHandler handler = AnnotationHandlers
										.getHandler(Attribute.class);
								if (handler != null) {
									handler.handleMethod(ann, method, value, context);
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	private boolean doValidate(Method method, DelegatingExecuteContext context) throws Exception{
		Object args[] = this.buildExecuteMethodArguments(method, context);
		Object value = ReflectionUtils.invokeMethod(method, target, args);
		Attribute ann = method.getAnnotation(Attribute.class);
		if (ann != null) {
			AnnotationHandler handler = AnnotationHandlers
					.getHandler(Attribute.class);
			if (handler != null) {
				handler.handleMethod(ann, method, value, context);
			}
		}
		if(value!=null&&value instanceof Result){
			context.result=(Result) value;
		}
		if(value!=null&&value instanceof Boolean){
			return (Boolean)value;
		}
		return true;
	}

	private Object doExecute(Method method, DelegatingExecuteContext context) {
		Object args[] = this.buildExecuteMethodArguments(method, context);
		Object value = ReflectionUtils.invokeMethod(method, target, args);
		Attribute ann = method.getAnnotation(Attribute.class);
		if (ann != null) {
			AnnotationHandler handler = AnnotationHandlers
					.getHandler(Attribute.class);
			if (handler != null) {
				handler.handleMethod(ann, method, value, context);
			}
		}
		return value;
	}

	private Object resolveCommon(Class<?> argumentType,
			DelegatingExecuteContext context) {
		if (argumentType.isInstance(context.request)) {
			return context.request;
		}
		if (argumentType.isInstance(context.session)) {
			return context.session;
		}
		if (argumentType.isInstance(context.result)) {
			return context.result;
		}
		if (argumentType.isInstance(context)) {
			return context;
		}
		return null;

	}

	private Object[] buildExecuteMethodArguments(Method method,
			DelegatingExecuteContext context) {
		Annotation[][] parameterAnns = method.getParameterAnnotations();
		Class<?>[] parameterTypes = method.getParameterTypes(); 
		int argumentSize = parameterTypes.length;
		if (argumentSize <= 0) {
			return EMPTY_OBJECTS;
		}
		Object[] args = new Object[argumentSize];
		for (int i = 0; i < argumentSize; i++) {
			Annotation[] anns = parameterAnns[i];
			Class<?> parameterType=parameterTypes[i];
			Object value = null;
			if (anns != null) {
				for (Annotation ann : anns) {
					ValueResolver valueResolver = ValueResolvers
							.getValueResolver(ann.annotationType());
					if (valueResolver == null) {
						continue;
					}
					value = valueResolver.resolve(ann,parameterType,context);
					if (value != null) {
						break;
					}
				}
			}
			if (value == null) {
				value = this.resolveCommon(parameterTypes[i], context);
			}else{
				value=injectTypeConverter.convertIfNecessary(value, method, i, parameterType);
			}
			value=this.handleNullValue(anns, value, parameterType);
			args[i]=value;
		}
		return args;
	}
	private Object handleNullValue(Annotation[] anns, Object value, Class<?> paramType) {
		if (value == null) {
			if (Boolean.TYPE.equals(paramType)) {
				return Boolean.FALSE;
			}
			else if (paramType.isPrimitive()) {
				List<String> arrays=new ArrayList<String>();
				for(Annotation ann:anns){
					Object v=AnnotationUtils.getValue(ann);
					arrays.add(v+"");
				}
				throw new IllegalStateException("Optional " + paramType + " parameter '" + StringUtils.join(arrays.toArray()) +
						"' is present but cannot be translated into a null value due to being declared as a " +
						"primitive type. Consider declaring it as object wrapper for the corresponding primitive type.");
			}
		}
		return value;
	}

	public NodeConfig getNodeConfig() {
		return nodeConfig;
	}

	@Override
	public String toString() {
		return "DefaultExecutionNode [nodeConfig=" + nodeConfig
				+ ", validationMethods=" + Arrays.toString(validationMethods)
				+ ", executionMethods=" + Arrays.toString(executionMethods)
				+ "]";
	}

	@Override
	public void init() {
		this.target = getNodeTarget();		
		Assert.notNull(target,
				"Node Instance is null,No Node Implement found!Please check your Process configuration!");
		this.validationMethods = getValidationMethods();
		this.executionMethods = getExecutionMethods();
		this.scriptMethods=getScriptMethods();
		this.initScriptStaticContext();
	}
	

}

package com.jd.frameworks.processframework.invoke;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.frameworks.processframework.bind.annotations.RequestVar;
import com.jd.frameworks.processframework.config.ConfigurationException;
import com.jd.frameworks.processframework.exception.ExceptionHandler;
import com.jd.frameworks.processframework.request.Request;
import com.jd.frameworks.processframework.result.DefaultResult;
import com.jd.frameworks.processframework.result.Result;
import com.jd.frameworks.processframework.utils.ReflectionUtils;

public class JavassistClassGenerator implements ClassGenerator {
	static Logger logger = LoggerFactory
			.getLogger(JavassistClassGenerator.class);
	static final ClassPool javassitClassPool = ClassPool.getDefault();
	static {
		javassitClassPool.importPackage("com.jd.frameworks.processframework");
		javassitClassPool
				.importPackage("com.jd.frameworks.processframework.result");
		javassitClassPool
				.importPackage("com.jd.frameworks.processframework.request");

		javassitClassPool
				.importPackage("com.jd.frameworks.processframework.invoke");
		javassitClassPool.importPackage("java.util");
		javassitClassPool.insertClassPath(new ClassClassPath(
				ProcessInvoker.class));
	}

	@Override
	public Class<?> generateClass(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler) {

		String className = "com.jd.frameworks.processframework.invoke."
				+ processHolder.getId() + "Proxy";
		CtClass invokerClass = javassitClassPool.makeClass(className);

		try {
			CtClass interfaceClass = getCtClass(ProcessInvoker.class);
			CtClass superClass = getCtClass(DefaultProcessInvoker.class);
			invokerClass.addInterface(interfaceClass);

			invokerClass.setSuperclass(superClass);
			CtConstructor constructor1 = new CtConstructor(null, invokerClass);
			constructor1.setBody("{}");
			CtConstructor constructor2 = this.generateCtConstructor(
					processHolder, exceptionHandler, invokerClass);

			invokerClass.addConstructor(constructor1);
			invokerClass.addConstructor(constructor2);
			CtMethod invokerMethod = this.generateInvokeCtMethod(processHolder,
					invokerClass);
			invokerClass.addMethod(invokerMethod);
			if (processHolder.getProxyInterfaces() != null) {
				for (Class<?> proxyInteface : processHolder
						.getProxyInterfaces()) {
					CtClass proxyInterfaceClass = getCtClass(proxyInteface);
					invokerClass.addInterface(proxyInterfaceClass);
					Method methods[] = ReflectionUtils
							.getAllDeclaredMethods(proxyInteface);
					if (methods != null) {
						for (Method method : methods) {
							CtMethod ctMethod = this.generateInterfaceCtMethod(
									method, invokerClass);
							invokerClass.addMethod(ctMethod);
						}
					}

				}
			}
			return invokerClass.toClass();
		} catch (Exception e) {
			logger.error(
					"generate  class error!processHolder=" + processHolder, e);
			throw new ConfigurationException(e);
		}
	}

	private CtClass getCtClass(Class<?> type) throws NotFoundException {
		CtClass ctClass = javassitClassPool.get(type.getName());
		if (ctClass == null) {
			javassitClassPool.insertClassPath(new ClassClassPath(type));
			ctClass = javassitClassPool.get(type.getName());
		}
		return ctClass;
	}

	private CtConstructor generateCtConstructor(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler, CtClass invokerClass)
			throws Exception {

		CtClass processHolderClass = getCtClass(ProcessHolder.class);
		CtClass exceptionHandlerClass = getCtClass(ExceptionHandler.class);
		CtConstructor constructor = new CtConstructor(new CtClass[] {
				processHolderClass, exceptionHandlerClass }, invokerClass);
		constructor
				.setBody("{this.processHolder=$1;this.exceptionHandler=$2;}");
		return constructor;
	}

	private CtMethod generateInvokeCtMethod(ProcessHolder processHolder,
			CtClass invokerClass) throws Exception {
		CtMethod invokeMethod = new CtMethod(
				javassitClassPool.get(Result.class.getName()),
				"doInvoke",
				new CtClass[] { javassitClassPool.get(Request.class.getName()) },
				invokerClass);

		invokeMethod.setBody(this.buildInvokeMethodBody(processHolder));
		return invokeMethod;
	}

	private CtMethod generateInterfaceCtMethod(Method method,
			CtClass invokerClass) throws Exception {
		CtClass[] argsCtClassArray = null;
		CtClass returnCtClass = getCtClass(Result.class);
		Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes != null && parameterTypes.length > 0) {
			argsCtClassArray = new CtClass[parameterTypes.length];
			for (int i = 0; i < parameterTypes.length; i++) {
				CtClass argCtClass = getCtClass(parameterTypes[i]);
				if (argCtClass == null) {
					javassitClassPool.insertClassPath(new ClassClassPath(
							parameterTypes[i]));
				}
				argCtClass = getCtClass(parameterTypes[i]);
				argsCtClassArray[i] = argCtClass;
			}
		}
		if (!Result.class.isAssignableFrom(method.getReturnType())) {
			returnCtClass = getCtClass(method.getReturnType());
		}

		CtMethod invokeMethod = new CtMethod(returnCtClass, method.getName(),
				argsCtClassArray, invokerClass);
		MethodDescription methodDesc = new MethodDescription(method);
		invokeMethod.setBody(this.buildInterfaceMethodBody(methodDesc));
		return invokeMethod;
	}

	private String buildInterfaceMethodBody(MethodDescription methodDesc) {
		StringBuilder body = new StringBuilder();
		body.append("{");
		body.append("Request request = new ApplicationRequest();");
		if (methodDesc.isHasParameter()) {
			for (int i = 0; i < methodDesc.getMethod().getParameterTypes().length; i++) {
				Class<?> type = methodDesc.getMethod().getParameterTypes()[i];
				if (Request.class.isAssignableFrom(type)) {
					body.append(" request=$" + (i + 1) + ";");
				}

			}
			for (int i = 0; i < methodDesc.argumentsNames.length; i++) {
				String argName = methodDesc.argumentsNames[i];
				body.append(" request.addParameter(\"" + argName + "\",$"
						+ (i + 1) + ");");
			}
		}
		body.append("Result result=this.invoke(request);");
		if (!methodDesc.getReturnType().equals(Void.TYPE)) {
			if (Result.class.isAssignableFrom(methodDesc.getReturnType())) {
				body.append("return result;");
			} else {
				body.append("return ("
						+ methodDesc.getReturnType().getName()
						+ ")injectTypeConverter.convertIfNecessary(result.getModel(),"
						+ methodDesc.getReturnType().getName() + ".class);");
			}
		}

		body.append("}");
		return body.toString();

	}

	private String buildInvokeMethodBody(ProcessHolder processHolder) {
		StringBuilder body = new StringBuilder();
		body.append("{Object value = null;");
		body.append("DelegatingExecuteContext delegatingContext = null;");
		body.append("try {");
		body.append("delegatingContext = createContext($1);");
		body.append("SubProcess mainProcess = processHolder.getMainProcess();");
		body.append("value = this.invokeMainProcess(mainProcess, delegatingContext);");
		body.append("if (value != null && value instanceof Result) {");
		body.append("return (Result)value;}");
		body.append("}");
		if (processHolder.getCatchProcess() != null
				&& processHolder.getCatchProcess().size() > 0) {
			this.appendExceptionBlock(processHolder.getCatchProcess(), body);
		}
		body.append("catch (Exception e) {");
		body.append("value = exceptionHandler.handle(e);");
		body.append("}");
		if (processHolder.getFinallyProcess() != null) {
			this.appendFinallyBlock(processHolder.getFinallyProcess(), body);
		}
		body.append("if (value != null && value instanceof Result) {");
		body.append("return (Result)value;}");
		body.append("return delegatingContext.result;}");
		return body.toString();
	}

	private void appendExceptionBlock(List<SubProcess> exceptionList,
			StringBuilder body) {
		for (SubProcess subProcess : exceptionList) {
			body.append("catch (" + subProcess.getException() + " e) {");
			body.append("SubProcess exceptionProcess = processHolder.getExceptionProcess(e.getClass());");
			body.append("value = this.invokeCatchProcess(exceptionProcess, delegatingContext);");
			body.append("if (value != null && value instanceof Result) {");
			body.append("return (Result)value;}");
			body.append("}");
		}
	}

	private void appendFinallyBlock(SubProcess subProcess, StringBuilder body) {
		body.append("finally {");
		body.append("SubProcess finalProcess = processHolder.getFinallyProcess();");
		body.append("value = this.invokeFinallyProcess(finalProcess, delegatingContext);");
		body.append("}");
	}

	static class MethodDescription {
		Method method;
		String argumentsNames[];
		Class<?> parameterTypes[];
		Class<?> returnType;
		final boolean hasParameter;

		MethodDescription(Method method) {
			this.method = method;
			this.returnType = method.getReturnType();
			Annotation[][] annotatinons = method.getParameterAnnotations();
			this.parameterTypes = method.getParameterTypes();
			if (annotatinons != null && annotatinons.length > 0) {
				hasParameter = true;
				argumentsNames = new String[annotatinons.length];
				boolean found = false,hasRequest=false;
				for (int i = 0; i < annotatinons.length; i++) {
					Annotation[] itemAnns = annotatinons[i];
					Class<?> type=parameterTypes[i];
					for (Annotation ann : itemAnns) {
						if (ann instanceof RequestVar) {
							argumentsNames[i] = ((RequestVar) ann).value();
							found = true;
						}
					}	
					if(Request.class.isAssignableFrom(type)){
						hasRequest=true;
					}
				}
				if (!found&&!hasRequest) {
					throw new ConfigurationException(
							"method ="
									+ method.getName()
									+ " config error!Parameter name must set at method(Use @RequestVar):"
									+ method);
				}
			} else {
				hasParameter = false;
			}

		}

		public boolean isHasParameter() {
			return hasParameter;
		}

		public Method getMethod() {
			return method;
		}

		public void setMethod(Method method) {
			this.method = method;
		}

		public String[] getArgumentsNames() {
			return argumentsNames;
		}

		public void setArgumentsNames(String[] argumentsNames) {
			this.argumentsNames = argumentsNames;
		}

		public Class<?> getReturnType() {
			return returnType;
		}

		public void setReturnType(Class<?> returnType) {
			this.returnType = returnType;
		}

	}

	public static void main(String args[]) throws Exception {
		System.out.println(Result.class.isAssignableFrom(DefaultResult.class));
	}

}

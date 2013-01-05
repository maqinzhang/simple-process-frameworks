package com.opensource.frameworks.processframework.factory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javassist.CannotCompileException;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import javassist.NotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensource.frameworks.processframework.config.CatchTagConfig;
import com.opensource.frameworks.processframework.config.ConfigurationException;
import com.opensource.frameworks.processframework.config.ExceptionConfig;
import com.opensource.frameworks.processframework.invocation.ExceptionInvocation;
import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.result.DefaultResult;
import com.opensource.frameworks.processframework.result.Result;
import com.opensource.frameworks.processframework.utils.CollectionUtils;

public class JavassistInvocationClassGenerator implements
		InvocationClassGenerator {
	static Logger logger = LoggerFactory
			.getLogger(JavassistInvocationClassGenerator.class);
	static final ClassPool javassitClassPool = ClassPool.getDefault();
	static {
		javassitClassPool.importPackage("com.jd.frameworks.processframework");
		javassitClassPool
				.importPackage("com.jd.frameworks.processframework.result");
		javassitClassPool
				.importPackage("com.jd.frameworks.processframework.request");
		javassitClassPool.importPackage("java.util");
	}
	static AtomicInteger version = new AtomicInteger(0);

	@Override
	public Class<?> generateClass(ExceptionConfig config) {
		String className = "com.jd.frameworks.processframework.invocation.ExceptionInvocationProxy"
				+ version.incrementAndGet();
		CtClass invokerClass = javassitClassPool.makeClass(className);
		try {
			CtClass superClass = getCtClass(ExceptionInvocation.class);
			invokerClass.setSuperclass(superClass);
			CtConstructor constructor = this
					.generateCtConstructor(invokerClass);
			invokerClass.addConstructor(constructor);
			CtMethod invokerMethod = this.generateInvokeCtMethod(config,
					invokerClass);
			invokerClass.addMethod(invokerMethod);
		} catch (Exception e) {
			logger.error("generate  class error!config=" + config, e);
			throw new ConfigurationException(e);
		}
		try {
			return invokerClass.toClass();
		} catch (CannotCompileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private CtClass getCtClass(Class<?> type) throws NotFoundException {
		CtClass ctClass = javassitClassPool.get(type.getName());
		if (ctClass == null) {
			javassitClassPool.insertClassPath(new ClassClassPath(type));
			ctClass = javassitClassPool.get(type.getName());
		}
		return ctClass;
	}

	private CtConstructor generateCtConstructor(CtClass invokerClass)
			throws Exception {

		CtClass arg1 = getCtClass(List.class);
		CtClass arg2 = getCtClass(Map.class);
		CtClass arg3 = getCtClass(List.class);
		CtConstructor constructor = new CtConstructor(new CtClass[] { arg1,
				arg2, arg3 }, invokerClass);
		constructor.setBody("{super($1,$2,$3);}");
		return constructor;
	}

	private CtMethod generateInvokeCtMethod(ExceptionConfig config,
			CtClass invokerClass) throws Exception {
		CtMethod invokeMethod = new CtMethod(getCtClass(Object.class),
				"invoke",
				new CtClass[] { getCtClass(WrapperExecuteContext.class) },
				invokerClass);
		String body = this.buildInvokeMethodBody(config);
		invokeMethod.setBody(body);
		return invokeMethod;
	}

	private String buildInvokeMethodBody(ExceptionConfig config) {
		StringBuilder body = new StringBuilder();
		body.append("{Object value = null;");
		body.append("try {");
		body.append("value = this.invokeTry($1);");
		body.append("if (value != null && value instanceof Result) {");
		body.append("return (Result)value;}");
		body.append("}");
		if (!CollectionUtils.isEmpty(config.getCatchConfigs())) {
			this.appendExceptionBlock(config, body);
		}
		if (config.getFinallyConfig() != null) {
			this.appendFinallyBlock(config, body);
		}
		body.append("return value;}");
		return body.toString();
	}

	private void appendExceptionBlock(ExceptionConfig config, StringBuilder body) {
		for (CatchTagConfig item : config.getCatchConfigs()) {
			body.append("catch (" + item.getException() + " e) {");
			body.append("value = invokeCatch(e,$1);");
			body.append("if (value != null && value instanceof Result) {");
			body.append("return (Result)value;}");
			body.append("}");
		}
	}

	private void appendFinallyBlock(ExceptionConfig config, StringBuilder body) {
		body.append("finally {");
		body.append("value =  invokeFinally($1);");
		body.append("}");
	}

	public static void main(String args[]) throws Exception {
		System.out.println(Result.class.isAssignableFrom(DefaultResult.class));
	}

}

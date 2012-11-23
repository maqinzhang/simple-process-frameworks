package com.simple.frameworks.processframework.invoke;
 

import java.util.HashMap;
import java.util.Map;

import com.simple.frameworks.processframework.exception.ExceptionHandler;

public class DefaultProcessInvockerFactory implements ProcessInvockerFactory{
	private ClassGenerator classGenerator=new JavassistClassGenerator(); 
	private final Map<String,ProcessInvoker> invokers=new HashMap<String,ProcessInvoker>();
	@Override
	public ProcessInvoker create(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler) {
		Class<?> claz=classGenerator.generateClass(processHolder, exceptionHandler);
		String key=processHolder.getId();
		ProcessInvoker invocker=invokers.get(key);
		if(invocker!=null)return invocker;
		try {
			invocker=(ProcessInvoker) claz.getConstructor(ProcessHolder.class,ExceptionHandler.class).newInstance(processHolder, exceptionHandler);
			invokers.put(key, invocker);
			return invocker;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void setClassGenerator(ClassGenerator classGenerator) {
		this.classGenerator = classGenerator;
	}

}

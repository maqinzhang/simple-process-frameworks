 
package com.simple.frameworks.processframework.invoke;

import com.simple.frameworks.processframework.exception.ExceptionHandler;

/**
 * @author luolishu
 *
 */
public interface ProcessInvockerFactory {
	
	
	public ProcessInvoker create(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler);

}

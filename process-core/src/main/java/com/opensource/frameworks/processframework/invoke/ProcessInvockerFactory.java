 
package com.opensource.frameworks.processframework.invoke;

import com.opensource.frameworks.processframework.exception.ExceptionHandler;

/**
 * @author luolishu
 *
 */
public interface ProcessInvockerFactory {
	
	
	public ProcessInvoker create(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler);

}

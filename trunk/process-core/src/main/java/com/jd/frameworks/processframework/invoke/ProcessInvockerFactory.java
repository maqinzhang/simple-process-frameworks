 
package com.jd.frameworks.processframework.invoke;

import com.jd.frameworks.processframework.exception.ExceptionHandler;

/**
 * @author luolishu
 *
 */
public interface ProcessInvockerFactory {
	
	
	public ProcessInvoker create(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler);

}

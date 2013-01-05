/**
 * 
 */
package com.opensource.frameworks.processframework.invoke;

import com.opensource.frameworks.processframework.exception.ExceptionHandler;

/**
 * @author luolishu
 *
 */
public interface ClassGenerator {
	Class<?> generateClass(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler);
}

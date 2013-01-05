/**
 * 
 */
package com.simple.frameworks.processframework.invoke;

import com.simple.frameworks.processframework.exception.ExceptionHandler;

/**
 * @author luolishu
 *
 */
public interface ClassGenerator {
	Class<?> generateClass(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler);
}

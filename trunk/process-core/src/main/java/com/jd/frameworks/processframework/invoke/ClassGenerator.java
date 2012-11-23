/**
 * 
 */
package com.jd.frameworks.processframework.invoke;

import com.jd.frameworks.processframework.exception.ExceptionHandler;

/**
 * @author luolishu
 *
 */
public interface ClassGenerator {
	Class<?> generateClass(ProcessHolder processHolder,
			ExceptionHandler<?> exceptionHandler);
}

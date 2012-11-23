/**
 * 
 */
package com.jd.frameworks.processframework.exception;

/**
 * @author luolishu
 *
 */
public interface ExceptionHandler<E extends Throwable> {
	
	public Object handle(E e);

}

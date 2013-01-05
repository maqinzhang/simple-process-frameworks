/**
 * 
 */
package com.opensource.frameworks.processframework.exception;

/**
 * @author luolishu
 *
 */
public class ProcessInvokeException extends RuntimeException {

	public ProcessInvokeException() {
		super(); 
	}

	public ProcessInvokeException(String message, Throwable cause) {
		super(message, cause); 
	}

	public ProcessInvokeException(String message) {
		super(message); 
	}

	public ProcessInvokeException(Throwable cause) {
		super(cause); 
	}

}

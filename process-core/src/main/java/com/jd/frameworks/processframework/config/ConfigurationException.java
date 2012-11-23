/**
 * 
 */
package com.jd.frameworks.processframework.config;

/**
 * @author luolishu
 *
 */
public class ConfigurationException extends RuntimeException { 
	private static final long serialVersionUID = 2239298505621307275L;

	public ConfigurationException() {
		super(); 
	}

	public ConfigurationException(String message, Throwable cause) {
		super(message, cause); 
	}

	public ConfigurationException(String message) {
		super(message); 
	}

	public ConfigurationException(Throwable cause) {
		super(cause); 
	}

}

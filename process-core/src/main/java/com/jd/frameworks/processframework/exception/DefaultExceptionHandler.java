/**
 * 
 */
package com.jd.frameworks.processframework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 

/**
 * @author luolishu
 *
 */
public class DefaultExceptionHandler implements ExceptionHandler<Exception>{
	static Logger logger=LoggerFactory.getLogger(ExceptionHandler.class);
	@Override
	public Object handle(Exception e) { 
		logger.error("execute exception!", e);
		return null;
	}

}

/**
 * 
 */
package com.opensource.frameworks.processframework.invoke;
 

import com.opensource.frameworks.processframework.config.ProcessConfig;

/**
 * @author luolishu
 *
 */
public interface ProcessHolder {
	public String getExceptionHandlerId();
	public ProcessConfig getProcessConfig();
	public String getId(); 
	
	public Class<?> getResultClass();
	
	public Class<?>[] getProxyInterfaces();

}

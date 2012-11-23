/**
 * 
 */
package com.simple.frameworks.processframework.invoke;

import java.util.List;

import com.simple.frameworks.processframework.config.ProcessConfig;

/**
 * @author luolishu
 *
 */
public interface ProcessHolder {
	public String getExceptionHandlerId();
	public ProcessConfig getProcessConfig();
	public String getId();
	
	public SubProcess getMainProcess();
	
	public SubProcess getExceptionProcess(Class<?> claz);
	public List<SubProcess> getCatchProcess();
	
	public SubProcess getFinallyProcess();
	
	public Class<?> getResultClass();
	
	public Class<?>[] getProxyInterfaces();

}

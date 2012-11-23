/**
 * 
 */
package com.simple.frameworks.processframework.invoke;

import java.util.List;

import com.simple.frameworks.processframework.config.SubProcessConfig;

/**
 * @author luolishu
 *
 */
public interface SubProcess {
	public String getException();
	public Class<?> getExceptionClass();
	public List<ExecutionNode> getNodes();
	public SubProcessConfig getSubProcessConfig();

}

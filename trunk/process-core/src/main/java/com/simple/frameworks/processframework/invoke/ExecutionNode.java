/**
 * 
 */
package com.simple.frameworks.processframework.invoke;

import com.simple.frameworks.processframework.config.NodeConfig;


/**
 * @author luolishu
 * 
 */
public interface ExecutionNode {
	public void init();
	public NodeConfig getNodeConfig();
	public boolean validate(DelegatingExecuteContext context) throws Exception;	
	public Object execute(DelegatingExecuteContext context) throws Exception;
}

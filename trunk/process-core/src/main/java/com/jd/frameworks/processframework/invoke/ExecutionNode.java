/**
 * 
 */
package com.jd.frameworks.processframework.invoke;

import com.jd.frameworks.processframework.config.NodeConfig;


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

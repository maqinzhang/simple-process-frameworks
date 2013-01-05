/**
 * 
 */
package com.opensource.frameworks.processframework.invocation;

import com.opensource.frameworks.processframework.config.NodeConfig;
import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;


/**
 * @author luolishu
 * 
 */
public interface ExecutionNode {
	public void init();
	public NodeConfig getNodeConfig();
	public boolean validate(WrapperExecuteContext context) throws Throwable;	
	public Object execute(WrapperExecuteContext context) throws Throwable;
}

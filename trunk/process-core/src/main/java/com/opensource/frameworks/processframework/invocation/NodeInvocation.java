/**
 * 
 */
package com.opensource.frameworks.processframework.invocation;

import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.result.Result;

/**
 * @author luolishu
 *
 */
public class NodeInvocation implements Invocation{
	ExecutionNode node;
    public NodeInvocation(ExecutionNode node){
    	this.node=node;
    }
	@Override
	public Object invoke(WrapperExecuteContext context) throws Throwable {
		Object result = null;
		if (node.validate(context)) {
			result = node.execute(context);
			if (result != null && result instanceof Result) {
				return result;
			}
		}
		return result;
	}
	
	

}

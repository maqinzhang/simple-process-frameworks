/**
 * 
 */
package com.opensource.frameworks.processframework.invocation;

import com.opensource.frameworks.processframework.factory.ObjectFactory;
import com.opensource.frameworks.processframework.invoke.ProcessInvoker;
import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.utils.Assert;

/**
 * @author luolishu
 * 
 */
public class ForwardInvocation implements Invocation { 
	ObjectFactory objectFactory;
	ProcessInvoker forwardProcessInvoker;
	String processId;

	@Override
	public Object invoke(WrapperExecuteContext context) throws Throwable {
		if(forwardProcessInvoker==null){
			forwardProcessInvoker=(ProcessInvoker) objectFactory.getObject(processId);
		}
		Assert.notNull(forwardProcessInvoker, "forward process id="+processId+" not found!");
		return forwardProcessInvoker.forward(context);
	}
 
	public ObjectFactory getObjectFactory() {
		return objectFactory;
	}

	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	public ProcessInvoker getForwardProcessInvoker() {
		return forwardProcessInvoker;
	}

	public void setForwardProcessInvoker(ProcessInvoker forwardProcessInvoker) {
		this.forwardProcessInvoker = forwardProcessInvoker;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

}

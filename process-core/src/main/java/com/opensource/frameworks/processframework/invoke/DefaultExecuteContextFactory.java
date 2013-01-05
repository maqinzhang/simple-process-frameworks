/**
 * 
 */
package com.opensource.frameworks.processframework.invoke;

import com.opensource.frameworks.processframework.request.Request;
import com.opensource.frameworks.processframework.request.Session;

/**
 * @author luolishu
 *
 */
public class DefaultExecuteContextFactory implements ExecuteContextFactory {

	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.invoke.ExecuteContextFactory#create()
	 */
	@Override
	public WrapperExecuteContext create(Request request,Session session) { 
		WrapperExecuteContext context= new WrapperExecuteContext();
		context.request=request;
		context.session=session;
		return context;
	}

}

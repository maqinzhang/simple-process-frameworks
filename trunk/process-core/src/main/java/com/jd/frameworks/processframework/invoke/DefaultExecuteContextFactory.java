/**
 * 
 */
package com.jd.frameworks.processframework.invoke;

import com.jd.frameworks.processframework.request.Request; 
import com.jd.frameworks.processframework.request.Session;

/**
 * @author luolishu
 *
 */
public class DefaultExecuteContextFactory implements ExecuteContextFactory {

	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.invoke.ExecuteContextFactory#create()
	 */
	@Override
	public DelegatingExecuteContext create(Request request,Session session) { 
		DelegatingExecuteContext context= new DelegatingExecuteContext();
		context.request=request;
		context.session=session;
		return context;
	}

}

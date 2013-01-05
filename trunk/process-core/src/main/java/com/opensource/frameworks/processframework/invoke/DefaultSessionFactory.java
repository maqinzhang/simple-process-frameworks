/**
 * 
 */
package com.opensource.frameworks.processframework.invoke;

import com.opensource.frameworks.processframework.request.DefaultSessionImpl;
import com.opensource.frameworks.processframework.request.Session;

/**
 * @author luolishu
 *
 */
public class DefaultSessionFactory implements SessionFactory {

	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.invoke.SessionFactory#createSession()
	 */
	@Override
	public Session createSession() { 
		return new DefaultSessionImpl();
	}

}

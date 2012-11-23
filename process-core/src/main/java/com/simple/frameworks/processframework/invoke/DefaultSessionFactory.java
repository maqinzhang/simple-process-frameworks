/**
 * 
 */
package com.simple.frameworks.processframework.invoke;

import com.simple.frameworks.processframework.request.DefaultSessionImpl;
import com.simple.frameworks.processframework.request.Session;

/**
 * @author luolishu
 *
 */
public class DefaultSessionFactory implements SessionFactory {

	/* (non-Javadoc)
	 * @see com.simple.frameworks.processframework.invoke.SessionFactory#createSession()
	 */
	@Override
	public Session createSession() { 
		return new DefaultSessionImpl();
	}

}

/**
 * 
 */
package com.jd.frameworks.processframework.invoke;

import com.jd.frameworks.processframework.request.DefaultSessionImpl;
import com.jd.frameworks.processframework.request.Session;

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

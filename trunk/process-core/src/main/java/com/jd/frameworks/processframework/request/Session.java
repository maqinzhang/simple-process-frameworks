/**
 * 
 */
package com.jd.frameworks.processframework.request;

/**
 * 
 * @author luolishu
 *
 */
public interface Session {
	
	public void setAttribute(String key,Object value);
	
	public Object getAttribute(String key);
	
	public void removeAttribute(String key);

}

/**
 * 
 */
package com.opensource.frameworks.processframework.request;

import java.util.Map;

/**
 * 
 * @author luolishu
 * 
 */
public interface Session {

	public void setAttribute(String key, Object value);

	public Object getAttribute(String key);

	public void removeAttribute(String key);

	public Map getAttributes();

}

/**
 * 
 */
package com.simple.frameworks.processframework.request;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author luolishu
 *
 */
public class DefaultSessionImpl implements Session{
	Map<String,Object> parameters=new HashMap<String,Object>();
	@Override
	public void setAttribute(String key, Object value) {
		parameters.put(key, value);
		
	}

	@Override
	public Object getAttribute(String key) {
		return parameters.get(key);
	}

	@Override
	public void removeAttribute(String key) {
		parameters.remove(key);
	}
	
 

}

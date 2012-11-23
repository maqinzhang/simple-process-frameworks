/**
 * 
 */
package com.jd.frameworks.processframework.request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luolishu
 *
 */
public final class ApplicationRequest implements Request {
   Map<String,Object> parameters=new HashMap<String,Object>();
	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.request.Request#getParameter(java.lang.String)
	 */
	@Override
	public Object getParameter(String key) { 
		return parameters.get(key);
	}

	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.request.Request#getParameterMap()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map getParameterMap() { 
		return parameters;
	}

	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.request.Request#addParameter(java.lang.String, java.lang.Object)
	 */
	@Override
	public void addParameter(String key, Object value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameter(String key, int value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameter(String key, long value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameter(String key, byte value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameter(String key, boolean value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameter(String key, double value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameter(String key, float value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameter(String key, short value) {
		parameters.put(key, value);
	}

	@Override
	public void addParameter(String key, char value) {
		parameters.put(key, value);
	}

}

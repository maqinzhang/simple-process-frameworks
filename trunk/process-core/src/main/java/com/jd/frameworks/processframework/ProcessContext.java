/**
 * 
 */
package com.jd.frameworks.processframework;

import java.util.HashMap;
import java.util.Map;

/**
 * @author luolishu
 * 
 */
public class ProcessContext {
	static final ThreadLocal<ProcessContext> threadLocal = new ThreadLocal<ProcessContext>();
	Map<String, Object> contents = new HashMap<String, Object>();

	public ProcessContext() {
	}

	public static ProcessContext getContext() {
		return threadLocal.get();
	}

	public static void setContext(ProcessContext context) {
		threadLocal.set(context);
	}

	public void put(String key, Object value) {
		this.contents.put(key, value);
	}

	public Object get(String key) {
		return this.contents.get(key);
	}
}

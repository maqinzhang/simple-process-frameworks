/**
 * 
 */
package com.jd.frameworks.nosql;

import java.io.Serializable;

/**
 * @author luolishu
 * 
 */
public interface CachedObject {
	public Serializable getKey();
	public Serializable getValue();
	public Long getExpired();
}

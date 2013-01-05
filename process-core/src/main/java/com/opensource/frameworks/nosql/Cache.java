/**
 * 
 */
package com.opensource.frameworks.nosql;

import java.io.Serializable;

/**
 * @author luolishu
 * 
 */
public interface Cache {
	public void set(String key, Serializable value);

	public void set(String key, Serializable value, long timeout);

	public void set(CachedObject cachedObject);

	public void set(CachedObject cachedObject, long timeout);

	public Serializable get(String key);

	public boolean remove(String key);

	public Serializable get(CachedObject cachedObject);

	public boolean remove(CachedObject cachedObject);
}

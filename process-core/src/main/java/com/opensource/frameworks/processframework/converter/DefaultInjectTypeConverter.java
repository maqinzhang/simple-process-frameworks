/**
 * 
 */
package com.opensource.frameworks.processframework.converter;

import java.lang.reflect.Method;

/**
 * @author luolishu
 *
 */

@SuppressWarnings("unchecked")
public class DefaultInjectTypeConverter implements InjectTypeConverter {
 
	@Override
	public <T> T convertIfNecessary(Object value, Class<T> requiredType) { 
		return (T) value;
	}

	@Override
	public <T> T convertIfNecessary(Object value, Method method, int index,
			Class<T> requiredType) { 
		return (T) value;
	}

}

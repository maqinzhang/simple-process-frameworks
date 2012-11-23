/**
 * 
 */
package com.jd.frameworks.processframework.spring.converter;

import java.lang.reflect.Method;

import org.springframework.beans.SimpleTypeConverter;
import org.springframework.beans.TypeConverter;
import org.springframework.core.MethodParameter;

import com.jd.frameworks.processframework.converter.InjectTypeConverter;

/**
 * @author luolishu
 * 
 */
public class SpringTypeConverter implements InjectTypeConverter {
	TypeConverter typeConverter = new SimpleTypeConverter();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jd.frameworks.processframework.invoke.TypeConverter#convertIfNecessary
	 * (java.lang.Object, java.lang.Class)
	 */
	@Override
	public <T> T convertIfNecessary(Object value, Class<T> requiredType) { 
		return typeConverter.convertIfNecessary(value, requiredType);
	}

	@Override
	public <T> T convertIfNecessary(Object value, Method method, int index,
			Class<T> requiredType) {
		MethodParameter parameter=new MethodParameter(method,index);
		return typeConverter.convertIfNecessary(value, requiredType, parameter);
	}

}

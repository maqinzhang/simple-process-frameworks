package com.jd.frameworks.processframework.converter;

import java.lang.reflect.Method;
 

/**
 * @author luolishu
 *
 */
public interface InjectTypeConverter {

	/**
	 * Convert the value to the required type (if necessary from a String).
	 * <p>Conversions from String to any type will typically use the <code>setAsText</code>
	 * method of the PropertyEditor class. Note that a PropertyEditor must be registered
	 * for the given class for this to work; this is a standard JavaBeans API.
	 * A number of PropertyEditors are automatically registered.
	 * @param value the value to convert
	 * @param requiredType the type we must convert to
	 * (or <code>null</code> if not known, for example in case of a collection element)
	 * @return the new value, possibly the result of type conversion
	 * @throws TypeMismatchException if type conversion failed
	 * @see java.beans.PropertyEditor#setAsText(String)
	 * @see java.beans.PropertyEditor#getValue()
	 */
	<T> T convertIfNecessary(Object value, Class<T> requiredType) ; 
	
	/**
	 * Convert the value to the required type (if necessary from a String).
	 * <p>Conversions from String to any type will typically use the <code>setAsText</code>
	 * method of the PropertyEditor class. Note that a PropertyEditor must be registered
	 * for the given class for this to work; this is a standard JavaBeans API.
	 * A number of PropertyEditors are automatically registered.
	 * @param value the value to convert
	 * @param requiredType the type we must convert to
	 * (or <code>null</code> if not known, for example in case of a collection element)
	 * @return the new value, possibly the result of type conversion
	 * @throws TypeMismatchException if type conversion failed
	 * @see java.beans.PropertyEditor#setAsText(String)
	 * @see java.beans.PropertyEditor#getValue()
	 */
	<T> T convertIfNecessary(Object value,Method method,int index, Class<T> requiredType) ; 

}
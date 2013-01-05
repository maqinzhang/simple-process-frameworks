/**
 * 
 */
package com.simple.frameworks.processframework.bind.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.simple.frameworks.processframework.ExecuteContext;
 
 

/**
 * @author luolishu
 *
 */
public interface AnnotationHandler<T extends Annotation> {
	
	public void handleMethod(T ann,Method method,Object value,ExecuteContext context);

}

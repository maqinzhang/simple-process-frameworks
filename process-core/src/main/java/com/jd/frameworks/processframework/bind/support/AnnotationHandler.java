/**
 * 
 */
package com.jd.frameworks.processframework.bind.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.jd.frameworks.processframework.ExecuteContext;
 
 

/**
 * @author luolishu
 *
 */
public interface AnnotationHandler<T extends Annotation> {
	
	public void handleMethod(T ann,Method method,Object value,ExecuteContext context);

}

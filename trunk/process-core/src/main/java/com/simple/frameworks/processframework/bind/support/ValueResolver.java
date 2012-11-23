/**
 * 
 */
package com.simple.frameworks.processframework.bind.support;

import java.lang.annotation.Annotation;

import com.simple.frameworks.processframework.ExecuteContext;
 

/**
 * @author luolishu
 *
 */
public interface ValueResolver<T extends Annotation> {
public Object resolve(T ann,Class<?> parameterType,ExecuteContext context);
}

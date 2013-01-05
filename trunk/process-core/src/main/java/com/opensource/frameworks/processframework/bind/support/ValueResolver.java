/**
 * 
 */
package com.opensource.frameworks.processframework.bind.support;

import java.lang.annotation.Annotation;

import com.opensource.frameworks.processframework.ExecuteContext;
 

/**
 * @author luolishu
 *
 */
public interface ValueResolver<T extends Annotation> {
public Object resolve(T ann,Class<?> parameterType,ExecuteContext context);
}

/**
 * 
 */
package com.simple.frameworks.processframework.bind.support;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.simple.frameworks.processframework.bind.annotations.Attribute;

/**
 * @author luolishu
 *
 */
public class AnnotationHandlers {
	 static Map<Class<?>,AnnotationHandler<?>> container=new HashMap<Class<?>,AnnotationHandler<?>>();
	  static{
		  AnnotationHandler<?> resolver=new AttributeAnnotationHandler();
		  container.put(Attribute.class, resolver); 
	  }
	  
	  public static <A extends Annotation>AnnotationHandler<?> getHandler(Class<A> annCls){
		  return container.get(annCls);
	  }
}

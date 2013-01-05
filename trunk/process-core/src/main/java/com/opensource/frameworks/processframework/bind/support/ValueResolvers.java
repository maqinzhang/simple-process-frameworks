/**
 * 
 */
package com.opensource.frameworks.processframework.bind.support;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import com.opensource.frameworks.processframework.bind.annotations.Attribute;
import com.opensource.frameworks.processframework.bind.annotations.RequestVar;

/**
 * @author luolishu
 * 
 */
public abstract class ValueResolvers {
	static Map<Class<?>, ValueResolver<?>> container = new HashMap<Class<?>, ValueResolver<?>>();
	static {
		ValueResolver<?> resolver = new AttributeValueResolver();
		container.put(Attribute.class, resolver);
		resolver = new RequestValueResolver();
		container.put(RequestVar.class, resolver);
	}

	public static <A extends Annotation> ValueResolver<?> getValueResolver(
			Class<A> annCls) {
		return container.get(annCls);
	}
}

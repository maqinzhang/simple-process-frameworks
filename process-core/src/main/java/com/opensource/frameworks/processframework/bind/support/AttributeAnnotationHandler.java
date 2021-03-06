/**
 * 
 */
package com.opensource.frameworks.processframework.bind.support;

import java.lang.reflect.Method;

import com.opensource.frameworks.processframework.ExecuteContext;
import com.opensource.frameworks.processframework.bind.annotations.Attribute;

/**
 * @author luolishu
 *
 */
public class AttributeAnnotationHandler implements AnnotationHandler<Attribute> {

	@Override
	public void handleMethod(Attribute ann, Method method, Object value,
			ExecuteContext context) {
		if(method.getReturnType()==Void.TYPE){
			return;
		}
		String key=ann.value();
		context.session.setAttribute(key, value);
	} 

}

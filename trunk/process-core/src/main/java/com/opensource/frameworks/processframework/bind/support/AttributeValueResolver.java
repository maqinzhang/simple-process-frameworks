/**
 * 
 */
package com.opensource.frameworks.processframework.bind.support;
 

import com.opensource.frameworks.processframework.ExecuteContext;
import com.opensource.frameworks.processframework.bind.annotations.Attribute;

/**
 * @author luolishu
 *
 */
public class AttributeValueResolver implements ValueResolver<Attribute>{

	@Override
	public Object resolve(Attribute ann,Class<?> parameterType,ExecuteContext context) {
		String key=ann.value();		
		return context.session.getAttribute(key);
	}

}

/**
 * 
 */
package com.simple.frameworks.processframework.bind.support;
 

import com.simple.frameworks.processframework.ExecuteContext;
import com.simple.frameworks.processframework.bind.annotations.RequestVar;

/**
 * @author luolishu
 *
 */
public class RequestValueResolver implements ValueResolver<RequestVar>{

	@Override
	public Object resolve(RequestVar ann,Class<?> parameterType,ExecuteContext context) {
		String key=ann.value();		
		return context.request.getParameter(key);
	}

}

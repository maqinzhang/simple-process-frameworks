/**
 * 
 */
package com.opensource.frameworks.processframework.invocation;

import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;

/**
 * @author luolishu
 * 
 */
public interface Invocation {

	public Object invoke(WrapperExecuteContext context)throws Throwable;
}

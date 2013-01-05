/**
 * 
 */
package com.opensource.frameworks.processframework.invocation;

import java.util.List;

import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.result.Result;

/**
 * @author luolishu
 *
 */
public class ProcessInvocation implements Invocation {
	List<Invocation> invocations;
	public ProcessInvocation(List<Invocation> invocations){
		this.invocations=invocations;
	}
 
	@Override
	public Object invoke(WrapperExecuteContext context) throws Throwable{
		Object result = null;
		if(invocations!=null){
			for(Invocation invocation:invocations){
				result=invocation.invoke(context);
				if(result instanceof Result){
					return result;
				}
			}
		}
		return result;
	}

}

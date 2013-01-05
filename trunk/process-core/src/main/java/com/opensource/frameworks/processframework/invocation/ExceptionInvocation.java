/**
 * 
 */
package com.opensource.frameworks.processframework.invocation;

import java.util.List;
import java.util.Map;

import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
public abstract class ExceptionInvocation implements Invocation {
	List<Invocation> tryInvocations;
	Map<String, List<Invocation>> catchInvocations;
	List<Invocation> finallyInvocations;

/*	public Object invoke(WrapperExecuteContext context) throws Throwable{
		Object result = null;
		try {
			result = this.invokeTry(context);
		} catch(Throwable e){
			result = this.invokeCatch(e, context);
		}finally {
			result = this.invokeFinally(context);
		}
		return result;
	}*/

	public ExceptionInvocation(List<Invocation> tryInvocations,
			Map<String, List<Invocation>> catchInvocations,
			List<Invocation> finallyInvocations) {
		this.tryInvocations = tryInvocations;
		this.catchInvocations = catchInvocations;
		this.finallyInvocations = finallyInvocations;
	}

	public Object invokeTry(WrapperExecuteContext context) throws Throwable {
		Object result = null;
		for (Invocation invocation : tryInvocations) {
			result = invocation.invoke(context);
			if (result != null && result instanceof Result) {
				return result;
			}
		}
		return result;
	}

	public Object invokeFinally(WrapperExecuteContext context) throws Throwable {
		Object result = null;
		for (Invocation invocation : finallyInvocations) {
			result = invocation.invoke(context);
			if (result != null && result instanceof Result) {
				return result;
			}
		}
		return result;
	}

	public Object invokeCatch(Throwable e, WrapperExecuteContext context)
			throws Throwable {
		List<Invocation> invocations = catchInvocations.get(e.getClass()
				.getName());
		Object result = null;
		for (Invocation invocation : invocations) {
			result = invocation.invoke(context);
			if (result != null && result instanceof Result) {
				return result;
			}
		}
		return result;
	}

}

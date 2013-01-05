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
public class ConditionInvocation implements Invocation {
	List<IfInvocation> invocations;
	List<IfInvocation> elseIfInvocations;
	List<Invocation> elseInvocations;

	public ConditionInvocation(List<IfInvocation> invocations,
			List<IfInvocation> elseIfInvocations,
			List<Invocation> elseInvocations) {
		this.invocations = invocations;
		this.elseIfInvocations = elseIfInvocations;
		this.elseInvocations = elseInvocations;
	}

	@Override
	public Object invoke(WrapperExecuteContext context) throws Throwable {
		Object result = null;
		if (invocations != null) {
			for (IfInvocation invocation : invocations) {
				if (invocation.test(context)) {
					result = invocation.invoke(context);
					return result;
				} else {

				}

			}
		}
		if (elseIfInvocations != null) {
			for (IfInvocation invocation : elseIfInvocations) {
				if (invocation.test(context)) {
					result = invocation.invoke(context);
					return result;
				}
			}
		}
		if (elseInvocations != null) {
			for (Invocation invocation : elseInvocations) {
				result = invocation.invoke(context);
				if (result instanceof Result) {
					return result;
				}
			}
		}
		return result;
	}

}

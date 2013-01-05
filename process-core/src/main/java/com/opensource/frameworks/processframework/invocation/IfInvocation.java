/**
 * 
 */
package com.opensource.frameworks.processframework.invocation;

import java.util.List;

import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.result.Result;
import com.opensource.frameworks.processframework.script.JavascriptScriptExecutor;

/**
 * @author luolishu
 * 
 */
public class IfInvocation implements Invocation {
	List<Invocation> invocations;
	String expresion;
	static final JavascriptScriptExecutor scriptExecutor = new JavascriptScriptExecutor();

	public IfInvocation(String expresion, List<Invocation> invocations) {
		this.invocations = invocations;
		this.expresion = expresion;
	}

	@Override
	public Object invoke(WrapperExecuteContext context) throws Throwable {
		 
		Object result = null;
		if (invocations != null) {
			for (Invocation invocation : invocations) {
				result = invocation.invoke(context);
				if (result instanceof Result) {
					return result;
				}
			}
		}
		return result;
	}

	public boolean test(WrapperExecuteContext context) {
		Object value = scriptExecutor.execute(expresion, context);
		if (value instanceof Boolean) {
			return (Boolean) value;
		}
		return false;
	}

}

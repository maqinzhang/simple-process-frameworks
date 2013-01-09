/**
 * 
 */
package com.opensource.frameworks.processframework.invocation;

import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.script.ScriptExecutor;

/**
 * @author luolishu
 * 
 */
public class ScriptInvocation implements Invocation {
	ScriptExecutor scriptExecutor;
	String script;

	@Override
	public Object invoke(WrapperExecuteContext context) throws Throwable {
		return scriptExecutor.execute(script, context);
	}

	public ScriptExecutor getScriptExecutor() {
		return scriptExecutor;
	}

	public void setScriptExecutor(ScriptExecutor scriptExecutor) {
		this.scriptExecutor = scriptExecutor;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

}

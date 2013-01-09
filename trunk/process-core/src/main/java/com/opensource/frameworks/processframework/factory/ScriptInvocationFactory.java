package com.opensource.frameworks.processframework.factory;

import com.opensource.frameworks.processframework.config.ScriptConfig;
import com.opensource.frameworks.processframework.invocation.Invocation;
import com.opensource.frameworks.processframework.invocation.ScriptInvocation;
import com.opensource.frameworks.processframework.script.DefaultScriptExecutorFactory;
import com.opensource.frameworks.processframework.script.ScriptExecutorFactory;

public class ScriptInvocationFactory implements InvocationFactory<ScriptConfig> {
	static final ScriptExecutorFactory scriptExecutorFactory = new DefaultScriptExecutorFactory();

	@Override
	public Invocation create(ScriptConfig config) {
		ScriptInvocation invocation = new ScriptInvocation();
		invocation.setScript(config.getScript());
		invocation.setScriptExecutor(scriptExecutorFactory.create(config
				.getLanguage()));
		return invocation;
	}

}

package com.opensource.frameworks.processframework.factory;

import com.opensource.frameworks.processframework.config.DefaultConfigurationManager;
import com.opensource.frameworks.processframework.config.ForwardConfig;
import com.opensource.frameworks.processframework.invocation.ForwardInvocation;
import com.opensource.frameworks.processframework.invocation.Invocation;
import com.opensource.frameworks.processframework.script.DefaultScriptExecutorFactory;
import com.opensource.frameworks.processframework.script.ScriptExecutorFactory;

public class ForwardInvocationFactory implements
		InvocationFactory<ForwardConfig> {
	static final ScriptExecutorFactory scriptExecutorFactory = new DefaultScriptExecutorFactory();

	@Override
	public Invocation create(ForwardConfig config) {
		ObjectFactory objectFactory = DefaultConfigurationManager.getInstance()
				.getObjectFactory();

		ForwardInvocation invocation = new ForwardInvocation(); 
		invocation.setObjectFactory(objectFactory);
		invocation.setProcessId(config.getProcess());
		return invocation;
	}

}

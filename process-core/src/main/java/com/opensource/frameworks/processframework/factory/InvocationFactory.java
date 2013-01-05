package com.opensource.frameworks.processframework.factory;

import com.opensource.frameworks.processframework.config.ElementConfig;
import com.opensource.frameworks.processframework.invocation.Invocation;

public interface InvocationFactory<T extends ElementConfig> {
	
	public Invocation create(T element);

}

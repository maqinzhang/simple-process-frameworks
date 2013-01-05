package com.opensource.frameworks.processframework.factory;

import com.opensource.frameworks.processframework.config.ExceptionConfig;

public interface InvocationClassGenerator {
	Class<?> generateClass(ExceptionConfig config);
}
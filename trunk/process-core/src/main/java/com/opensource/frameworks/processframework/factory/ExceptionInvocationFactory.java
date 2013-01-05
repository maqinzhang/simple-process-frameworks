package com.opensource.frameworks.processframework.factory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opensource.frameworks.processframework.config.CatchTagConfig;
import com.opensource.frameworks.processframework.config.ElementConfig;
import com.opensource.frameworks.processframework.config.ExceptionConfig;
import com.opensource.frameworks.processframework.config.FinallyTagConfig;
import com.opensource.frameworks.processframework.config.TryTagConfig;
import com.opensource.frameworks.processframework.invocation.Invocation;

public class ExceptionInvocationFactory implements
		InvocationFactory<ExceptionConfig> {
	InvocationClassGenerator generator = new JavassistInvocationClassGenerator();

	@Override
	public Invocation create(ExceptionConfig config) {
		TryTagConfig tryConfig = config.getTryConfig();
		List<Invocation> tryInvocations = new LinkedList<Invocation>();
		Map<String, List<Invocation>> catchInvocationsMap = new LinkedHashMap<String, List<Invocation>>();
		List<Invocation> finalInvocations = new LinkedList<Invocation>();
		if (tryConfig != null) {
			for (ElementConfig item : tryConfig.getChilds()) {
				Invocation invocation = InvocationBuilder.build(item);
				tryInvocations.add(invocation);
			}
		}
		List<CatchTagConfig> catchConfigs = config.getCatchConfigs();
		if (catchConfigs != null) {
			for (CatchTagConfig catchItem : catchConfigs) {
				List<Invocation> catchInvocations = new LinkedList<Invocation>();
				catchInvocationsMap.put(catchItem.getException(),
						catchInvocations);
				for (ElementConfig item : catchItem.getChilds()) {
					Invocation invocation = InvocationBuilder.build(item);
					catchInvocations.add(invocation);
				}
			}
		}
		FinallyTagConfig finalConfig = config.getFinallyConfig();
		if (finalConfig != null) {
			for (ElementConfig item : finalConfig.getChilds()) {
				Invocation invocation = InvocationBuilder.build(item);
				finalInvocations.add(invocation);
			}
		}
		Object[] args = new Object[] { tryInvocations, catchInvocationsMap,
				finalInvocations };

		return newInstance(config, args);
	}

	private Invocation newInstance(ExceptionConfig config, Object... args) {
		Class claz = generator.generateClass(config);

		try {
			return (Invocation) claz.getConstructor(List.class, Map.class,
					List.class).newInstance(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

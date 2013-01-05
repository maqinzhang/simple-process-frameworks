package com.opensource.frameworks.processframework.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensource.frameworks.processframework.config.ConfigurationException;
import com.opensource.frameworks.processframework.config.NodeConfig;
import com.opensource.frameworks.processframework.config.ProcessConfig;
import com.opensource.frameworks.processframework.invocation.DefaultExecutionNode;
import com.opensource.frameworks.processframework.invocation.ExecutionNode;
import com.opensource.frameworks.processframework.invoke.ProcessHolder;
import com.opensource.frameworks.processframework.utils.ClassUtils;
import com.opensource.frameworks.processframework.utils.StringUtils;

/**
 * @author luolishu
 * 
 */
public class DefaultObjectFactory implements ObjectFactory {
	static Logger logger = LoggerFactory.getLogger(DefaultObjectFactory.class);
	Map<Class<?>, Object> reposMap = new HashMap<Class<?>, Object>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jd.frameworks.processframework.ObjectFactory#buildNode(com.jd.frameworks
	 * .config.NodeConfig)
	 */
	@Override
	public Object buildNode(NodeConfig config) {
		try {
			if (StringUtils.isBlank(config.className)) {
				return null;
			}
			Class<?> claz = ClassUtils.forName(config.className, this
					.getClass().getClassLoader());
			Object result = reposMap.get(claz);
			if (result == null) {
				synchronized (reposMap) {
					result = reposMap.get(claz);
					if (result == null) {
						result = claz.newInstance();
						reposMap.put(claz, result);
					}
				}

			}
			return result;
		} catch (Exception e) {
			logger.error("build node exception!", e);
			throw new ConfigurationException("build node process exception!", e);
		} catch (LinkageError e) {
			logger.error("build node exception!", e);
			throw new ConfigurationException("build node process exception!", e);
		}
	}
	@Override
	public ProcessHolder buildProcessHolder(ProcessConfig config) {
		DefaultProcessHolder holder = new DefaultProcessHolder();
		holder.id = config.id; 
		holder.processConfig = config;
		holder.exceptionHandlerId = config.exceptionHandlerId;
		if (StringUtils.isNotBlank(config.interfaceClass)) {
			String[] interfaceNames = config.interfaceClass.split(",|;");
			List<Class<?>> classList = new LinkedList<Class<?>>();
			for (String className : interfaceNames) {
				if (StringUtils.isNotBlank(config.interfaceClass)) {
					try {
						Class<?> intefaceClass = Class.forName(className);
						if (!intefaceClass.isInterface()) {
							throw new ConfigurationException("class name="
									+ intefaceClass.getName()
									+ ",is not a interface!");
						}
						classList.add(intefaceClass);
					} catch (ClassNotFoundException e) {
						logger.error("build node process exception!", e);
						throw new ConfigurationException(
								"build node process exception!", e);
					}
				}
			}
			holder.proxyIntefaces = classList.toArray(new Class[] {});
		}
		return holder;
	}
	protected  ExecutionNode  buildExecuteNode( NodeConfig config) { 
		return new DefaultExecutionNode(config, this);
	}
	protected List<ExecutionNode> buildExecuteNodes(List<NodeConfig> nodeConfigs) {
		List<ExecutionNode> nodesList = new ArrayList<ExecutionNode>();
		for (NodeConfig config : nodeConfigs) {
			nodesList.add(new DefaultExecutionNode(config, this));
		}
		return nodesList;
	}

	class DefaultProcessHolder implements ProcessHolder {
		String exceptionHandlerId;
		Class<?> resultClass;
		Class<?>[] proxyIntefaces;
		String id; 
		ProcessConfig processConfig;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public Class<?> getResultClass() {
			return resultClass;
		}

	 

		@Override
		public ProcessConfig getProcessConfig() {
			return processConfig;
		}

		@Override
		public String getExceptionHandlerId() {
			return exceptionHandlerId;
		}

		public void setExceptionHandlerId(String exceptionHandlerId) {
			this.exceptionHandlerId = exceptionHandlerId;
		}

		@Override
		public Class<?>[] getProxyInterfaces() {
			return proxyIntefaces;
		}

		public void setProxyIntefaces(Class<?>[] proxyIntefaces) {
			this.proxyIntefaces = proxyIntefaces;
		}

	}
}

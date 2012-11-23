package com.simple.frameworks.processframework.factory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.frameworks.processframework.config.ConfigurationException;
import com.simple.frameworks.processframework.config.NodeConfig;
import com.simple.frameworks.processframework.config.ProcessConfig;
import com.simple.frameworks.processframework.config.SubProcessConfig;
import com.simple.frameworks.processframework.invoke.DefaultExecutionNode;
import com.simple.frameworks.processframework.invoke.ExecutionNode;
import com.simple.frameworks.processframework.invoke.ProcessHolder;
import com.simple.frameworks.processframework.invoke.SubProcess;
import com.simple.frameworks.processframework.utils.ClassUtils;
import com.simple.frameworks.processframework.utils.StringUtils;

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
	 * com.simple.frameworks.processframework.ObjectFactory#buildNode(com.simple.frameworks
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

	protected List<ExecutionNode> buildExecuteNodes(List<NodeConfig> nodeConfigs) {
		List<ExecutionNode> nodesList = new ArrayList<ExecutionNode>();
		for (NodeConfig config : nodeConfigs) {
			nodesList.add(new DefaultExecutionNode(config, this));
		}
		return nodesList;
	}

	protected List<SubProcess> buildCatchProcess(
			List<SubProcessConfig> subProcessConfigList) {
		if (subProcessConfigList == null || subProcessConfigList.isEmpty()) {
			return null;
		}
		List<SubProcess> processList = new LinkedList<SubProcess>();
		for (SubProcessConfig config : subProcessConfigList) {
			SubProcess process = this.buildSubProcess(config);
			processList.add(process);
		}
		return processList;
	}

	protected SubProcess buildSubProcess(SubProcessConfig subProcessConfig) {
		if (subProcessConfig == null) {
			return null;
		}
		SubProcessImpl subProcess = new SubProcessImpl();
		subProcess.exception = subProcessConfig.exception;
		if (StringUtils.isNotBlank(subProcess.exception)) {
			try {
				subProcess.exceptionClass = Class.forName(subProcess.exception);
			} catch (ClassNotFoundException e) {
				logger.error("build subprocess exception!", e);
				throw new ConfigurationException(
						"build node process exception!", e);
			}
		}
		subProcess.executionNodes = this.buildExecuteNodes(subProcessConfig
				.getNodes());
		subProcess.subProcessConfig = subProcessConfig;
		return subProcess;
	}

	@Override
	public ProcessHolder buildProcessHolder(ProcessConfig config) {
		DefaultProcessHolder holder = new DefaultProcessHolder();
		holder.id = config.id;
		holder.mainProcess = this.buildSubProcess(config.getMainProcess());
		holder.setCatchProcess(buildCatchProcess(config.getCatchProcess()));
		holder.finallyProcess = this.buildSubProcess(config.getFinalProcess());
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

	class SubProcessImpl implements SubProcess {
		String exception;
		List<ExecutionNode> executionNodes;
		SubProcessConfig subProcessConfig;
		Class<?> exceptionClass;

		@Override
		public String getException() {
			return exception;
		}

		@Override
		public List<ExecutionNode> getNodes() {
			return executionNodes;
		}

		public SubProcessConfig getSubProcessConfig() {
			return subProcessConfig;
		}

		@Override
		public Class<?> getExceptionClass() {
			return exceptionClass;
		}

	}

	class DefaultProcessHolder implements ProcessHolder {
		String exceptionHandlerId;
		Class<?> resultClass;
		Class<?>[] proxyIntefaces;
		String id;
		SubProcess mainProcess;
		List<SubProcess> catchProcess;
		Map<Class<?>, SubProcess> exceptionMap = new HashMap<Class<?>, SubProcess>();
		SubProcess finallyProcess;
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

		public SubProcess getMainProcess() {
			return mainProcess;
		}

		public void setMainProcess(SubProcess mainProcess) {
			this.mainProcess = mainProcess;
		}

		public List<SubProcess> getCatchProcess() {
			return catchProcess;
		}

		public void setCatchProcess(List<SubProcess> catchProcess) {
			this.catchProcess = catchProcess;

			if (catchProcess != null) {
				for (SubProcess process : catchProcess) {
					exceptionMap.put(process.getExceptionClass(), process);
				}
			}
		}

		public SubProcess getFinallyProcess() {
			return finallyProcess;
		}

		public void setFinallyProcess(SubProcess finallyProcess) {
			this.finallyProcess = finallyProcess;
		}

		@Override
		public SubProcess getExceptionProcess(Class<?> claz) {
			SubProcess process = exceptionMap.get(claz);
			if (process == null) {
				for (SubProcess p : this.catchProcess) {
					if (claz.equals(p.getExceptionClass())
							|| p.getExceptionClass().isAssignableFrom(claz)) {
						return p;
					}
				}
			}
			return process;
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

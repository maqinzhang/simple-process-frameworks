/**
 * 
 */
package com.jd.frameworks.processframework.config;

import java.util.List;

/**
 * @author luolishu
 * 
 */
public class SubProcessConfig {	
	public ProcessConfig processConfig;
	public String id;
	public String exception;
	public List<NodeConfig> nodes;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public List<NodeConfig> getNodes() {
		return nodes;
	}
	public void setNodes(List<NodeConfig> nodes) {
		this.nodes = nodes;
	}
	public ProcessConfig getProcessConfig() {
		return processConfig;
	}
	public void setProcessConfig(ProcessConfig processConfig) {
		this.processConfig = processConfig;
	}
	@Override
	public String toString() {
		return "SubProcessConfig [id=" + id + ", exception=" + exception
				+ ", nodes=" + nodes + "]";
	} 

}

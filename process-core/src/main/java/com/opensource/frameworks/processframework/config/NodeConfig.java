package com.opensource.frameworks.processframework.config;

/**
 * @author luolishu
 * 
 */
public class NodeConfig implements ElementConfig{ 
	ProcessConfig process;
	public String id;
	public String className;	
	public String scope;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	 
 
	public ProcessConfig getProcess() {
		return process;
	}

	public void setProcess(ProcessConfig process) {
		this.process = process;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scrope) {
		this.scope = scrope;
	}

	@Override
	public String toString() {
		return "NodeConfig [id="
				+ id + ", className=" + className + ", scope=" + scope + "]";
	}

	 
	 
	

}

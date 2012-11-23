package com.jd.frameworks.processframework.config;

/**
 * @author luolishu
 * 
 */
public class NodeConfig {
	public SubProcessConfig subProcessConfig;
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

	 

	public SubProcessConfig getSubProcessConfig() {
		return subProcessConfig;
	}

	public void setSubProcessConfig(SubProcessConfig subProcessConfig) {
		this.subProcessConfig = subProcessConfig;
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

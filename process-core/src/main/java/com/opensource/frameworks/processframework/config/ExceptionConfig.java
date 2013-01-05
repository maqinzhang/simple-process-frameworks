/**
 * 
 */
package com.opensource.frameworks.processframework.config;

import java.util.LinkedList;
import java.util.List;

/**
 * @author luolishu
 * 
 */
public class ExceptionConfig implements ElementConfig {
	TryTagConfig tryConfig;
	List<CatchTagConfig> catchConfigs = new LinkedList<CatchTagConfig>();
	FinallyTagConfig finallyConfig;

	public void addCatch(CatchTagConfig config) {
		catchConfigs.add(config);
	}

	public TryTagConfig getTryConfig() {
		return tryConfig;
	}

	public void setTryConfig(TryTagConfig tryConfig) {
		this.tryConfig = tryConfig;
	}

	public void setFinallyConfig(FinallyTagConfig finallyConfig) {
		this.finallyConfig = finallyConfig;
	}

	public List<CatchTagConfig> getCatchConfigs() {
		return catchConfigs;
	}

	public void setCatchConfigs(List<CatchTagConfig> catchConfigs) {
		this.catchConfigs = catchConfigs;
	}

	public FinallyTagConfig getFinallyConfig() {
		return finallyConfig;
	}

	@Override
	public String toString() {
		return "ExceptionConfig [tryConfig=" + tryConfig + ", catchConfigs="
				+ catchConfigs + ", finallyConfig=" + finallyConfig + "]";
	}

}

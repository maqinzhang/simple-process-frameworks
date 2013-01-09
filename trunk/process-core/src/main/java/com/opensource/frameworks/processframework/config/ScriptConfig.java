package com.opensource.frameworks.processframework.config;

/**
 * @author luolishu
 * 
 */
public class ScriptConfig implements ElementConfig {
	String script;
	String language;

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}

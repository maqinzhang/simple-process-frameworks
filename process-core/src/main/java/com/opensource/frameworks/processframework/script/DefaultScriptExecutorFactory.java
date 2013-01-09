/**
 * 
 */
package com.opensource.frameworks.processframework.script;

import com.opensource.frameworks.processframework.Script;
import com.opensource.frameworks.processframework.Script.ScriptTypes;

/**
 * @author luolishu
 * 
 */
public class DefaultScriptExecutorFactory implements ScriptExecutorFactory {

	 
	@Override
	public ScriptExecutor create(Script ann) {
		return create(ann.value());
	}

	private ScriptExecutor create(ScriptTypes type) {
		switch (type) {
		case GROOVY:
			break;
		default:
			return new JavascriptScriptExecutor();

		}
		return null;
	}

	@Override
	public ScriptExecutor create(String language) {
		ScriptTypes scriptType = ScriptTypes.getType(language);
		return create(scriptType);
	}

}

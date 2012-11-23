/**
 * 
 */
package com.simple.frameworks.processframework.script;

import com.simple.frameworks.processframework.Script;

/**
 * @author luolishu
 *
 */
public class DefaultScriptExecutorFactory implements ScriptExecutorFactory {

	/* (non-Javadoc)
	 * @see com.simple.frameworks.processframework.script.ScriptExecutorFactory#create(com.simple.frameworks.processframework.Script)
	 */
	@Override
	public ScriptExecutor create(Script ann) {
		switch(ann.value()){
		case GROOVY:
			break;
		default:
			return new JavascriptScriptExecutor();
		
		}
		return null;
	}

}

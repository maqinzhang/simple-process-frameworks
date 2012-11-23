/**
 * 
 */
package com.jd.frameworks.processframework.script;

import com.jd.frameworks.processframework.Script;

/**
 * @author luolishu
 *
 */
public class DefaultScriptExecutorFactory implements ScriptExecutorFactory {

	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.script.ScriptExecutorFactory#create(com.jd.frameworks.processframework.Script)
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

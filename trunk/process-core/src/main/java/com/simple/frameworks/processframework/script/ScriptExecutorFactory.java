/**
 * 
 */
package com.simple.frameworks.processframework.script;

import com.simple.frameworks.processframework.Script;


/**
 * @author luolishu
 *
 */
public interface ScriptExecutorFactory {

	public ScriptExecutor create(Script ann);
}

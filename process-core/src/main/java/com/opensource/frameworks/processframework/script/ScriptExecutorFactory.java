/**
 * 
 */
package com.opensource.frameworks.processframework.script;

import com.opensource.frameworks.processframework.Script;


/**
 * @author luolishu
 *
 */
public interface ScriptExecutorFactory {

	public ScriptExecutor create(Script ann);
}

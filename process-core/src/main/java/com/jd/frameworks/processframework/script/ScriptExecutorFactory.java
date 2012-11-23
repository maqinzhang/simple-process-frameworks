/**
 * 
 */
package com.jd.frameworks.processframework.script;

import com.jd.frameworks.processframework.Script;


/**
 * @author luolishu
 *
 */
public interface ScriptExecutorFactory {

	public ScriptExecutor create(Script ann);
}

/**
 * 
 */
package com.opensource.frameworks.processframework.script;

import java.util.Map;

import com.opensource.frameworks.processframework.ExecuteContext;

/**
 * @author luolishu
 *
 */
public interface ScriptExecutor  {
    /**
     * 执行脚本
     * @param script
     * @param context
     * @return
     */
	public Object execute(String script,ExecuteContext context);
	
	 /**
     * 执行脚本
     * @param script
     * @param context
     * @return
     */
	public Object execute(String script,ExecuteContext context,Map<String,Object> otherContext);
}

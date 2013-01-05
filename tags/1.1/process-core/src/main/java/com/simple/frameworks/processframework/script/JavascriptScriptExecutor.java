/**
 * 
 */
package com.simple.frameworks.processframework.script;

import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.simple.frameworks.processframework.ExecuteContext;
import com.simple.frameworks.processframework.utils.StringUtils;

/**
 * @author luolishu
 * 
 */
public class JavascriptScriptExecutor  implements ScriptExecutor  {
	static Logger logger=LoggerFactory.getLogger(JavascriptScriptExecutor.class);
	private static final ThreadLocal<ScriptEngine> threadLocal = new ThreadLocal<ScriptEngine>();

	ScriptEngine getEngine() {
		ScriptEngine scriptEngine = threadLocal.get();
		if (scriptEngine != null) {
			return scriptEngine;
		}
		ScriptEngineManager manager = new ScriptEngineManager();
		scriptEngine = manager.getEngineByName("javascript");
		threadLocal.set(scriptEngine);
		return scriptEngine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.simple.frameworks.processframework.script.ScriptExecutor#execute(java
	 * .lang.String, com.simple.frameworks.processframework.ExecuteContext)
	 */
	@Override
	public Object execute(String script, ExecuteContext context) {
		return this.execute(script, context, null);
	}
	
	@Override
	public Object execute(String script, ExecuteContext context,
			Map<String, Object> otherContext) {
		if(StringUtils.isBlank(script)){
			return null;
		}
		ScriptEngine engine = getEngine(); 
		engine.put("executeContext",context);
		engine.put("session",context.session);
		engine.put("request",context.request);
		engine.put("result",context.result);
		if(otherContext!=null){
			for(Map.Entry<String, Object> entry:otherContext.entrySet()){
				engine.put(entry.getKey(),entry.getValue());
			}
		}
		Object value=null;
		try {
			value=engine.eval(script);
		} catch (ScriptException e) {
			logger.error("execute script error!",e);
		} 
		return value;
	}

}

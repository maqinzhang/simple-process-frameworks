/**
 * 
 */
package com.simple.frameworks.processframework.invoke;

import com.simple.frameworks.processframework.request.Request;
import com.simple.frameworks.processframework.result.Result;

/**
 * @author luolishu
 *
 */
public interface ProcessInvoker {
	/**
	 * �������
	 * @return
	 */
	public Result<?,?> invoke(Request request);

}

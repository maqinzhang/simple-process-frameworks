/**
 * 
 */
package com.opensource.frameworks.processframework.invoke;

import com.opensource.frameworks.processframework.request.Request;
import com.opensource.frameworks.processframework.result.Result;

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

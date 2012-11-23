/**
 * 
 */
package com.jd.frameworks.processframework.invoke;

import com.jd.frameworks.processframework.request.Request;
import com.jd.frameworks.processframework.result.Result;

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

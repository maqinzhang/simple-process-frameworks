/**
 * 
 */
package com.opensource.frameworks.processframework.invoke;

import com.opensource.frameworks.processframework.request.Request;
import com.opensource.frameworks.processframework.request.Session;

/**
 * @author luolishu
 *
 */
public interface ExecuteContextFactory {
public WrapperExecuteContext create(Request request,Session session);
}

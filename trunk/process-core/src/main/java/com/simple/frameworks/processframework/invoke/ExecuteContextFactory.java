/**
 * 
 */
package com.simple.frameworks.processframework.invoke;

import com.simple.frameworks.processframework.request.Request;
import com.simple.frameworks.processframework.request.Session;

/**
 * @author luolishu
 *
 */
public interface ExecuteContextFactory {
public DelegatingExecuteContext create(Request request,Session session);
}

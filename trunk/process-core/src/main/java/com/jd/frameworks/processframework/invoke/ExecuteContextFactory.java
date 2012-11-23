/**
 * 
 */
package com.jd.frameworks.processframework.invoke;

import com.jd.frameworks.processframework.request.Request;
import com.jd.frameworks.processframework.request.Session; 

/**
 * @author luolishu
 *
 */
public interface ExecuteContextFactory {
public DelegatingExecuteContext create(Request request,Session session);
}

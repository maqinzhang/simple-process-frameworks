/**
 * 
 */
package com.opensource.frameworks.processframework.invoke;
  
import java.util.LinkedHashSet; 
import java.util.Set;

import com.opensource.frameworks.processframework.ExecuteContext;
import com.opensource.frameworks.processframework.invocation.ExecutionNode;
import com.opensource.frameworks.processframework.request.Request;
import com.opensource.frameworks.processframework.request.Session;
import com.opensource.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
public final class WrapperExecuteContext extends ExecuteContext{ 
	protected ProcessHolder processHolder;
	ExecuteContext context; 
	public ExecutionNode lastExecuteNode;
	public Set<ExecutionNode> executionNodes=new LinkedHashSet<ExecutionNode>();
	public WrapperExecuteContext(){
	
	}
	WrapperExecuteContext(ExecuteContext inputContext){
		this.request=inputContext.request;
		this.result=inputContext.result;
		this.session=inputContext.session;
		this.context=inputContext;
	}
	public ExecuteContext getContext() {
		return context;
	}
	public void setContext(ExecuteContext context) {
		this.context = context;
	} 
	public ExecutionNode getLastExecuteNode() {
		return lastExecuteNode;
	}
	public void setLastExecuteNode(ExecutionNode lastExecuteNode) {
		executionNodes.add(lastExecuteNode);
		this.lastExecuteNode = lastExecuteNode;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Result<?,?> getResult() {
		return result;
	}
	public void setResult(Result<?,?> result) {
		this.result = result;
	}
	public Set<ExecutionNode> getExecutionNodes() {
		return executionNodes;
	}
	public ProcessHolder getProcessHolder() {
		return processHolder;
	}
	public void setProcessHolder(ProcessHolder processHolder) {
		this.processHolder = processHolder;
	} 

}

/**
 * 
 */
package com.simple.frameworks.processframework.invoke;
  
import java.util.LinkedHashSet; 
import java.util.Set;

import com.simple.frameworks.processframework.ExecuteContext;
import com.simple.frameworks.processframework.request.Request;
import com.simple.frameworks.processframework.request.Session;
import com.simple.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
public final class DelegatingExecuteContext extends ExecuteContext{ 
	protected ProcessHolder processHolder;
	ExecuteContext context;
	public SubProcess lastSubProcess;
	public ExecutionNode lastExecuteNode;
	public Set<ExecutionNode> executionNodes=new LinkedHashSet<ExecutionNode>();
	DelegatingExecuteContext(){
	
	}
	DelegatingExecuteContext(ExecuteContext inputContext){
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
	public SubProcess getLastSubProcess() {
		return lastSubProcess;
	}
	public void setLastSubProcess(SubProcess lastSubProcess) {
		
		this.lastSubProcess = lastSubProcess;
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

/**
 * 
 */
package com.opensource.frameworks.spring.test;

import org.springframework.stereotype.Service;

import com.opensource.frameworks.processframework.Execute;
import com.opensource.frameworks.processframework.invocation.ExecutionNode;
import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;

/**
 * @author luolishu
 * 
 */
@Service
public class PrintlnInfo {
	@Execute
	public void printNodesEx(WrapperExecuteContext context) {
		StringBuilder printBuffer = new StringBuilder("Invoking process =["+context.getProcessHolder().getProcessConfig().getId()+"], executed nodes:  [start->");
		if (context.getExecutionNodes() != null) {
			for (ExecutionNode node : context.getExecutionNodes()) {
				printBuffer.append(node.getNodeConfig().getId()).append("->");
			}
		}
		printBuffer.append("end];");
		System.out.println(printBuffer);

	}
}

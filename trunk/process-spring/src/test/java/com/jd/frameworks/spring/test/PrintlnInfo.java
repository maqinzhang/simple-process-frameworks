/**
 * 
 */
package com.jd.frameworks.spring.test;

import org.springframework.stereotype.Service;

import com.jd.frameworks.processframework.Execute;
import com.jd.frameworks.processframework.invoke.DelegatingExecuteContext;
import com.jd.frameworks.processframework.invoke.ExecutionNode;

/**
 * @author luolishu
 * 
 */
@Service
public class PrintlnInfo {
	@Execute
	public void printNodesEx(DelegatingExecuteContext context) {
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
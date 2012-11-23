/**
 * 
 */
package com.simple.frameworks.spring.test;

import com.simple.frameworks.processframework.Execute;
import com.simple.frameworks.processframework.Validate;
import com.simple.frameworks.processframework.bind.annotations.RequestVar;
import com.simple.frameworks.processframework.invoke.DelegatingExecuteContext;
import com.simple.frameworks.processframework.request.Request;
import com.simple.frameworks.processframework.request.Session;

/**
 * @author luolishu
 *
 */
public class JavaBean2 {
	@Execute
	public void printNodesEx(DelegatingExecuteContext context) {
		System.out.println("------------node list--------------" +context.getExecutionNodes());

	}
	@Execute
	public void method1(@RequestVar("hello") String hello){
		System.out.println("--------------------------"+hello+"----------------------------");		

		
	}
	@Execute
	public void method2(){
		/*throw new RuntimeException();*/
		
	}
	
	@Validate
	public void method3(Request request,Session session){
		System.out.println("--------------------------validate----------------------------"+request.getParameter("hello"));		
	}

}

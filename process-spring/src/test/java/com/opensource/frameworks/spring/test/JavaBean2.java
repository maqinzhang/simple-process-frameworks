/**
 * 
 */
package com.opensource.frameworks.spring.test;

import com.opensource.frameworks.processframework.Execute;
import com.opensource.frameworks.processframework.Validate;
import com.opensource.frameworks.processframework.bind.annotations.RequestVar;
import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.request.Request;
import com.opensource.frameworks.processframework.request.Session;

/**
 * @author luolishu
 *
 */
public class JavaBean2 {
	@Execute
	public void printNodesEx(WrapperExecuteContext context) {
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

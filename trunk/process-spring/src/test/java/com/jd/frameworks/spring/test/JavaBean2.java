/**
 * 
 */
package com.jd.frameworks.spring.test;

import com.jd.frameworks.processframework.Execute;
import com.jd.frameworks.processframework.Validate;
import com.jd.frameworks.processframework.bind.annotations.RequestVar;
import com.jd.frameworks.processframework.invoke.DelegatingExecuteContext;
import com.jd.frameworks.processframework.request.Request;
import com.jd.frameworks.processframework.request.Session;

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

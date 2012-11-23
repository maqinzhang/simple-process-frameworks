/**
 * 
 */
package com.jd.frameworks.spring.test;

import java.io.Serializable;
import java.util.Date;

import com.jd.frameworks.processframework.Execute;
import com.jd.frameworks.processframework.ExecuteContext;
import com.jd.frameworks.processframework.Validate;
import com.jd.frameworks.processframework.bind.annotations.Attribute;
import com.jd.frameworks.processframework.bind.annotations.RequestVar;
import com.jd.frameworks.processframework.invoke.DelegatingExecuteContext;
import com.jd.frameworks.processframework.request.Request;
import com.jd.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
public class JavaBean {
	@Execute
	public @Attribute("attr")
	String method0(@RequestVar("hello") String hello, Result result,
			ExecuteContext context) {
		return "Abcddafaslfjkadslk";
	}
	
	@Execute
	public void method1(@RequestVar("hello") String hello,
			@Attribute("attr") String attr) {
		System.out.println("------------method1--------------" + hello
				+ "----------------------------" + attr);

	}

	@Execute
	public void method2(@RequestVar("date") Date date) {
		System.out.println("------------date--------------" + date
				+ "----------------------------" + date);

	}
	@Execute
	public void execute() {
		System.out
				.println("============================execute==============================");

	}
	@Execute
	public void execute(@RequestVar("date") Date date) {
		System.out.println("------------execute--------------" + date
				+ "----------------------------" + date);

	}

	@Execute
	public Object method4() {
		Result result = new Result() {

			@Override
			public void setEnumCode(Enum enumCode) {
				// TODO Auto-generated method stub

			}

			@Override
			public Enum getEnumCode() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Serializable getModel() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setModel(Serializable model) {
				// TODO Auto-generated method stub

			}
		};

		return null;

	}

	@Validate
	public void method3(Request request) {
		System.out
				.println("------------request--------------validate----------------------------"
						+ request.getParameter("hello"));
	}

}

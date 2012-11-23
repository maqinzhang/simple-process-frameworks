/**
 * 
 */
package com.simple.frameworks.simple.test.binding;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.Execute;
import com.simple.frameworks.processframework.ExecuteContext;
import com.simple.frameworks.processframework.bind.annotations.Attribute;
import com.simple.frameworks.processframework.request.Request;
import com.simple.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
@Service
public class AttibuteBinding {

	@Attribute("hello")
	public @Execute
	String result() {
		return "Hello!_________________-";
	}

	public @Execute
	void result(@Attribute("hello") String hello,Result result,Request request,ExecuteContext context) {
		result.setModel(hello);
		System.out.println("attibute binding,hello=" + hello);
		System.out.println("attibute binding,result=" + result+" request="+request+" context="+context);
	}
	public Result execute(){
		Result result=new Result(){

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
				return "Hello world!";
			}

			@Override
			public void setModel(Serializable model) {
				// TODO Auto-generated method stub
				
			}
			
		};
		return result;
	}
}

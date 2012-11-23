/**
 * 
 */
package com.simple.process.showcase;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.Execute;
import com.simple.frameworks.processframework.result.DefaultResult;
import com.simple.frameworks.processframework.result.Result;

/**
 * @author luolishu
 *
 */
@Service
public class HelloWorld {
    @Execute
	public void sayHello(){
		System.out.println("Hello world!");
	}
    @Execute
   	public Result result(){
   		Result result=new DefaultResult();
   		result.setModel("Hello world! process Test case!");
   		return result;
   	}
}

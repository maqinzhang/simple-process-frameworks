/**
 * 
 */
package com.simple.process.showcase;

import org.springframework.stereotype.Service;

import com.opensource.frameworks.processframework.Execute;
import com.opensource.frameworks.processframework.result.DefaultResult;
import com.opensource.frameworks.processframework.result.Result;

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

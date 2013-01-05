/**
 * 
 */
package com.opensource.frameworks.simple.test.validate;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.opensource.frameworks.processframework.Execute;
import com.opensource.frameworks.processframework.bind.annotations.Attribute;
import com.opensource.frameworks.processframework.bind.annotations.RequestVar;
import com.opensource.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
@Service
public class ValidateLogic {

	 public boolean validate(){
		 return true;
	 }
	 
	 public void execute(@RequestVar("foo") String a){
		 System.out.println("========================================="+a);
	 } 
}

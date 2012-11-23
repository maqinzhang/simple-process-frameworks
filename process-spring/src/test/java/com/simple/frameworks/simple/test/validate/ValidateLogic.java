/**
 * 
 */
package com.simple.frameworks.simple.test.validate;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.Execute;
import com.simple.frameworks.processframework.bind.annotations.Attribute;
import com.simple.frameworks.processframework.bind.annotations.RequestVar;
import com.simple.frameworks.processframework.result.Result;

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

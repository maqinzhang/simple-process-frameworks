/**
 * 
 */
package com.jd.frameworks.simple.test.validate;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.jd.frameworks.processframework.Execute;
import com.jd.frameworks.processframework.bind.annotations.Attribute;
import com.jd.frameworks.processframework.bind.annotations.RequestVar;
import com.jd.frameworks.processframework.result.Result;

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

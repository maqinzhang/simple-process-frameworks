/**
 * 
 */
package com.simple.frameworks.simple.test.inject;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.bind.annotations.RequestVar;

/**
 * @author luolishu
 *
 */
@Service
public class InjectLogic {

	public void execute(@RequestVar("a") String a,@RequestVar("a")Integer int1,@RequestVar("a")int int2,@RequestVar("a")Long long1,@RequestVar("a")long long2){
		System.out.println("values: a="+a+" int1="+int1+" int2="+int2+" long1="+long1+" long2="+long2);
		
	}
}

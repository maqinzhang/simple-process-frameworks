/**
 * 
 */
package com.simple.frameworks.simple.proxy;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.bind.annotations.RequestVar;

/**
 * @author luolishu
 *
 */
@Service
public class ProxyLogic1 {

	public void execute(){
		System.out.println("execute1");
	}
	
	public void execute(@RequestVar("name") String name){
		System.out.println("execute2,name="+name);
	}
	
	public void execute(@RequestVar("name") String name,@RequestVar("age")int age){
		System.out.println("execute2,name="+name);
	}
}

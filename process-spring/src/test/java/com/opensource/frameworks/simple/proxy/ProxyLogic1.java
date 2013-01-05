/**
 * 
 */
package com.opensource.frameworks.simple.proxy;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.opensource.frameworks.processframework.bind.annotations.RequestVar;
import com.opensource.frameworks.spring.test.JavaBean2;

/**
 * @author luolishu
 *
 */
@Service
public class ProxyLogic1 {
	@Resource
	JavaBean2 bean;
	public void execute(){
		System.out.println("execute1");
	}
	
	public void execute(@RequestVar("name") String name){
		System.out.println("execute2,name="+name);
	}
	
	public void execute(@RequestVar("name") String name,@RequestVar("age")int age){
		System.out.println("execute2,name="+name+" and age="+age);
	}

	public void setBean(JavaBean2 bean) {
		this.bean = bean;
	}
}

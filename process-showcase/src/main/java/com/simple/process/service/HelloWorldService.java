/**
 * 
 */
package com.simple.process.service;

import com.opensource.frameworks.processframework.bind.annotations.RequestVar;

/**
 * @author luolishu
 * 
 */
public interface HelloWorldService {

	public String sayHello(@RequestVar("name") String name);
	public String sayHello(@RequestVar("name") String name,@RequestVar("age") int age);
}

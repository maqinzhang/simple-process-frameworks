/**
 * 
 */
package com.simple.frameworks.simple.proxy;

import com.simple.frameworks.processframework.bind.annotations.RequestVar;

/**
 * @author luolishu
 * 
 */
public interface ProxyInterface {
	public String hello();

	public String hello(@RequestVar("name") String name,
			@RequestVar("age") int age);
}

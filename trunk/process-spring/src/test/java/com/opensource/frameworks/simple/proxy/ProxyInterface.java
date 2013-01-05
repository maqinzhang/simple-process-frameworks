/**
 * 
 */
package com.opensource.frameworks.simple.proxy;

import com.opensource.frameworks.processframework.bind.annotations.RequestVar;

/**
 * @author luolishu
 * 
 */
public interface ProxyInterface {
	public String hello();

	public String hello(@RequestVar("name") String name,
			@RequestVar("age") int age);
}

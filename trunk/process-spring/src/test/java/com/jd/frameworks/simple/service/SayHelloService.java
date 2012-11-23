/**
 * 
 */
package com.jd.frameworks.simple.service;

import org.springframework.stereotype.Service;

/**
 * @author luolishu
 *
 */
@Service
public class SayHelloService {
	
	public void sayHello(String name){
		System.out.println("Hello "+name);
	}

}

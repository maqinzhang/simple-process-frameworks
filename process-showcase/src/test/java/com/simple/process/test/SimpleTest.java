/**
 * 
 */
package com.simple.process.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simple.process.service.HelloWorldService;

/**
 * @author luolishu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-config.xml" })
public class SimpleTest {
	@Resource
	private HelloWorldService helloWorldService;

	@Test
	public void testSayHello() {
		helloWorldService.sayHello("none");

	}

	@Test
	public void testSayHello2() {

		helloWorldService.sayHello("xiaoming", 20);

	}

	@Test
	public void testSayHello3() {

		helloWorldService.sayHello("xiaohua", 40);
	}
	@Test
	public void testSayHello33() {

		helloWorldService.sayHello("xiaohua", 25);
	}

	@Test
	public void testSayHello4() {

		helloWorldService.sayHello("xiaohua", 140);
	}
}

/**
 * 
 */
package com.jd.frameworks.simple.test.inject;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jd.frameworks.processframework.invoke.ProcessInvoker;
import com.jd.frameworks.processframework.request.ApplicationRequest;
import com.jd.frameworks.processframework.request.Request;

/**
 * @author luolishu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-main.xml" })
public class InjectTest {
	@Resource(name = "injectTestService")
	ProcessInvoker injectTestService;
	@Test
	public void testInject(){
		Request request=new ApplicationRequest();/*
		request.addParameter("a", 123);*/
		injectTestService.invoke(request);
	}
}

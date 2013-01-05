/**
 * 
 */
package com.opensource.frameworks.simple.test.validate;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.frameworks.processframework.invoke.ProcessInvoker;
import com.opensource.frameworks.processframework.request.ApplicationRequest;
import com.opensource.frameworks.processframework.request.Request;
import com.opensource.frameworks.processframework.result.Result;

/**
 * @author luolishu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-main.xml" })
public class ProcessValidateTest {
	@Resource(name = "validateTestService")
	ProcessInvoker validateTestService;
	@Test
	public void testValidate(){
		Request request=new ApplicationRequest();
		request.addParameter("foo", "abcdefg");
		Result result=validateTestService.invoke(request);
		System.out.println(result);
	}

}

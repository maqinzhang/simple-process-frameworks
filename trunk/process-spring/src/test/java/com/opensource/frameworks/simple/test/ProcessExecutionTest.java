/**
 * 
 */
package com.opensource.frameworks.simple.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.opensource.frameworks.processframework.invoke.ProcessInvoker;

/**
 * @author luolishu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-main.xml" })
public class ProcessExecutionTest {
	@Resource(name = "simpleTestService")
	ProcessInvoker simpleTestService;
	@Test
	public void testValidation(){
		simpleTestService.invoke(null);
	}

}

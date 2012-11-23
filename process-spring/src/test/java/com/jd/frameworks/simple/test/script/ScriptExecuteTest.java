/**
 * 
 */
package com.jd.frameworks.simple.test.script;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jd.frameworks.processframework.invoke.ProcessInvoker;
import com.jd.frameworks.processframework.request.ApplicationRequest;
import com.jd.frameworks.processframework.request.Request;
import com.jd.frameworks.processframework.result.Result;

/**
 * @author luolishu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-main.xml" })
public class ScriptExecuteTest {
	@Resource(name = "scriptSimpleService")
	ProcessInvoker scriptSimpleService;
	@Resource(name = "scriptConfigErrorService")
	ProcessInvoker scriptConfigErrorService;
	
	@Test
	public void testScriptLogic(){
		Request request=new ApplicationRequest();
		request.addParameter("foo", "abcdefg");
		Result result=scriptSimpleService.invoke(request);
		System.out.println(result);
	}
	
	@Test
	public void testScriptConfigErrorLogic(){
		Request request=new ApplicationRequest();
		request.addParameter("foo", "abcdefg");
		Result result=scriptConfigErrorService.invoke(request);
		System.out.println(result);
	}

}

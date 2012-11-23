package com.jd.frameworks.spring.test;

import java.util.Date;

import javax.annotation.Resource;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jd.frameworks.processframework.invoke.ProcessInvoker;
import com.jd.frameworks.processframework.request.ApplicationRequest;
import com.jd.frameworks.processframework.request.Request;

/**
 * import javax.annotation.Resource;
 * 
 * /**
 * 
 * @author luolishu
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-main.xml" })
public class ConfigurationTest implements ApplicationContextAware {
	ApplicationContext applicationContext;

	@Resource(name = "invokerService")
	ProcessInvoker invokerService;

	@Resource(name = "invokerService2")
	ProcessInvoker invokerService2;
	@Resource(name = "invokerNoTryService")
	ProcessInvoker invokerNoTryService;
	
	
	@Resource(name = "invokerFinalService")
	ProcessInvoker invokerFinalService;

	@Test
	public void testBean() {
		System.out.println(applicationContext
				.getBeansOfType(ProcessInvoker.class));
		for (String item : applicationContext.getBeanDefinitionNames()){
			System.out.println(item);
		}
		invokerNoTryService.invoke(null);
	}
	
	@Test
	public void testInvokeBean() { 
		Request request=new ApplicationRequest();
		request.addParameter("hello", "world!");
		request.addParameter("date", new Date());
		invokerNoTryService.invoke(request);
	}
	@Test
	public void testInvokeFinalBean() { 
		Request request=new ApplicationRequest();
		request.addParameter("hello", "world!");
		request.addParameter("date", new Date());
		invokerFinalService.invoke(request);
	}
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}

}

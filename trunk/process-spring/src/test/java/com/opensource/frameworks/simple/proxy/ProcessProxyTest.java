/**
 * 
 */
package com.opensource.frameworks.simple.proxy;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.ReflectionUtils;
 

/**
 * @author luolishu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-test-main.xml" })
public class ProcessProxyTest implements ApplicationContextAware{
	ApplicationContext applicationContext;
	@Resource 
	ProxyInterface proxyInterface;
	/*
	@Resource 
	ProxyInterface2 proxyInterface2;*/
	@Test
	public void testProxy(){
		System.out.println(applicationContext.getBeansOfType(ProxyInterface.class));
		System.out.println(applicationContext.getBeansOfType(ProxyInterface2.class));
		proxyInterface.hello();
	}
	@Test
	public void testReflection(){
		Method[] methods=ReflectionUtils.getAllDeclaredMethods(ProxyInterface.class);
		proxyInterface.hello("Lishuluo",20);
		for(Method method:methods){
			System.out.println(method.getName());
		}
		 
	}


	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
		
	}

}

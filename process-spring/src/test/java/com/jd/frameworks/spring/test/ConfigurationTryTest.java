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
public class ConfigurationTryTest implements ApplicationContextAware {
	ApplicationContext applicationContext;

	@Resource(name = "invokerFinalService")
	ProcessInvoker invokerFinalService;

	@Test
	public void testInvokeFinalBean() {
		Request request = new ApplicationRequest();
		request.addParameter("hello", "world!");
		request.addParameter("date", new Date());
		invokerFinalService.invoke(request);
	}

	public void test() { 
			/*Object value = null;
			DelegatingExecuteContext delegatingContext = null;
			try {
				delegatingContext = createContext($1);
				SubProcess mainProcess = processHolder.getMainProcess();
				value = this.invokeMainProcess(mainProcess, delegatingContext);
				if (value != null && value instanceof Result) {
					return (Result) value;
				}
			} catch (Exception e) {
				value = exceptionHandler.handle(e);
			} finally {
				SubProcess finalProcess = processHolder.getMainProcess();
				value = this.invokeFinallyProcess(finalProcess,
						delegatingContext);
			}
			if (value != null && value instanceof Result) {
				return (Result) value;
			}
			return delegatingContext.result; */
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;

	}

}

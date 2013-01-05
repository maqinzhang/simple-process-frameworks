/**
 * 
 */
package com.opensource.frameworks.processframework.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author luolishu
 *
 */
public class ProcessNamespaceHandler extends NamespaceHandlerSupport implements ApplicationContextAware {
	ApplicationContext applicationContext;
	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.xml.NamespaceHandler#init()
	 */
	@Override
	public void init() {
		registerBeanDefinitionParser("process-config", new ProcessScanBeanDefinitionParser());

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
		
	}

}

package com.simple.frameworks.processframework.spring.factory;
 
import org.springframework.beans.factory.BeanFactory; 

import com.simple.frameworks.processframework.config.NodeConfig;
import com.simple.frameworks.processframework.factory.DefaultObjectFactory;
import com.simple.frameworks.processframework.utils.Assert;

/**
 * @author luolishu
 * 
 */
public class SpringObjectFactory extends DefaultObjectFactory {
	private BeanFactory beanFactory;

	public SpringObjectFactory() {
	}

	public SpringObjectFactory(BeanFactory applicationContext) {
		this.beanFactory = applicationContext;
	}

	public Object buildNode(NodeConfig config) {
 		Object bean = null;
		if (beanFactory.containsBean(config.getId())) {
			bean = beanFactory.getBean(config.getId());
		}
		if (bean == null
				&& beanFactory.containsBean(config.getClassName())) {
			bean = beanFactory.getBean(config.getClassName());
		}
		if (bean == null) {
			bean = super.buildNode(config);
		}
		Assert.notNull(bean,
				"Node Instance is null,No Node Implement found!Please check your Process configuration!\nNode config="+config); 
		
		/*applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);*/
		return bean;
	}
}

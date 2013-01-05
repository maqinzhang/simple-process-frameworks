/**
 * 
 */
package com.opensource.frameworks.processframework.spring.config;
 
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext; 
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.ConfigurationManager;
import com.opensource.frameworks.processframework.config.ProcessConfig;
import com.opensource.frameworks.processframework.exception.DefaultExceptionHandler;
import com.opensource.frameworks.processframework.exception.ExceptionHandler;
import com.opensource.frameworks.processframework.factory.InvocationBuilder;
import com.opensource.frameworks.processframework.factory.NodeInvocationFactory;
import com.opensource.frameworks.processframework.factory.ObjectFactory;
import com.opensource.frameworks.processframework.invocation.ExecutionNode;
import com.opensource.frameworks.processframework.invoke.ClassGenerator;
import com.opensource.frameworks.processframework.invoke.JavassistClassGenerator;
import com.opensource.frameworks.processframework.invoke.ProcessHolder;
import com.opensource.frameworks.processframework.spring.factory.SpringObjectFactory;
import com.opensource.frameworks.processframework.utils.StringUtils;

/**
 * @author luolishu
 * 
 */
public class ProcessScanBeanDefinitionParser implements BeanDefinitionParser,
		InitializingBean {
	static final String CONFIG_LOCATION_DELIMITERS = ",; \t\n";
	private ClassGenerator classGenerator = new JavassistClassGenerator();
	private ExceptionHandler exceptionHandler = new DefaultExceptionHandler();
	private ResourcePatternResolver resourcePatternResolver;
	private ObjectFactory objectFactory = null;

	private static final Set<ProcessHolder> holders = new LinkedHashSet<ProcessHolder>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.xml.BeanDefinitionParser#parse(org.
	 * w3c.dom.Element, org.springframework.beans.factory.xml.ParserContext)
	 */
	@Override
	public synchronized BeanDefinition parse(Element element, ParserContext parserContext) {
		String content = element.getAttribute("locations");
		String locations[] = StringUtils.tokenizeToStringArray(content,
				CONFIG_LOCATION_DELIMITERS);
		resourcePatternResolver = new PathMatchingResourcePatternResolver();
		SpringDefaultConfigurationManager configurationManager = new SpringDefaultConfigurationManager(
				locations, resourcePatternResolver);
		configurationManager.init();

		Set<ProcessConfig> processConfigs = configurationManager
				.getAllProcessConfig();
		BeanFactory applicationContext = (BeanFactory) parserContext
				.getRegistry();
		objectFactory = new SpringObjectFactory(applicationContext);
		configurationManager.setObjectFactory(objectFactory);
		for (ProcessConfig config : processConfigs) {
			ProcessHolder processHolder = objectFactory
					.buildProcessHolder(config);
			Class claz = classGenerator.generateClass(processHolder,
					exceptionHandler);
			BeanDefinition beanDefinition = new RootBeanDefinition(claz);
			beanDefinition.getPropertyValues().add("processHolder",
					processHolder);
			if (StringUtils.isNotBlank(processHolder.getExceptionHandlerId())) {
				beanDefinition.getPropertyValues().add(
						"exceptionHandler",
						new RuntimeBeanReference(processHolder
								.getExceptionHandlerId()));
			} else {
				beanDefinition.getPropertyValues().add("exceptionHandler",
						exceptionHandler);
			}
			beanDefinition.getPropertyValues().add("invocation",
					InvocationBuilder.build(config));
			parserContext.getRegistry().registerBeanDefinition(
					processHolder.getId(), beanDefinition);
			holders.add(processHolder);
		}
		BeanDefinition beanDefinition = new RootBeanDefinition(this.getClass());
		parserContext.getRegistry().registerBeanDefinition(
				"processScanBeanDefinitionParser", beanDefinition);
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		for (ExecutionNode node : NodeInvocationFactory.nodes) {
			node.init();
		}

	}

}

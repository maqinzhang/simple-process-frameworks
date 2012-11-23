/**
 * 
 */
package com.jd.frameworks.processframework.spring.config;
 
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

import com.jd.frameworks.processframework.config.ConfigurationManager;
import com.jd.frameworks.processframework.config.ProcessConfig;
import com.jd.frameworks.processframework.exception.DefaultExceptionHandler;
import com.jd.frameworks.processframework.exception.ExceptionHandler;
import com.jd.frameworks.processframework.factory.ObjectFactory;
import com.jd.frameworks.processframework.invoke.ClassGenerator;
import com.jd.frameworks.processframework.invoke.ExecutionNode;
import com.jd.frameworks.processframework.invoke.JavassistClassGenerator;
import com.jd.frameworks.processframework.invoke.ProcessHolder;
import com.jd.frameworks.processframework.invoke.SubProcess;
import com.jd.frameworks.processframework.spring.factory.SpringObjectFactory;
import com.jd.frameworks.processframework.utils.StringUtils;

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
		ConfigurationManager configurationManager = new SpringDefaultConfigurationManager(
				locations, resourcePatternResolver);
		configurationManager.init();

		Set<ProcessConfig> processConfigs = configurationManager
				.getAllProcessConfig();
		BeanFactory applicationContext = (BeanFactory) parserContext
				.getRegistry();
		objectFactory = new SpringObjectFactory(applicationContext);
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

		for (ProcessHolder holder : holders) {
			if (holder.getMainProcess() != null) {
				if (holder.getMainProcess().getNodes() != null) {
					for (ExecutionNode node : holder.getMainProcess()
							.getNodes()) {
						node.init();
					}
				}

			}
			if (holder.getFinallyProcess() != null) {
				if (holder.getFinallyProcess().getNodes() != null) {
					for (ExecutionNode node :holder.getFinallyProcess().getNodes()) {
						node.init();
					}
				}
			}
			if (holder.getCatchProcess() != null) {
				for(SubProcess p:holder.getCatchProcess() )
				if (p.getNodes() != null) {
					for (ExecutionNode node : p.getNodes()) {
						node.init();
					}
				}
			}
		}

	}

}

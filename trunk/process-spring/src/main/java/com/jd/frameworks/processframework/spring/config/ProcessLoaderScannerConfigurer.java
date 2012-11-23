/**
 * 
 */
package com.jd.frameworks.processframework.spring.config;

import static org.springframework.util.Assert.notNull;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.util.StringUtils;

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

/**
 * @author luolishu
 * 
 */
public class ProcessLoaderScannerConfigurer implements
		BeanDefinitionRegistryPostProcessor, InitializingBean,
		ApplicationContextAware, BeanNameAware {
	private final String basePackage = "com.jd.frameworks.processframework.invoke";
	private String beanName;
	private String locations[];
	
	private ApplicationContext applicationContext;
	private ObjectFactory objectFactory=null;
	private ClassGenerator classGenerator=new JavassistClassGenerator();
	private ExceptionHandler exceptionHandler=new DefaultExceptionHandler();
	private static final Set<ProcessHolder> holders = new LinkedHashSet<ProcessHolder>();
	
	public void setLocations(String[] locations) {
		this.locations = locations;
	}

	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	public void setClassGenerator(ClassGenerator classGenerator) {
		this.classGenerator = classGenerator;
	}

	public void setExceptionHandler(ExceptionHandler exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}

	public void setBeanName(String name) {
		this.beanName = name;
	}

	/**
	 * {@inheritDoc}
	 */
	public void afterPropertiesSet() throws Exception {
		notNull(this.basePackage, "Property 'basePackage' is required");
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

	/**
	 * {@inheritDoc}
	 */
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) {
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		this.objectFactory=new SpringObjectFactory(applicationContext);
	}

	/**
	 * {@inheritDoc}
	 */
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry beanDefinitionRegistry)
			throws BeansException {

		Scanner scanner = new Scanner(beanDefinitionRegistry);
		scanner.setResourceLoader(this.applicationContext);
		scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage,
				ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
	}

	private final class Scanner extends ClassPathBeanDefinitionScanner {

		public Scanner(BeanDefinitionRegistry registry) {
			super(registry);
		}

		@Override
		protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
			Set<BeanDefinitionHolder> definitionHolder = new HashSet<BeanDefinitionHolder>();

			ConfigurationManager configurationManager = new SpringDefaultConfigurationManager(
					locations,applicationContext);
			configurationManager.init();
			Set<ProcessConfig> processConfigs = configurationManager
					.getAllProcessConfig();
			
			for(ProcessConfig config:processConfigs){
				ProcessHolder processHolder=objectFactory.buildProcessHolder(config);
				Class claz=classGenerator.generateClass(processHolder, exceptionHandler);
				BeanDefinition beanDefinition=new RootBeanDefinition(claz);
				beanDefinition.getPropertyValues().add("processHolder", processHolder);
				if(StringUtils.hasLength(StringUtils.trimAllWhitespace(processHolder.getExceptionHandlerId()))){
					beanDefinition.getPropertyValues().add("exceptionHandler", new RuntimeBeanReference(processHolder.getExceptionHandlerId())); 
				}else{
					beanDefinition.getPropertyValues().add("exceptionHandler", exceptionHandler); 
				}
				BeanDefinitionHolder beanHolder=new BeanDefinitionHolder(beanDefinition,processHolder.getId());	
				this.registerBeanDefinition(beanHolder, this.getRegistry());
				definitionHolder.add(beanHolder);
				holders.add(processHolder);
			}
			BeanDefinition beanDefinition = new RootBeanDefinition(ProcessLoaderScannerConfigurer.class);
			BeanDefinitionHolder beanHolder=new BeanDefinitionHolder(beanDefinition,"processLoaderScannerConfigurer");	
			this.registerBeanDefinition(beanHolder, this.getRegistry());
			return definitionHolder;
		}

	}
}

/**
 * 
 */
package com.opensource.frameworks.processframework.spring.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.ConfigurationException;
import com.opensource.frameworks.processframework.config.DefaultConfigurationManager;
import com.opensource.frameworks.processframework.config.ProcessConfig;
import com.opensource.frameworks.processframework.factory.ObjectFactory;
import com.opensource.frameworks.processframework.spring.converter.SpringTypeConverter;
import com.opensource.frameworks.processframework.utils.StringUtils;
import com.opensource.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 * 
 */
public class SpringDefaultConfigurationManager extends
		DefaultConfigurationManager {
	ResourcePatternResolver resourceLoader;
	ObjectFactory objectFactory;
	static final Set<String> parsedResourceSet = Collections
			.synchronizedSet(new HashSet<String>());

	public SpringDefaultConfigurationManager(String[] locations) {
		this(locations,null);		
	}

	public SpringDefaultConfigurationManager(String[] locations,
			ResourcePatternResolver resourceLoader) {
		super(locations);
		this.resourceLoader = resourceLoader;
		SpringTypeConverter converter=new SpringTypeConverter();
		this.setInjectTypeConverter(converter);
	}
	@Override
	public ObjectFactory getObjectFactory() {
		return objectFactory;
	} 
	public void setObjectFactory(ObjectFactory objectFactory) {
		this.objectFactory = objectFactory;
	}

	@Override
	public synchronized void init() {
		for (String location : locations) {
			Resource[] resources = null;
			try {
				resources = this.resourceLoader.getResources(location);
				if (resources == null) {
					continue;
				}
			} catch (Exception e) {
				throw new ConfigurationException(e);
			}

			for (Resource resource : resources) {
				String url = null;
				try {
					url = resource.getURL().getPath();
					if (parsedResourceSet.contains(url)) {
						continue;
					}
					parsedResourceSet.add(url);
					Document document = this.parseDocument(resource
							.getInputStream());

					List<Element> elements = DomUtils.getChildElements(document
							.getDocumentElement());
					for (Element e : elements) {
						if (e.getTagName().equals("process")) {
							this.parseProcess(e);
							continue;
						}
						if (e.getTagName().equals("import")) {
							this.parseImport(e);
							continue;
						}
					}
				} catch (Exception e) {
					throw new ConfigurationException("resource path=" + url, e);
				}
			}

		}

	}

	private void parseProcess(Element e) {
		ProcessConfig processConfig = processParser.parse(e);
		if (processConfig != null) {
			if (containerMap.containsKey(processConfig.getId())) {
				throw new ConfigurationException(
						"Duplicate process config Exception!process id="
								+ processConfig.getId());
			}
			containerMap.put(processConfig.getId(), processConfig);
		}
	}

	private void parseImport(Element importEl) throws Exception {
		String location = importEl.getAttribute("resource");
		if (StringUtils.isBlank(location)) {
			return;
		}
		Resource[] resources = this.resourceLoader.getResources(location);
		if (resources == null) {
			return;
		}
		for (Resource resource : resources) {
			String url = null;
			try {
				url = resource.getURL().getPath();
				if (parsedResourceSet.contains(url)) {
					continue;
				}
				parsedResourceSet.add(url);
				Document document = this.parseDocument(resource
						.getInputStream());

				List<Element> elements = DomUtils.getChildElements(document
						.getDocumentElement());
				for (Element e : elements) {
					if (e.getTagName().equals("process")) {
						this.parseProcess(e);
						continue;
					}
					if (e.getTagName().equals("import")) {
						this.parseImport(e);
						continue;
					}
				}
			} catch (Exception e) {
				throw new ConfigurationException("resource path=" + url, e);
			}
		}
	}
	
}

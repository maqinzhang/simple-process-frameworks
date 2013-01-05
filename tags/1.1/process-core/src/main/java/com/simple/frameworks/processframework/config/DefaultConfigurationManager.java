/**
 * 
 */
package com.simple.frameworks.processframework.config;
 
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.simple.frameworks.processframework.converter.DefaultInjectTypeConverter;
import com.simple.frameworks.processframework.converter.InjectTypeConverter;
import com.simple.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 *
 */
public class DefaultConfigurationManager implements ConfigurationManager {
	protected String locations[];
	protected ProcessParser processParser=new XmlProcessParser();
	protected Map<String,ProcessConfig> containerMap=new LinkedHashMap<String,ProcessConfig>();
	protected InjectTypeConverter injectTypeConverter=new DefaultInjectTypeConverter();
	public static ConfigurationManager configurationManager;
	public DefaultConfigurationManager(String locations[]){
		this.locations=locations;
		configurationManager=this;
	} 
	
	/* (non-Javadoc)
	 * @see com.simple.frameworks.processframework.config.ConfigurationManager#getAllProcessConfig()
	 */
	@Override
	public Set<ProcessConfig> getAllProcessConfig() { 
		return new LinkedHashSet<ProcessConfig>(containerMap.values());
	}


	@Override
	public synchronized void init() {
		for(String location:locations){
			try {
				Document document=this.parseDocument(new FileInputStream(location));
				List<Element> elements=DomUtils.getChildElements(document.getDocumentElement());
				for(Element e:elements){
					ProcessConfig processConfig=processParser.parse(e);
					if(processConfig!=null){
						if(containerMap.containsKey(processConfig.getId())){
							throw new ConfigurationException("Duplicate process config Exception!process id="+processConfig.getId()+" configuration location="+location);
						}
						containerMap.put(processConfig.getId(), processConfig);
					}
				} 
			} catch (Exception e) {
				throw new ConfigurationException(e);
			}
		}
		
	}
	
	public static ConfigurationManager getInstance(){
		return configurationManager;
	}
	
	protected Document parseDocument(InputStream inputStream) throws Exception{
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		DocumentBuilder builder=factory.newDocumentBuilder();
		Document document=builder.parse(inputStream);
		return document;
	}

	public InjectTypeConverter getInjectTypeConverter() {
		return injectTypeConverter;
	}

	public void setInjectTypeConverter(InjectTypeConverter injectTypeConverter) {
		this.injectTypeConverter = injectTypeConverter;
	}

}

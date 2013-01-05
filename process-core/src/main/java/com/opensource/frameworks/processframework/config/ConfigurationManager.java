/**
 * 
 */
package com.opensource.frameworks.processframework.config;

 
import java.util.Set;

import com.opensource.frameworks.processframework.converter.InjectTypeConverter;
import com.opensource.frameworks.processframework.factory.ObjectFactory;

/**
 * @author luolishu
 *
 */
public interface ConfigurationManager {
	
	public Set<ProcessConfig> getAllProcessConfig();
	
	public InjectTypeConverter getInjectTypeConverter();
	
	public void init();
	
	public ObjectFactory getObjectFactory();

}

/**
 * 
 */
package com.jd.frameworks.processframework.config;

 
import java.util.Set;

import com.jd.frameworks.processframework.converter.InjectTypeConverter;

/**
 * @author luolishu
 *
 */
public interface ConfigurationManager {
	
	public Set<ProcessConfig> getAllProcessConfig();
	
	public InjectTypeConverter getInjectTypeConverter();
	
	public void init();

}

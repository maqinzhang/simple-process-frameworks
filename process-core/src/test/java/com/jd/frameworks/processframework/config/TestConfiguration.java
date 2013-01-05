/**
 * 
 */
package com.jd.frameworks.processframework.config;
 

import org.junit.Test; 

import com.opensource.frameworks.processframework.config.ConfigurationManager;
import com.opensource.frameworks.processframework.config.DefaultConfigurationManager;
 
 

/**
 * @author luolishu
 *
 */
public class TestConfiguration {
	
    @Test
	public void testParser() throws Exception{  
		ConfigurationManager configurationManager=new DefaultConfigurationManager(new String[]{"D:/workspace_opensource/business_framework/ProcessFramework/process-core/src/main/resources/process-sample.xml"});
		configurationManager.init();
		System.out.println(configurationManager.getAllProcessConfig());
	}
}

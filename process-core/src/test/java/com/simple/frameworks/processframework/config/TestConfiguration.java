/**
 * 
 */
package com.simple.frameworks.processframework.config;
 

import org.junit.Test; 

import com.simple.frameworks.processframework.config.ConfigurationManager;
import com.simple.frameworks.processframework.config.DefaultConfigurationManager;
import com.simple.frameworks.processframework.config.ProcessParser;
import com.simple.frameworks.processframework.config.XmlProcessParser;
 

/**
 * @author luolishu
 *
 */
public class TestConfiguration {
	
    @Test
	public void testParser() throws Exception{ 
		ProcessParser parser=new XmlProcessParser();
		ConfigurationManager configurationManager=new DefaultConfigurationManager(new String[]{"D:/workspace_opensource/business_framework/ProcessFramework/process-core/src/main/resources/process-sample.xml"});
		configurationManager.init();
		System.out.println(configurationManager.getAllProcessConfig());
	}
}

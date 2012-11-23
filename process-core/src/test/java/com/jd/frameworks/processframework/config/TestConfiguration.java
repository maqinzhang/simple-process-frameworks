/**
 * 
 */
package com.jd.frameworks.processframework.config;
 

import org.junit.Test; 
 

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
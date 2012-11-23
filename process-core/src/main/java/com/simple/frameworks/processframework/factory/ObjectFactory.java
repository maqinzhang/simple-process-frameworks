/**
 * 
 */
package com.simple.frameworks.processframework.factory;

import com.simple.frameworks.processframework.config.NodeConfig;
import com.simple.frameworks.processframework.config.ProcessConfig;
import com.simple.frameworks.processframework.invoke.ProcessHolder;

/**
 * @author luolishu
 *
 */
public interface ObjectFactory {
	
	public Object buildNode(NodeConfig config); 
	
	public ProcessHolder buildProcessHolder(ProcessConfig config);

}

/**
 * 
 */
package com.opensource.frameworks.processframework.factory;

import com.opensource.frameworks.processframework.config.NodeConfig;
import com.opensource.frameworks.processframework.config.ProcessConfig;
import com.opensource.frameworks.processframework.invoke.ProcessHolder;

/**
 * @author luolishu
 *
 */
public interface ObjectFactory {
	
	public Object buildNode(NodeConfig config); 
	
	public ProcessHolder buildProcessHolder(ProcessConfig config);

}

/**
 * 
 */
package com.jd.frameworks.processframework.factory;

import com.jd.frameworks.processframework.config.NodeConfig;
import com.jd.frameworks.processframework.config.ProcessConfig;
import com.jd.frameworks.processframework.invoke.ProcessHolder;

/**
 * @author luolishu
 *
 */
public interface ObjectFactory {
	
	public Object buildNode(NodeConfig config); 
	
	public ProcessHolder buildProcessHolder(ProcessConfig config);

}

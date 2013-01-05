/**
 * 
 */
package com.opensource.frameworks.processframework.config.parser;

import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.NodeConfig;
import com.opensource.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 *
 */
public class NodeTagParser implements TagParser<NodeConfig> {
 
	@Override
	public NodeConfig parse(Element e) {
		NodeConfig node=new NodeConfig();
		node.setId(DomUtils.getAttribuite(e, "id"));
		node.setClassName(DomUtils.getAttribuite(e, "class"));
		node.setScope(DomUtils.getAttribuite(e, "scope"));
		return node;
	}

}

/**
 * 
 */
package com.jd.frameworks.processframework.config;

import org.w3c.dom.Element;

import com.jd.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 *
 */
public class XmlNodeParser implements NodeParser {

	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.config.NodeParser#parser(org.w3c.dom.Element)
	 */
	@Override
	public NodeConfig parser(Element e) {
		NodeConfig node=new NodeConfig();
		node.setId(DomUtils.getAttribuite(e, "id"));
		node.setClassName(DomUtils.getAttribuite(e, "class"));
		node.setScope(DomUtils.getAttribuite(e, "scope"));
		return node;
	}

}

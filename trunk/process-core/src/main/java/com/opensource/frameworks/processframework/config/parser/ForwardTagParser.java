/**
 * 
 */
package com.opensource.frameworks.processframework.config.parser;

import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.ForwardConfig;
import com.opensource.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 * 
 */
public class ForwardTagParser implements TagParser<ForwardConfig> {

	@Override
	public ForwardConfig parse(Element e) {
		ForwardConfig node = new ForwardConfig();
		node.setProcess(DomUtils.getAttribuite(e, "process"));
		return node;
	}

}

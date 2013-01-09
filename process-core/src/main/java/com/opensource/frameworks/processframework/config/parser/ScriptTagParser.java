/**
 * 
 */
package com.opensource.frameworks.processframework.config.parser;

import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.ScriptConfig;
import com.opensource.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 * 
 */
public class ScriptTagParser implements TagParser<ScriptConfig> {

	@Override
	public ScriptConfig parse(Element e) {
		ScriptConfig node = new ScriptConfig();
		node.setLanguage(DomUtils.getAttribuite(e, "language"));
		node.setScript(DomUtils.getTextValue(e));
		return node;
	}

}

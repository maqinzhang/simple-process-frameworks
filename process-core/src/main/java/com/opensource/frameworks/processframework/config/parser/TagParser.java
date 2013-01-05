package com.opensource.frameworks.processframework.config.parser;

import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.ElementConfig;

/**
 * @author luolishu
 * 
 */
public interface TagParser<T extends ElementConfig> {
	T parse(Element e);
}

/**
 * 
 */
package com.jd.frameworks.processframework.config;

import org.w3c.dom.Element;

/**
 * @author luolishu
 *
 */
public interface ProcessParser {
	ProcessConfig parse(Element e);
}

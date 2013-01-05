/**
 * 
 */
package com.opensource.frameworks.processframework.config.parser;

import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.CatchTagConfig;
import com.opensource.frameworks.processframework.config.ConditionsTagConfig;
import com.opensource.frameworks.processframework.config.ElementConfig;
import com.opensource.frameworks.processframework.config.ElseIfTagConfig;
import com.opensource.frameworks.processframework.config.ElseTagConfig;
import com.opensource.frameworks.processframework.config.ExceptionConfig;
import com.opensource.frameworks.processframework.config.FinallyTagConfig;

/**
 * @author luolishu
 *
 */
public class ElseIfTagParser extends BaseTagParser<ElseIfTagConfig>{

	@Override
	protected ElseIfTagConfig doParse(Element e,List<Element> childElements,
			ElseIfTagConfig config) {
		Iterator<Element> iterator = childElements.iterator();
		String test = e.getAttribute("test");
		config.setTest(test);
		ElementConfig lastConfig = null;
		while (iterator.hasNext()) {
			Element childEl = iterator.next();
			TagParser parser = TagParserFactory.getParser(childEl);
			ElementConfig childConfig = parser.parse(childEl);
			if (lastConfig instanceof ExceptionConfig) {
				ExceptionConfig exception = (ExceptionConfig) lastConfig;
				if (childConfig instanceof CatchTagConfig) {
					exception.addCatch((CatchTagConfig) childConfig);
				}else if (childConfig instanceof FinallyTagConfig) {
					exception.setFinallyConfig((FinallyTagConfig) childConfig);
				}else {
					config.addChild(childConfig);
					lastConfig = childConfig;
				}

			} else if (lastConfig instanceof ConditionsTagConfig) {
				ConditionsTagConfig conditions = (ConditionsTagConfig) lastConfig;
				if (childConfig instanceof ElseIfTagConfig) {
					conditions.addElseIf((ElseIfTagConfig) childConfig);
				} else if (childConfig instanceof ElseTagConfig) {
					conditions.setElseTags((ElseTagConfig) childConfig);
				} else {
					config.addChild(childConfig);
					lastConfig = childConfig;
				}
			} else {
				config.addChild(childConfig);
				lastConfig = childConfig;
			}
		}
		return config;
	}

	@Override
	protected ElseIfTagConfig createObject() { 
		return new ElseIfTagConfig();
	}

	 
}

/**
 * 
 */
package com.opensource.frameworks.processframework.config.parser;

import java.util.List;

import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.ConditionsTagConfig;
import com.opensource.frameworks.processframework.config.ElseTagConfig;

/**
 * @author luolishu
 *
 */
public class ChooseTagParser  extends BaseTagParser<ConditionsTagConfig>{

	@Override
	protected ConditionsTagConfig doParse(Element e,List<Element> childElements,
			ConditionsTagConfig config) {
		for(Element child:childElements){
			if("when".equalsIgnoreCase(child.getTagName())){
				config.addChild(TagParserFactory.getParser(child).parse(child));
			}else if("otherwise".equalsIgnoreCase(child.getTagName())){
				config.setElseTags((ElseTagConfig) TagParserFactory.getParser(child).parse(child));
			}
		}
		return config;
	}

	@Override
	protected ConditionsTagConfig createObject() { 
		return new ConditionsTagConfig();
	}

	 

}

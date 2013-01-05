/**
 * 
 */
package com.opensource.frameworks.processframework.config.parser;

import java.util.List;

import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.ElementConfig;
import com.opensource.frameworks.processframework.utils.Assert;
import com.opensource.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 *
 */
public abstract class BaseTagParser<T extends ElementConfig>  implements TagParser<T>  {

	/* (non-Javadoc)
	 * @see com.jd.frameworks.processframework.config.parser.TagParser#parse(org.w3c.dom.Element)
	 */
	@Override
	public T parse(Element e) {
		List<Element> childElements = DomUtils.getChildElements(e);
		Assert.notEmpty(childElements, "tag name="+e.getTagName());
		T config=createObject();
		this.doParse(e,childElements, config);
		return config;
	}
	
	protected abstract T doParse(Element e,List<Element> childElements,T config);
	protected abstract T createObject();

}

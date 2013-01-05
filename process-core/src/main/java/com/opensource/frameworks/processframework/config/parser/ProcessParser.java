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
import com.opensource.frameworks.processframework.config.ProcessConfig;
import com.opensource.frameworks.processframework.utils.Assert;
import com.opensource.frameworks.processframework.utils.StringUtils;
import com.opensource.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 * 
 */
public class ProcessParser implements TagParser<ProcessConfig> {

	private String INTERFACE_ATTR = "interface";

	@Override
	public ProcessConfig parse(Element e) {
		ProcessConfig processConfig = new ProcessConfig();
		processConfig.setId(DomUtils.getAttribuite(e, "id"));
		processConfig.setInterfaceClass(DomUtils.getAttribuite(e, INTERFACE_ATTR));
		if (StringUtils.isBlank(DomUtils.getAttribuite(e, "resultType"))) {
			processConfig.setResultType(DomUtils.getAttribuite(e, "resultType"));
		} else {
			processConfig.setResultType(DomUtils.getAttribuite(e, "resultType"));
		}

		List<Element> childElements = DomUtils.getChildElements(e);
		Assert.notEmpty(childElements,
				"ProcessConfig childs must not empty!process=" + processConfig);
		Iterator<Element> iterator = childElements.iterator();
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
				} else {
					processConfig.addChild(childConfig);
					lastConfig = childConfig;
				}

			} else if (lastConfig instanceof ConditionsTagConfig) {
				ConditionsTagConfig conditions = (ConditionsTagConfig) lastConfig;
				if (childConfig instanceof ElseIfTagConfig) {
					conditions.addElseIf((ElseIfTagConfig) childConfig);
				} else if (childConfig instanceof ElseTagConfig) {
					conditions.setElseTags((ElseTagConfig) childConfig);
				} else {
					processConfig.addChild(childConfig);
					lastConfig = childConfig;
				}
			} else {
				processConfig.addChild(childConfig);
				lastConfig = childConfig;
			}

		}
		return processConfig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jd.frameworks.processframework.config.ProcessParser#parser(org.w3c
	 * .dom.Element)
	 * 
	 * @Override public ProcessConfig parse(Element e) { ProcessConfig config =
	 * new ProcessConfig(); config.setId(DomUtils.getAttribuite(e, "id"));
	 * config.setInterfaceClass(DomUtils.getAttribuite(e, INTERFACE_ATTR)); if
	 * (StringUtils.isBlank(DomUtils.getAttribuite(e, "resultType"))) {
	 * config.setResultType(DomUtils.getAttribuite(e, "resultType")); } else {
	 * config.setResultType(DomUtils.getAttribuite(e, "resultType")); }
	 * 
	 * List<Element> childElements = DomUtils.getChildElements(e);
	 * Assert.notEmpty(childElements,
	 * "ProcessConfig childs must not empty!process=" + config);
	 * Iterator<Element> iterator = childElements.iterator(); ElementConfig
	 * lastConfig = null; while (iterator.hasNext()) { Element childEl =
	 * iterator.next(); TagParser parser = TagParserFactory.getParser(childEl);
	 * ElementConfig childConfig = parser.parse(childEl); if (lastConfig
	 * instanceof ExceptionConfig) { ExceptionConfig exception =
	 * (ExceptionConfig) lastConfig; if (childConfig instanceof CatchTagConfig)
	 * { exception.addCatch((CatchTagConfig) childConfig); } if (childConfig
	 * instanceof FinallyTagConfig) {
	 * exception.setFinallyConfig((FinallyTagConfig) childConfig); }else {
	 * config.addChild(childConfig); lastConfig = childConfig; }
	 * 
	 * } else if (lastConfig instanceof ConditionsTagConfig) {
	 * ConditionsTagConfig conditions = (ConditionsTagConfig) lastConfig; if
	 * (childConfig instanceof ElseIfTagConfig) {
	 * conditions.addElseIf((ElseIfTagConfig) childConfig); } else if
	 * (childConfig instanceof ElseTagConfig) {
	 * conditions.setElseTags((ElseTagConfig) childConfig); } else {
	 * config.addChild(childConfig); lastConfig = childConfig; } } else {
	 * config.addChild(childConfig); lastConfig = childConfig; }
	 * 
	 * } return config; }
	 */

}

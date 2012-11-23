/**
 * 
 */
package com.jd.frameworks.processframework.config;

import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Element;

import com.jd.frameworks.processframework.utils.StringUtils;
import com.jd.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 * 
 */
public class XmlProcessParser implements ProcessParser {
	private String TRY_TAG = "try";
	private String CATCH_TAG = "catch";
	private String FINALLY_TAG = "finally";
	private String NODE_TAG = "node";
	private String INTERFACE_ATTR = "interface";

	private NodeParser nodeParser = new XmlNodeParser();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jd.frameworks.processframework.config.ProcessParser#parser(org.w3c
	 * .dom.Element)
	 */
	@Override
	public ProcessConfig parse(Element e) {
		ProcessConfig config = new ProcessConfig();
		List<Element> tryList = DomUtils.getChildElementsByTagName(e, TRY_TAG);
		List<Element> catchList = DomUtils.getChildElementsByTagName(e,
				CATCH_TAG);
		List<Element> finallyList = DomUtils.getChildElementsByTagName(e,
				FINALLY_TAG);
		config.setId(DomUtils.getAttribuite(e, "id"));
		config.setInterfaceClass(DomUtils.getAttribuite(e, INTERFACE_ATTR));
		if (StringUtils.isBlank(DomUtils.getAttribuite(e, "resultType"))) {
			config.setResultType(DomUtils.getAttribuite(e, "resultType"));
		} else {
			config.setResultType(DomUtils.getAttribuite(e, "resultType"));
		}
		if (tryList == null || tryList.size() <= 0) {
			SubProcessConfig process = this.parseSubProccess(e);
			config.setMainProcess(process);
		} else if (tryList.size() == 1) {
			SubProcessConfig process = this.parseSubProccess(tryList.get(0));
			config.setMainProcess(process);
			if (catchList != null) {
				List<SubProcessConfig> results = new LinkedList<SubProcessConfig>();
				config.setCatchProcess(results);
				for (Element catchEl : catchList) {
					SubProcessConfig item = this.parseSubProccess(catchEl);
					if (StringUtils.isBlank(item.getException())) {
						throw new ConfigurationException(
								"Catch Exception is not blank!process config id="
										+ config.getId() + " subprocess id="
										+ item.getId());
					}
					if (item != null) {
						results.add(item);
					}
				}
			}
			if (finallyList != null && finallyList.size() > 0) {
				config.setFinalProcess(this.parseSubProccess(finallyList.get(0)));
			}
		} else {
			throw new ConfigurationException("try config error on process="
					+ config + " duplication try block=" + tryList);
		}
		return config;
	}

	private SubProcessConfig parseSubProccess(Element e) {
		List<Element> elList = DomUtils.getChildElementsByTagName(e, NODE_TAG);
		if (elList == null || elList.isEmpty()) {
			return null;
		}
		SubProcessConfig subProcessConfig = new SubProcessConfig();
		subProcessConfig.setException(DomUtils.getAttribuite(e, "exception"));
		List<NodeConfig> nodeConfigList = new LinkedList<NodeConfig>();
		for (Element nodeEl : elList) {
			NodeConfig node = nodeParser.parser(nodeEl);
			node.setSubProcessConfig(subProcessConfig);
			if (node != null) {
				nodeConfigList.add(node);
			}
		}
		subProcessConfig.setNodes(nodeConfigList);
		return subProcessConfig;
	}

}

/**
 * 
 */
package com.jd.frameworks.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.opensource.frameworks.processframework.config.DefaultConfigurationManager;
import com.opensource.frameworks.processframework.config.ElementConfig;
import com.opensource.frameworks.processframework.config.ProcessConfig;
import com.opensource.frameworks.processframework.config.parser.TagParser;
import com.opensource.frameworks.processframework.config.parser.TagParserFactory;
import com.opensource.frameworks.processframework.factory.InvocationBuilder;
import com.opensource.frameworks.processframework.factory.NodeInvocationFactory;
import com.opensource.frameworks.processframework.invocation.ExecutionNode;
import com.opensource.frameworks.processframework.invocation.Invocation;
import com.opensource.frameworks.processframework.invoke.WrapperExecuteContext;
import com.opensource.frameworks.processframework.request.ApplicationRequest;
import com.opensource.frameworks.processframework.request.DefaultSessionImpl;
import com.opensource.frameworks.processframework.utils.Assert;
import com.opensource.frameworks.processframework.utils.xml.DomUtils;

/**
 * @author luolishu
 * 
 */
public class TestParser {

	@Test
	public void testParser() throws Throwable{
		WrapperExecuteContext context= new WrapperExecuteContext();
		context.request=new ApplicationRequest();
		context.session=new DefaultSessionImpl(); 
		context.session.setAttribute("abc",2);
		new DefaultConfigurationManager(null);
		String location = "D:/jd_source/ProcessFramework/process-core/src/test/resources/process-sample.xml";
		Document document = this.parseDocument(new FileInputStream(location));
		List<Element> elements = DomUtils.getChildElements(document
				.getDocumentElement());
		for (Element e : elements) {
			TagParser parser = TagParserFactory.getParser(e);
			ElementConfig config = parser.parse(e);
			Assert.isInstanceOf(ProcessConfig.class, config);
			System.out.println(config);
			Invocation invocation=InvocationBuilder.build(config);
			for (ExecutionNode node : NodeInvocationFactory.nodes) {
				node.init();
			}
			invocation.invoke(context);
		}
	}

	protected Document parseDocument(InputStream inputStream) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(inputStream);
		return document;
	}
}

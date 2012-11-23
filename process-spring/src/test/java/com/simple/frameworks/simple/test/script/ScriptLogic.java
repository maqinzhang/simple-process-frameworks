/**
 * 
 */
package com.simple.frameworks.simple.test.script;
 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
 
import com.simple.frameworks.processframework.Script;
import com.simple.frameworks.processframework.bind.annotations.Attribute;
import com.simple.frameworks.processframework.request.Session;
import com.simple.frameworks.simple.service.SayHelloService;

/**
 * @author luolishu
 * 
 */
@Service
public class ScriptLogic {
	@Resource(name="sayHelloService")
	private SayHelloService sayHelloService;
 
	@Script
	public String scriptNoReturn(Session session) {
		System.out
				.println("==================scriptNoReturn=======================");
		session.setAttribute("var", "Hello World!");
		String script = "function hello(){session.setAttribute('key','set in javascript value');};hello();sayHelloService.sayHello('world! lishu.luo')";
		return script;
	}

	public void execute(@Attribute("key") String key) {
		System.out
				.println("==================scriptNoReturn=======================,key="
						+ key);
	}
}

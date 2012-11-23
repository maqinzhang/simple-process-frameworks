/**
 * 
 */
package com.jd.frameworks.simple.test.binding;
 
import org.springframework.stereotype.Service;

import com.jd.frameworks.processframework.Execute;
import com.jd.frameworks.processframework.bind.annotations.RequestVar;

/**
 * @author luolishu
 *
 */
@Service
public class RequestBinding { 
	public @Execute
	void result(@RequestVar("foo") String foo) {
		System.out.println("request binding,foo=" + foo);
	}
}

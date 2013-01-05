/**
 * 
 */
package com.opensource.frameworks.simple.test.binding;
 
import org.springframework.stereotype.Service;

import com.opensource.frameworks.processframework.Execute;
import com.opensource.frameworks.processframework.bind.annotations.RequestVar;

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

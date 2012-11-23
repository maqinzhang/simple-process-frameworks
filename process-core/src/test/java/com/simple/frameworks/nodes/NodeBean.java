/**
 * 
 */
package com.simple.frameworks.nodes;

import com.simple.frameworks.processframework.Validate;
import com.simple.frameworks.processframework.bind.annotations.Attribute;

/**
 * @author luolishu
 * 
 */
public class NodeBean {

	public @Validate  void validate(){
		
	}
	public void test(@Attribute Object value) {

	}

	
	public @Attribute("key") Object test2(@Attribute Object value) {
		return null;
	}

}

/**
 * 
 */
package com.simple.orders.payment.request.logic;
 
import java.util.Map;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.bind.annotations.Attribute; 
import com.simple.orders.payment.model.RequestModel;

/**
 * @author luolishu
 *
 */
@Service("buildRequestParameters")
public class BuildRequestParametersLogic {
	public boolean validate(RequestModel requestModel){
		
		return true;
	}
	@Attribute("parameters")
	public Map execute(RequestModel requestModel){
		return null;
	}

}

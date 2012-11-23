/**
 * 
 */
package com.simple.orders.payment.request.logic;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.bind.annotations.Attribute;
import com.simple.orders.payment.result.PaymentResult;
import com.simple.orders.payment.result.RequestResultModel;
import com.simple.orders.payment.result.PaymentResultCode;

/**
 * @author luolishu
 *
 */
@Service("alipayRequestSign")
public class AlipayRequstSignLogic {
	public PaymentResult validate(@Attribute("parameters")Map parameters){
		if(parameters==null){
			return new PaymentResult(PaymentResultCode.PRAMETER_CONFIG_ERROR) ;	
		}
		return null;
	}
	
	public PaymentResult execute(@Attribute("parameters")Map parameters){
		PaymentResult result=new PaymentResult(PaymentResultCode.SUCCESS);
		RequestResultModel model=new RequestResultModel();
		result.setModel(model);
		
		return result;
	}

}

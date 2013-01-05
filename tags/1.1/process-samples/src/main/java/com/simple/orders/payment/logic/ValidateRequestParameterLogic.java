/**
 * 
 */
package com.simple.orders.payment.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.result.Result;
import com.simple.orders.payment.domain.PaymentParameterDomain;
import com.simple.orders.payment.domain.ServiceProviderDomain;
import com.simple.orders.payment.result.PaymentResult;
import com.simple.orders.payment.result.PaymentResultCode;

/**
 * @author luolishu
 *
 */
@Service("validateParameters")
public class ValidateRequestParameterLogic {
	public Result validate(ServiceProviderDomain provider,List<PaymentParameterDomain> parameterMapping){
		if(provider==null ){
			return new PaymentResult(PaymentResultCode.NO_PROVIDER_ERROR) ;				
		}
		if( parameterMapping==null){
			return new PaymentResult(PaymentResultCode.PRAMETER_CONFIG_ERROR) ;				
		}
		return null;
	}

}

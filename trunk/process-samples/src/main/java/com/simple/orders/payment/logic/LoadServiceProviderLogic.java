/**
 * 
 */
package com.simple.orders.payment.logic;

import org.springframework.stereotype.Service;

import com.opensource.frameworks.processframework.bind.annotations.Attribute;
import com.opensource.frameworks.processframework.bind.annotations.RequestVar;
import com.opensource.frameworks.processframework.result.Result;
import com.opensource.frameworks.processframework.utils.StringUtils;
import com.simple.orders.payment.domain.ServiceProviderDomain;
import com.simple.orders.payment.result.PaymentResult;
import com.simple.orders.payment.result.PaymentResultCode;

/**
 * @author luolishu
 * 
 */
@Service("loadProvider")
public class LoadServiceProviderLogic {
	public Result validate(@RequestVar("providerId") String providerId) {
		if (StringUtils.isBlank(providerId)) {
			return new PaymentResult(PaymentResultCode.NO_PROVIDER_ERROR);
		}
		return null;
	}

	@Attribute("provider")
	public ServiceProviderDomain execute() {
		return null;
	}
}

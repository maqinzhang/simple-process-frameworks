/**
 * 
 */
package com.simple.orders.payment.logic;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jd.frameworks.processframework.bind.annotations.Attribute;
import com.simple.orders.payment.domain.PaymentParameterDomain;

/**
 * @author luolishu
 *
 */
@Service("loadParameters")
public class LoadPaymentParametersLogic {
    @Attribute("parameterMapping")
	public List<PaymentParameterDomain> execute(){
		return null;
	}
}

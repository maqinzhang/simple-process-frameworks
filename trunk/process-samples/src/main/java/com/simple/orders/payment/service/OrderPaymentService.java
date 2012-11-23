/**
 * 
 */
package com.simple.orders.payment.service;

import com.simple.frameworks.processframework.request.Request;
import com.simple.orders.payment.result.NotifyResultModel;
import com.simple.orders.payment.result.RequestResultModel;
import com.simple.orders.payment.result.ReturnResultModel;

/**
 * @author luolishu
 *
 */
public interface OrderPaymentService {

	public RequestResultModel requestPayment(Request request);
	
	public ReturnResultModel returnPayment(Request request);
	
	public NotifyResultModel notifyPayment(Request request);
}

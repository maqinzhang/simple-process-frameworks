/**
 * 
 */
package com.simple.orders.payment.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simple.frameworks.processframework.invoke.ProcessInvoker;
import com.simple.frameworks.processframework.request.Request;
import com.simple.frameworks.processframework.result.Result;
import com.simple.orders.payment.result.NotifyResultModel;
import com.simple.orders.payment.result.RequestResultModel;
import com.simple.orders.payment.result.ReturnResultModel;

/**
 * @author luolishu
 * 
 */
@Service("alipayPaymentService")
public class AlipayOrderPaymentService implements OrderPaymentService {
	@Resource(name="alipayRequestInvoker")
	ProcessInvoker requestInvoker;
	@Resource(name="alipayNotifyInvoker")
	ProcessInvoker notifyInvoker;
	@Resource(name="alipayReturnInvoker")
	ProcessInvoker returnInvoker;
	public RequestResultModel requestPayment(Request request) {
		RequestResultModel model = null;
		Result result=requestInvoker.invoke(request);
		if(result.getModel()!=null){
			model=(RequestResultModel) result.getModel();
		}
		return model;
	}

	@Override
	public ReturnResultModel returnPayment(Request request) {
		ReturnResultModel model = null;
		Result result=returnInvoker.invoke(request);
		if(result.getModel()!=null){
			model=(ReturnResultModel) result.getModel();
		}
		return model;
	}

	@Override
	public NotifyResultModel notifyPayment(Request request) {
		NotifyResultModel model = null;
		Result result=notifyInvoker.invoke(request);
		if(result.getModel()!=null){
			model=(NotifyResultModel) result.getModel();
		}
		return model;
	}
 
}

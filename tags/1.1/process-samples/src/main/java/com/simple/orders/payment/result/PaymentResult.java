/**
 * 
 */
package com.simple.orders.payment.result;

import com.simple.frameworks.processframework.result.Result;

/**
 * @author luolishu
 * 
 */
public class PaymentResult implements
		Result<PaymentResultCode, BaseResultModel> { 
	private static final long serialVersionUID = 6648481577085740523L;
	PaymentResultCode enumCode;
	BaseResultModel model;
	public PaymentResult(PaymentResultCode enumCode) {
		this.enumCode = enumCode;
	}

	public PaymentResultCode getEnumCode() {
		return enumCode;
	}

	public void setEnumCode(PaymentResultCode enumCode) {
		this.enumCode = enumCode;
	}

	public BaseResultModel getModel() {
		return model;
	}

	public void setModel(BaseResultModel model) {
		this.model = model;
	}

	 

}

package com.simple.orders.payment.result;

import java.io.Serializable;

public abstract class BaseResultModel  implements Serializable{
	PaymentResultCode resultCode;

	public PaymentResultCode getResultCode() {
		return resultCode;
	}

	public void setResultCode(PaymentResultCode resultCode) {
		this.resultCode = resultCode;
	}
}

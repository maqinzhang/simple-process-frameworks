/**
 * 
 */
package com.simple.orders.payment.result;

import java.io.Serializable;

/**
 * @author luolishu
 * 
 */
public class ReturnResultModel extends BaseResultModel implements Serializable {
	private String requestUrl;
	private String signedQueryString;

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public String getSignedQueryString() {
		return signedQueryString;
	}

	public void setSignedQueryString(String signedQueryString) {
		this.signedQueryString = signedQueryString;
	}

}

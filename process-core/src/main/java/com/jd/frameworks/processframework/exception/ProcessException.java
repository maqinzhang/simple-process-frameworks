package com.jd.frameworks.processframework.exception;

import java.io.Serializable;

/**
 * @author luolishu
 * 
 */
public class ProcessException extends RuntimeException { 
	private static final long serialVersionUID = 5642572558640929823L;
	Enum<?> resultCode;
	Serializable model;

	public ProcessException() {

	}

	public ProcessException(Enum<?> resultCode, Serializable model) {
		this.resultCode = resultCode;
		this.model = model;
	}

	public Enum<?> getResultCode() {
		return resultCode;
	}

	public void setResultCode(Enum<?> resultCode) {
		this.resultCode = resultCode;
	}

	public Serializable getModel() {
		return model;
	}

	public void setModel(Serializable model) {
		this.model = model;
	}
	
	

}

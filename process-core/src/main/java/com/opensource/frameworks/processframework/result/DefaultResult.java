/**
 * 
 */
package com.opensource.frameworks.processframework.result;

import java.io.Serializable;

/**
 * @author luolishu
 *
 */
public class DefaultResult<E extends Enum<?>, T extends Serializable> implements Result<E, T> {   
	private static final long serialVersionUID = -9007845844909778649L;
	T model;
    E code;
	@Override
	public E getEnumCode() { 
		return code;
	}

	@Override
	public T getModel() { 
		return model;
	}

	@Override
	public void setEnumCode(E enumCode) {
		this.code=enumCode;
		
	}

	@Override
	public void setModel(T model) {
		this.model=model;
		
	}

	@Override
	public String toString() {
		return "DefaultResult [model=" + model + ", code=" + code + "]";
	}

}

package com.jd.frameworks.processframework.result;

import java.io.Serializable;

/**
 * @author luolishu
 * 
 */
public interface Result<E extends Enum<?>, T extends Serializable> extends Serializable{
	
	public void setEnumCode(E enumCode);
	/**
	 * 返回错误码
	 * @return
	 */
	E getEnumCode();
    /**
     * 返回的模型
     * @return
     */
	T getModel();
	
	public void setModel(T model);
	
}

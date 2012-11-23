package com.simple.frameworks.processframework.result;

import java.io.Serializable;

/**
 * @author luolishu
 * 
 */
public interface Result<E extends Enum<?>, T extends Serializable> extends Serializable{
	
	public void setEnumCode(E enumCode);
	/**
	 * ���ش�����
	 * @return
	 */
	E getEnumCode();
    /**
     * ���ص�ģ��
     * @return
     */
	T getModel();
	
	public void setModel(T model);
	
}

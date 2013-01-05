/**
 * 
 */
package com.opensource.frameworks.processframework.request;

import java.util.Map;

/**
 * @author luolishu
 * 
 */
public interface Request extends java.io.Serializable{
	/**
	 * �õ�һ���
	 * @param key
	 * @return
	 */
	public Object getParameter(String key);
    /**
     * �õ����еı�
     * @return
     */
	@SuppressWarnings("rawtypes")
	public Map getParameterMap();
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,Object value);
	
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,int value);
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,long value);
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,byte value);
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,boolean value);
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,double value);
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,float value);
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,short value);
	/**
	 * ��ӱ�
	 * @param key
	 * @param value
	 */
	public void addParameter(String key,char value);

}

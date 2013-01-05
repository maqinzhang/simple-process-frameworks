/**
 * 
 */
package com.opensource.frameworks.processframework.config;

import java.util.LinkedList;
import java.util.List;

/**
 * @author luolishu
 *
 */
public class ProcessConfig implements ElementConfig{
	public String interfaceClass;
	public String id;
	public String exceptionHandlerId;
	public String  resultType;
	public final List<ElementConfig> childs=new LinkedList<ElementConfig>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getInterfaceClass() {
		return interfaceClass;
	}
	public void setInterfaceClass(String interfaceClass) {
		this.interfaceClass = interfaceClass;
	}
	public String getResultType() {
		return resultType;
	}
	public void setResultType(String resultType) {
		this.resultType = resultType;
	}
	 
	public String getExceptionHandlerId() {
		return exceptionHandlerId;
	}
	public void setExceptionHandlerId(String exceptionHandlerId) {
		this.exceptionHandlerId = exceptionHandlerId;
	}
	 
	public List<ElementConfig> getChilds() {
		return childs;
	}
	public void addChild(ElementConfig config){
		this.childs.add(config);
	}
	@Override
	public String toString() {
		return "ProcessConfig [interfaceClass=" + interfaceClass + ", id=" + id
				+ ", exceptionHandlerId=" + exceptionHandlerId
				+ ", resultType=" + resultType + ", childs=" + childs + "]";
	}
	
	
}

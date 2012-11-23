/**
 * 
 */
package com.jd.frameworks.processframework.config;

import java.util.List;

/**
 * @author luolishu
 *
 */
public class ProcessConfig {
	public String interfaceClass;
	public String id;
	public String exceptionHandlerId;
	public String  resultType;
	public SubProcessConfig mainProcess;
	public List<SubProcessConfig> catchProcess;
	public SubProcessConfig finalProcess;
	
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
	public List<SubProcessConfig> getCatchProcess() {
		return catchProcess;
	}
	public void setCatchProcess(List<SubProcessConfig> catchProcess) {
		this.catchProcess = catchProcess;
		if(catchProcess!=null){
			for(SubProcessConfig item:catchProcess){
				item.processConfig=this;
			}
		}
	}
	public SubProcessConfig getMainProcess() {
		return mainProcess;
	}
	public void setMainProcess(SubProcessConfig mainProcess) {
		if(mainProcess!=null){
			mainProcess.processConfig=this;
		}
		this.mainProcess = mainProcess;
	}
	public SubProcessConfig getFinalProcess() {
		return finalProcess;
	}
	public void setFinalProcess(SubProcessConfig finalProcess) {
		if(finalProcess!=null){
			finalProcess.processConfig=this;
		}
		this.finalProcess = finalProcess;
	}
	public String getExceptionHandlerId() {
		return exceptionHandlerId;
	}
	public void setExceptionHandlerId(String exceptionHandlerId) {
		this.exceptionHandlerId = exceptionHandlerId;
	}
	@Override
	public String toString() {
		return "ProcessConfig [id=" + id + ", mainProcess=" + mainProcess
				+ ", catchProcess=" + catchProcess + ", finalProcess="
				+ finalProcess + "]";
	}   
	
	
	
}

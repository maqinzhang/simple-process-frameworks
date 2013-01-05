/**
 * 
 */
package com.simple.orders.payment.domain;

/**
 * @author luolishu
 * 
 */
public class ServiceProviderDomain {
	private Long id;
	private String name;
	private String company;
	private String signedFields;
	private String requestUrlTest;
	private String requestUrlLive;
	private String moneyType;
	private String moneyUnit;
	private String description;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSignedFields() {
		return signedFields;
	}
	public void setSignedFields(String signedFields) {
		this.signedFields = signedFields;
	}
	public String getRequestUrlTest() {
		return requestUrlTest;
	}
	public void setRequestUrlTest(String requestUrlTest) {
		this.requestUrlTest = requestUrlTest;
	}
	public String getRequestUrlLive() {
		return requestUrlLive;
	}
	public void setRequestUrlLive(String requestUrlLive) {
		this.requestUrlLive = requestUrlLive;
	}
	public String getMoneyType() {
		return moneyType;
	}
	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	public String getMoneyUnit() {
		return moneyUnit;
	}
	public void setMoneyUnit(String moneyUnit) {
		this.moneyUnit = moneyUnit;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

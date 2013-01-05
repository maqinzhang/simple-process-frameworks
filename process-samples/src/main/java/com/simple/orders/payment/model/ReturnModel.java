/**
 * 
 */
package com.simple.orders.payment.model;

import java.math.BigDecimal;

 

/**
 * @author luolishu
 * 
 */
public class ReturnModel {
	private String serviceName;
	private String notifyTime;
	private String notifyId;
	private String notifyType;
	private String thirdpartyTradeNo;
	private String paymentType;
    private String sellerTradeNo;
    private String tradeStatus;
    
    private String goodsSubject;
    private String goodsBody;
    private String buyerId;
    private String buyerEmail;
    private String sellerId;
    private String sellerEmail;
    
    private BigDecimal payAmount;
    private BigDecimal price;
    private Integer quantity;
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getNotifyTime() {
		return notifyTime;
	}
	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}
	public String getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	public String getThirdpartyTradeNo() {
		return thirdpartyTradeNo;
	}
	public void setThirdpartyTradeNo(String thirdpartyTradeNo) {
		this.thirdpartyTradeNo = thirdpartyTradeNo;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getSellerTradeNo() {
		return sellerTradeNo;
	}
	public void setSellerTradeNo(String sellerTradeNo) {
		this.sellerTradeNo = sellerTradeNo;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getGoodsSubject() {
		return goodsSubject;
	}
	public void setGoodsSubject(String goodsSubject) {
		this.goodsSubject = goodsSubject;
	}
	public String getGoodsBody() {
		return goodsBody;
	}
	public void setGoodsBody(String goodsBody) {
		this.goodsBody = goodsBody;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getSellerEmail() {
		return sellerEmail;
	}
	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}
	public BigDecimal getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}

/**
 * 
 */
package com.simple.orders.payment.model;

import java.util.Date;

/**
 * @author luolishu
 * 
 */
public class RequestModel {
	private String signType;//签名方式
	private String bankType;//支付方式
	private String defaultBank;//默认的网银

	private String partnerId;//在第三方支付公司的商户编号

	private String returnUrl;//当前支付成功的返回请求
	private String notifyUrl;//后台异步通知请求地址
	private String goodsDetailUrl;//商品详情地址
	private String goodsSubject;//商品标题
	private String goodsBody;//商品详细内容
	
	private String sellerId;//卖家的信息
	private String sellerEmail;//买家邮箱

	private String buyerId;//买家的ID
	private String buyerName;//买家姓名
    private String buyerEmail;//买家Email	
	private String buyerAddress;//买家送货地址
	private String buyerIp;//买家的客户端IP地址
	private String orderId;//订单ID

	private String royaltyType;//提成类型
	private Date trxStartTime;//交易开始时间
	private Date trxEndTime;//交易过期时间
	private Double logisticFee;//物流费用
	private Double price;//价格
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getDefaultBank() {
		return defaultBank;
	}
	public void setDefaultBank(String defaultBank) {
		this.defaultBank = defaultBank;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getGoodsDetailUrl() {
		return goodsDetailUrl;
	}
	public void setGoodsDetailUrl(String goodsDetailUrl) {
		this.goodsDetailUrl = goodsDetailUrl;
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
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public String getBuyerIp() {
		return buyerIp;
	}
	public void setBuyerIp(String buyerIp) {
		this.buyerIp = buyerIp;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRoyaltyType() {
		return royaltyType;
	}
	public void setRoyaltyType(String royaltyType) {
		this.royaltyType = royaltyType;
	}
	public Date getTrxStartTime() {
		return trxStartTime;
	}
	public void setTrxStartTime(Date trxStartTime) {
		this.trxStartTime = trxStartTime;
	}
	public Date getTrxEndTime() {
		return trxEndTime;
	}
	public void setTrxEndTime(Date trxEndTime) {
		this.trxEndTime = trxEndTime;
	}
	public Double getLogisticFee() {
		return logisticFee;
	}
	public void setLogisticFee(Double logisticFee) {
		this.logisticFee = logisticFee;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

}

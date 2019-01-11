package org.mutils.wechat.wechatpay.core.model;

import cn.minsin.core.init.WechatPayCoreConfig;

/**
 * 用户用于发起微信退款的容器
 * 
 * @author minsin
 *
 */
public class RefundModel extends BaseWeChatPayModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3579818110826754665L;
	/**
	 * 支付时的appid
	 */
	private String appid;
	/**
	 * 商户号
	 */
	private String mch_id =WechatPayCoreConfig.wechatPayConfig.getPartnerId();
	/**
	 * 随机字符串
	 */
	private String nonce_str=String.valueOf(System.currentTimeMillis());
	/**
	 * 微信订单号
	 */
	private String transaction_id;
	/**
	 * 商户退款单号,同一退款单号多次请求 只退款一次
	 */
	private String out_refund_no;
	/**
	 * 订单金额
	 */
	private int total_fee;
	/**
	 * 退款金额
	 */
	private int refund_fee;
	/**
	 * 退款结果通知url
	 */
	private String notify_url =WechatPayCoreConfig.wechatPayConfig.getRefundUrl();
	/**
	 * 退款原因：可不填
	 */
	private String refund_desc;
	
	protected void setAppid(String appid) {
		this.appid = appid;
	}
	
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public int getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}
	public int getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(int refund_fee) {
		this.refund_fee = refund_fee;
	}
	public String getRefund_desc() {
		return refund_desc;
	}
	public void setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
	}
	public String getAppid() {
		return appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public String getNotify_url() {
		return notify_url;
	}
	
}
package org.mutils.wechat.jsapi.model;

import org.mutils.wechat.wechatpay.core.model.PayModel;

public class JsapiOrderPayModel extends PayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2340902354991037737L;
	
	public JsapiOrderPayModel() {
		super();
		//设置appid
		this.setAppid("");
	}
	
	//支付人的openid
	private String openid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
}

package org.mutils.wechat.miniprogram.model;

import org.mutils.wechat.wechatpay.core.model.PayModel;

public class MiniProgramOrderPayModel extends PayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1298640499066790288L;

	//支付人的openid
	private String openid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
}

package org.mutils.wechat.wechatpay.core.model;

public class MiniProgramOrderPayModel extends BaseWeChatPayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1298640499066790288L;


	private String openid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
}

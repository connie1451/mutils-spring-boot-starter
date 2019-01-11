package org.mutils.wechat.app.model;

import org.mutils.wechat.wechatpay.core.model.PayModel;

import cn.minsin.core.init.WechatAppConfig;

public class AppOrderPayModel extends PayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4389845287484100322L;
	
	
	public AppOrderPayModel() {
		this.setAppid(WechatAppConfig.wechatAppConfig.getAppid());
	}

}

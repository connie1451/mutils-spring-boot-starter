package cn.minsin.core.init;

import cn.minsin.core.init.core.InitConfig;

public class WechatAppConfig extends InitConfig {
	
	
	public static WechatAppConfig wechatAppConfig;
	
	//移动应用appid
	private String appid;
	
	// 移动应用appSecret
	private String appSecret;
	
	public String getAppid() {
		return appid;
	}
	
	/**
	 * 移动应用appid
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	@Override
	protected void done() {
		
	}

	@Override
	protected void showInfomation() {
		
	}

}

package cn.minsin.core.init;

import cn.minsin.core.init.core.InitConfig;

public class WechatJsapiConfig extends InitConfig {

	public static WechatJsapiConfig wechatJsapiConfig;
	
	// 公众号appid
	private String appid;

	// 公众号appSecret
	private String appSecret;
	

	public String getAppid() {
		return appid;
	}

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

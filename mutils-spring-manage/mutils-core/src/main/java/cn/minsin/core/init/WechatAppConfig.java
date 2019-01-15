package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

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
		if(StringUtil.isBlank(appid,appSecret)) {
			throw new MutilsException("The wechat-app config was initialization failed.");
		}
		wechatAppConfig = this;
	}

	@Override
	protected void showInfomation() {
		slog.info("Required for initialization appid,appSecret");
	}

}

package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class WechatMiniProgramConfig extends InitConfig {
	
	
	public static WechatMiniProgramConfig wechatMiniProgramConfig;
	
	// 小程序appid
	private String appid;
	
	// 小程序appSecret
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
		if(StringUtil.isBlank(appid,appSecret)) {
			throw new MutilsException("小程序 初始化失败,请检查配置文件是否正确. The mini program config was initialization failed.");
		}
		wechatMiniProgramConfig = this;
	}

	@Override
	protected void showInfomation() {
		slog.info("Required for initialization appid,appSecret");
	}

}

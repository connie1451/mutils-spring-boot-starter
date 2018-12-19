package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

public class MiniProgramConfig implements InitConfig {
	
	
	public static MiniProgramConfig miniProgramConfig;
	
	/**
	 * 小程序appid
	 */
	private String appid;
	
	/**
	 * 小程序 appSecret
	 */
	private String appSecret;
	
	/**
	 * 授权类型，此处只需填写 authorization_code
	 */
	private String grantType ="authorization_code";
	
	private String serverUrl ="https://api.weixin.qq.com/sns/jscode2session";

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getserverUrl() {
		return serverUrl;
	}

	public void setserverUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

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

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	@Override
	public void done() {
		if(StringUtil.isBlank(appid,appSecret)) {
			throw new MutilsException("小程序 初始化失败,请检查配置文件是否正确.");
		}
		miniProgramConfig = this;
	}

}

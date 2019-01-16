package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class GexinPushConfig extends InitConfig {
	
	//应用appid
	private  String appId;
	//应用appkey
	private  String appKey;
	
	private  String masterSecret;
	
	//服务器地址
	private  String url = "http://sdk.open.api.igexin.com/apiex.htm";

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization appId,appKey,masterSecret,url");
		if(StringUtil.isBlank(appId,appKey,masterSecret,url)) {
			throw new MutilsException("个推 初始化失败,请检查配置文件是否正确.");
		}
	}

}

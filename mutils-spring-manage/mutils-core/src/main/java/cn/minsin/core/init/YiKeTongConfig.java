package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

public class YiKeTongConfig implements InitConfig {
	public static YiKeTongConfig yiKeTongConfig;
	
	private  String corpKey;// = "";
	private  String corpSecret;// = "";
	private  String apiUrl;// = "http://api.1ketong.com:81/ykt-pool/";
	
	public String getCorpKey() {
		return corpKey;
	}
	public void setCorpKey(String corpKey) {
		this.corpKey = corpKey;
	}
	public String getCorpSecret() {
		return corpSecret;
	}
	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}
	public String getApiUrl() {
		return apiUrl;
	}
	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}
	@Override
	public void done() {
		if(StringUtil.isBlank(corpKey,corpSecret,apiUrl)) {
			throw new MutilsException("移客通初始化失败,请检查配置文件是否正确.");
		}
		yiKeTongConfig =this;
	}
}

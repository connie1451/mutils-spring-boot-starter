package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class AlipayConfig extends InitConfig {

	public static AlipayConfig alipayConfig;

	// 支付宝的APPID 需要在官方申请
	private String appid;

	// 2.私钥 pkcs8格式的
	private String privateKey;

	// 3.支付宝公钥
	private String publicKey;

	// 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	private String notifyUrl;

	// 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	private String returnUrl;

	// 6.请求网关地址
	private String serverUrl = "https://openapi.alipay.com/gateway.do";

	// 7.编码
	private String charset = "UTF-8";

	// 8.返回格式
	private String format = "json";

	// 9.加密类型(推荐使用RSA2)
	private String signType = "RSA2";

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	@Override
	protected void done() {
		if (StringUtil.isBlank(appid, privateKey, publicKey, notifyUrl, returnUrl, serverUrl)) {
			throw new MutilsException("支付宝支付初始化失败,请检查配置文件是否正确.");
		}
		alipayConfig = this;
	}

	@Override
	protected void showInfomation() {
		
	}
}

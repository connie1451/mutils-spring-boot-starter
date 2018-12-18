package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

public class WechatPayConfig implements InitConfig {
	
	public static WechatPayConfig wechatPayConfig;
	
	// appid
	private  String appid;

	private  String appSercet;

	private  String apiSercet;
	// 商户号
	private  String partner;

	private  String createOrderURL;
	// 退款地址
	private  String refundURL;

	private  String backUrl;// 微信支付下单地址

	private  String notifyUrl;// 异步通知地址

	private  String certificatePath;// 证书地址

	private  String url;// 接口地址

	private  String certificateFormat = "PKCS12";// 证书格式

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppSercet() {
		return appSercet;
	}

	public void setAppSercet(String appSercet) {
		this.appSercet = appSercet;
	}

	public String getApiSercet() {
		return apiSercet;
	}

	public void setApiSercet(String apiSercet) {
		this.apiSercet = apiSercet;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getCreateOrderURL() {
		return createOrderURL;
	}

	public void setCreateOrderURL(String createOrderURL) {
		this.createOrderURL = createOrderURL;
	}

	public String getRefundURL() {
		return refundURL;
	}

	public void setRefundURL(String refundURL) {
		this.refundURL = refundURL;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCertificateFormat() {
		return certificateFormat;
	}

	public void setCertificateFormat(String certificateFormat) {
		this.certificateFormat = certificateFormat;
	}

	@Override
	public void done() {
		if(StringUtil.isBlank(appid,appSercet,apiSercet,partner,createOrderURL,refundURL,backUrl,notifyUrl,certificatePath,url)) {
			throw new MutilsException("微信支付初始化失败,请检查配置文件是否正确.");
		}
		wechatPayConfig = this;
		
	}
}

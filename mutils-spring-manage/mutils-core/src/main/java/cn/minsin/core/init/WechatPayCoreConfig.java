package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

public class WechatPayCoreConfig implements InitConfig {

	public static WechatPayCoreConfig wechatPayConfig;

	//微信分配的公众账号ID 并不是app或小程序的appid 退款时必填
	private String publicAppId;
	
	// 商户id
	private String partnerId;

	// 商户秘钥
	private String partnerKey;

	// 退款通知地址
	private String refundUrl;

	// 付款通知地址
	private String notifyUrl;// 异步通知地址

	// 退款证书地址
	private String certificatePath;// 证书地址

	// 统一下单地址
	private String unifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

	// 退款证书格式
	private String certificateFormat = "PKCS12";// 证书格式

	// 是否包含退款 如果为true 如果为true certificatePath、refundUrl、certificateFormat, publicAppId必填
	private boolean withRefund = false;
	
	//提现地址
	private String withdrawUrl ="https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
	
	

	public boolean isWithRefund() {
		return withRefund;
	}

	
	public String getWithdrawUrl() {
		return withdrawUrl;
	}

	public void setWithdrawUrl(String withdrawUrl) {
		this.withdrawUrl = withdrawUrl;
	}



	public void setWithRefund(boolean withRefund) {
		this.withRefund = withRefund;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getPartnerKey() {
		return partnerKey;
	}

	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}

	public String getRefundUrl() {
		return refundUrl;
	}

	public void setRefundUrl(String refundUrl) {
		this.refundUrl = refundUrl;
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

	public String getUnifiedOrderUrl() {
		return unifiedOrderUrl;
	}

	public void setUnifiedOrderUrl(String unifiedOrderUrl) {
		this.unifiedOrderUrl = unifiedOrderUrl;
	}

	public String getCertificateFormat() {
		return certificateFormat;
	}

	public void setCertificateFormat(String certificateFormat) {
		this.certificateFormat = certificateFormat;
	}

	public String getPublicAppId() {
		return publicAppId;
	}

	public void setPublicAppId(String publicAppId) {
		this.publicAppId = publicAppId;
	}

	@Override
	public void done() {
		if (StringUtil.isBlank(partnerId, partnerKey, notifyUrl, unifiedOrderUrl)) {
			throw new MutilsException("微信支付初始化失败,请检查配置文件是否正确. A error when initialization the basic config for wechat pay, please check config");
		}
		if (withRefund) {
			if (StringUtil.isBlank(certificatePath, refundUrl, certificateFormat,publicAppId)) {
				throw new MutilsException("微信支付退款初始化失败,请检查配置文件是否正确. The refund config of wechat pay was initialization failed.");
			}
		}
		wechatPayConfig = this;
	}
}

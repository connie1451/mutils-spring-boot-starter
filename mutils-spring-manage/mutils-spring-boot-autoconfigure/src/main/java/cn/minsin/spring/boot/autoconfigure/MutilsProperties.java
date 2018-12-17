package cn.minsin.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.init.MutilsFunctions;

@ConfigurationProperties(prefix = MutilsProperties.MUTILS_PREFIX)
public class MutilsProperties {
	public static final String MUTILS_PREFIX = "mutils";

	private MutilsFunctions[] functions;

	private AlipayConfig alipay = new AlipayConfig();

	private ExcelConfig excel = new ExcelConfig();

	public ExcelConfig getExcel() {
		return excel;
	}

	public void setExcel(ExcelConfig excel) {
		this.excel = excel;
	}

	public MutilsFunctions[] getFunctions() {
		return functions;
	}

	public void setFunctions(MutilsFunctions[] functions) {
		this.functions = functions;
	}

	public AlipayConfig getAlipay() {
		return alipay;
	}

	public void setAlipay(AlipayConfig alipay) {
		this.alipay = alipay;
	}

	static class ExcelConfig {

		private String error_template_url;

		public String getError_template_url() {
			return error_template_url;
		}

		public void setError_template_url(String error_template_url) {
			this.error_template_url = error_template_url;
		}
	}

	static class AlipayConfig {

		/**
		 * 支付宝appid
		 */
		private String appid;

		/**
		 * 支付宝私钥
		 */
		private String privateKey;
		/**
		 * 支付宝公钥
		 */
		private String publicKey;

		/**
		 * 回调地址 完整路径
		 */
		private String notifyUrl;

		public String getNotifyUrl() {
			return notifyUrl;
		}

		public void setNotifyUrl(String notifyUrl) {
			this.notifyUrl = notifyUrl;
		}

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
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}

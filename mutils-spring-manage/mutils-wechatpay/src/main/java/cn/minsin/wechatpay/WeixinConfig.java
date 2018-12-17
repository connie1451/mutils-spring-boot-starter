package cn.minsin.wechatpay;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import cn.minsin.core.thirdpart.HttpClientFactory;
import cn.minsin.core.thirdpart.Sha1Util;


/**
 * 微信配置文件(微信支付，微信公众号)
 * 
 * @author mintonzhang
 * @date 2018年6月22日
 */
public class WeixinConfig {
	// appid
	public static String APPID;

	public static String APP_SERCET;

	public static String API_SERCET;
	// 商户号
	public static String PARTNER;

	public static String DOMAIN;

	public static String createOrderURL;
	// 退款地址
	public static String refundURL;

	public static String backUri;// 微信支付下单地址

	public static String notify_url;// 异步通知地址

	public static String certificatePath;// 证书地址

	public static String url;// 接口地址

	public static String certificateFormat = "PKCS12";// 证书格式

	/**
	 * 读取配置文件
	 */
	public static Properties properties = new Properties();

	static {
		try {
			String path = "wechat.properties";
			InputStream inStream = WeixinConfig.class.getClassLoader().getResourceAsStream(path);
			if (inStream == null) {
				inStream = WeixinConfig.class.getClassLoader().getResourceAsStream("/" + path);
			}
			properties.load(inStream);
			APPID = properties.getProperty("APPID");
			APP_SERCET = properties.getProperty("APP_SERCET");
			API_SERCET = properties.getProperty("API_SERCET");
			PARTNER = properties.getProperty("PARTNER");
			DOMAIN = properties.getProperty("DOMAIN");
			createOrderURL = properties.getProperty("createOrderURL");
			refundURL = properties.getProperty("refundURL");
			certificatePath = properties.getProperty("certificatePath");
			backUri = DOMAIN + "/pay/wechatNotify";
			notify_url = DOMAIN + "/pay/wechatNotify";
			url = properties.getProperty("url");
			certificateFormat = properties.getProperty("certificateFormat");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 生成JSAPI微信支付
	 * 
	 * @date 2018年6月26日
	 * @author mintonzhang@163.com
	 * @param orderNumber
	 * @param price
	 * @return 返回xml字符串
	 */
	public static String createWeiXinPayXml(String out_trade_no, BigDecimal price, String ip) {
		/* 公众账号ID appid */
		String appid = WeixinConfig.APPID;

		/* 商户号 mch_id */
		String mch_id = WeixinConfig.PARTNER;

		/* 随机字符串 nonce_str */
		// 8位日期
		String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;

		/* 商品描述 body */
		String body = "赞币充值";
		/* 金额 total_fee */
		BigDecimal sessionmoney = new BigDecimal(price.toString());
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		int intMoney = Integer.parseInt(finalmoney);

		// 总金额以分为单位，不带小数点
		int total_fee = intMoney;
		/* 终端 spbill_create_ip */
		String spbill_create_ip = ip;
		/* 通知地址 notify_url */
		String notify_url = WeixinConfig.notify_url;
		/* 交易类型 trade_type */
		String trade_type = "APP";
		String divice_info = "WEB";
		/* 签名 sign */
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", APPID);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		packageParams.put("device_info", divice_info);
		packageParams.put("out_trade_no", out_trade_no);
		// packageParams.put("openid", openId);
		packageParams.put("total_fee", "" + total_fee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);
		packageParams.put("trade_type", trade_type);
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, WeixinConfig.APP_SERCET, WeixinConfig.API_SERCET);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<body><![CDATA[" + body + "]]></body>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<spbill_create_ip>"
				+ spbill_create_ip + "</spbill_create_ip>" + "<total_fee>" + total_fee + "</total_fee>" + "<trade_type>"
				+ trade_type + "</trade_type>" + "<sign>" + sign + "</sign>" + "<device_info>" + divice_info
				+ "</device_info></xml>";
		return xml;
	}

	/**
	 * 生成统一下单
	 * 
	 * @date 2018年6月26日
	 * @author mintonzhang@163.com
	 * @param orderNumber
	 * @param price
	 * @return 返回xml字符串
	 */
	public static String createWeiXinPayXmlWeb(String out_trade_no, BigDecimal price, Integer type, String ip) {

		/* 公众账号ID appid */
		String appid = WeixinConfig.APPID;

		/* 商户号 mch_id */
		String mch_id = WeixinConfig.PARTNER;

		/* 随机字符串 nonce_str */
		// 8位日期
		String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;

		/* 商品描述 body */
		String body = "赞币充值";
		/* 商户订单号 out_trade_no */

		/* 金额 total_fee */
		BigDecimal sessionmoney = new BigDecimal(price.toString());
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		int intMoney = Integer.parseInt(finalmoney);

		// 总金额以分为单位，不带小数点
		int total_fee = intMoney;
		/* 终端 spbill_create_ip */
		String spbill_create_ip = ip;
		/* 通知地址 notify_url */
		String notify_url = WeixinConfig.notify_url;
		/* 交易类型 trade_type */
		String trade_type = "NATIVE";
		/* 签名 sign */
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("body", body);
		// packageParams.put("attach", attach);
		packageParams.put("out_trade_no", out_trade_no);

		// 这里写的金额为1 分到时修改
		packageParams.put("total_fee", "" + total_fee);
		packageParams.put("spbill_create_ip", spbill_create_ip);
		packageParams.put("notify_url", notify_url);

		packageParams.put("trade_type", trade_type);
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(appid, WeixinConfig.APP_SERCET, WeixinConfig.API_SERCET);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<body><![CDATA[" + body + "]]></body>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<notify_url>" + notify_url
				+ "</notify_url>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<spbill_create_ip>"
				+ spbill_create_ip + "</spbill_create_ip>" + "<total_fee>" + total_fee + "</total_fee>" + "<trade_type>"
				+ trade_type + "</trade_type>" + "<sign>" + sign + "</sign>" + "</xml>";
		return xml;

	}

	/**
	 * 生成微信支付js需要的提交参数
	 * 
	 * @date 2018年6月27日
	 * @author mintonzhang@163.com
	 * @param doXMLParse
	 * @return
	 */
	public static Map<String, String> createWexinJsPayParamter(Map<String, String> doXMLParse) {
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			// appId、timeStamp、nonceStr、package、signType
			String appId = doXMLParse.get("appid");
			sortMap.put("appId", appId);
			sortMap.put("nonceStr", doXMLParse.get("nonce_str"));
			sortMap.put("package", "prepay_id=" + doXMLParse.get("prepay_id"));
			sortMap.put("signType", "MD5");
			sortMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
			RequestHandler reqHandler = new RequestHandler(null, null);
			reqHandler.init(appId, APP_SERCET, API_SERCET);
			String sign = reqHandler.createSign(sortMap);
			sortMap.put("paySign", sign);
			sortMap.put("prepay_id", doXMLParse.get("prepay_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortMap;
	}

	/**
	 * 生成app支付需要的提交参数
	 * 
	 * @date 2018年6月27日
	 * @author mintonzhang@163.com
	 * @param doXMLParse
	 * @return
	 */
	public static Map<String, String> createAppPayParamter(Map<String, String> doXMLParse) {
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			// appId、timeStamp、nonceStr、package、signType
			sortMap.put("appid", APPID);
			sortMap.put("partnerid", PARTNER);
			sortMap.put("noncestr", doXMLParse.get("nonce_str"));
			sortMap.put("package", "Sign=WXPay");
			sortMap.put("timestamp", System.currentTimeMillis() / 1000 + "");
			sortMap.put("prepayid", doXMLParse.get("prepay_id"));
			sortMap.put("code_url", doXMLParse.get("code_url"));
			RequestHandler reqHandler = new RequestHandler(null, null);
			reqHandler.init(APPID, APP_SERCET, API_SERCET);
			String sign = reqHandler.createSign(sortMap);
			sortMap.put("paysign", sign);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortMap;
	}

	/**
	 * 生成微信JS相关初始化config配置
	 * 
	 * @date 2018年6月27日
	 * @author mintonzhang@163.com
	 * @param url
	 * @return
	 */
	public static Map<String, Object> createInitJSConfig(String url) {
		try {
			/* 公众账号ID appid */
			String appid = WeixinConfig.APPID;//
			String sercet = WeixinConfig.APP_SERCET;
			String jsapi_ticket =WeiXinEntity.init(appid, sercet).getJsTicket();

			String currTime = TenpayUtil.getCurrTime();
			String strTime = currTime.substring(8, currTime.length());
			// 四位随机数
			String strRandom = TenpayUtil.buildRandom(4) + "";
			// 10位序列号,可以自行调整。
			String nonce_str = strTime + strRandom;

			Long timestamp = System.currentTimeMillis() / 1000;

			String[] jsApiList = { "openLocation", "getLocation", "chooseWXPay" };
			SortedMap<String, String> packageParams = new TreeMap<>();
			packageParams.put("noncestr", nonce_str);
			packageParams.put("timestamp", timestamp.toString());
			packageParams.put("jsapi_ticket", jsapi_ticket);
			packageParams.put("url", url);

			String sign = Sha1Util.createSHA1Sign(packageParams);

			SortedMap<String, Object> returnMap = new TreeMap<>();
			returnMap.put("appId", appid);
			returnMap.put("nonceStr", nonce_str);
			returnMap.put("timestamp", timestamp);
			returnMap.put("signature", sign);
			returnMap.put("jsApiList", jsApiList);
			returnMap.put("debug", false);
			return returnMap;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 发起微信退款申请
	 * 
	 * @date 2018年7月6日
	 * @author mintonzhang@163.com
	 * @param transaction_id
	 *            微信支付id
	 * @param out_refund_no
	 *            退款单号
	 * @param total_fee
	 *            订单总金额
	 * @param refund_fee
	 *            退款金额
	 * @return
	 */
	public static String createRefundXml(String transaction_id, String out_refund_no, Integer total_fee,
			Integer refund_fee) {
		/* 随机字符串 nonce_str */
		// 8位日期
		String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		/* 签名 sign */
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", APPID);
		packageParams.put("mch_id", PARTNER);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("transaction_id", transaction_id);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", "" + total_fee);
		packageParams.put("refund_fee", "" + refund_fee);
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(APPID, APP_SERCET, API_SERCET);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + APPID + "</appid>" + "<mch_id>" + PARTNER + "</mch_id>" + "<nonce_str>"
				+ nonce_str + "</nonce_str>" + "<transaction_id>" + transaction_id + "</transaction_id>"
				+ "<out_refund_no>" + out_refund_no + "</out_refund_no>" + "<total_fee>" + total_fee + "</total_fee>"
				+ "<sign>" + sign + "</sign>" + "<refund_fee>" + refund_fee + "</refund_fee></xml>";
		return xml;

	}

	/**
	 * 发起微信提现申请
	 * 
	 * @date 2018年7月6日
	 * @author mintonzhang@163.com
	 * @param transaction_id
	 *            微信支付id
	 * @param out_refund_no
	 *            退款单号
	 * @param total_fee
	 *            订单总金额
	 * @param refund_fee
	 *            退款金额
	 * @return
	 */
	public static String createWithdrawXml(String out_refund_no, BigDecimal amount, String openId,
			String re_user_name) {
		/* 随机字符串 nonce_str */
		// 8位日期
		String currTime = TenpayUtil.getCurrTime();
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String nonce_str = strTime + strRandom;
		/* 签名 sign */
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("mch_appid", APPID);
		packageParams.put("mchid", PARTNER);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("partner_trade_no", out_refund_no);
		packageParams.put("openid", openId);
		packageParams.put("check_name", "FORCE_CHECK");
		packageParams.put("re_user_name", re_user_name);
		packageParams.put("amount", amount.toString());
		packageParams.put("desc", "提现");
		packageParams.put("spbill_create_ip", "192.168.0.109");
		RequestHandler reqHandler = new RequestHandler(null, null);
		reqHandler.init(APPID, APP_SERCET, API_SERCET);
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<mch_appid>" + APPID + "</mch_appid>" + "<mchid>" + PARTNER + "</mchid>" + "<nonce_str>"
				+ nonce_str + "</nonce_str>" + "<partner_trade_no>" + out_refund_no + "</partner_trade_no>" + "<openid>"
				+ openId + "</openid>" + "<check_name>FORCE_CHECK</check_name>" + "<re_user_name>" + re_user_name
				+ "</re_user_name>" + "<amount>" + amount + "</amount>" + "<desc>提现</desc>"
				+ "<spbill_create_ip>192.168.0.109</spbill_create_ip>" + "<sign>" + sign + "</sign></xml>";
		return xml;

	}

	/**
	 * 发起微信退款
	 * 
	 * @date 2018年7月6日
	 * @author mintonzhang@163.com
	 * @param url
	 * @param xmlParam
	 * @return
	 */
	public static Map<String, String> getRefundMap(String url, String xmlParam) {

		System.out.println("xml是:" + xmlParam);
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			CloseableHttpClient httpclient = HttpClientFactory.getSSLInstance(true, WeixinConfig.PARTNER,
					WeixinConfig.certificatePath, WeixinConfig.certificateFormat);

			HttpPost httpost = HttpClientFactory.getPostMethod(url);

			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));

			HttpResponse response = httpclient.execute(httpost);

			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("json是:" + jsonStr);
			httpclient.close();
			return WeChatOrder.doXMLParse(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortMap;
	}
}

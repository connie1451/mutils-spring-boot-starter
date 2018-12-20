package org.mutils.wechat.wechatpay.core;

import java.math.BigDecimal;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.mutils.wechat.wechatpay.core.model.BaseWeChatPayModel;
import org.mutils.wechat.wechatpay.core.util.ParseXmlUtil;
import org.mutils.wechat.wechatpay.core.util.SignUtil;

import cn.minsin.core.init.WechatPayCoreConfig;
import cn.minsin.core.thirdpart.HttpClientFactory;



/**
 * 微信配置文件(微信支付，微信公众号)
 * 
 * @author mintonzhang
 * @date 2018年6月22日
 */
public class WeChatPayFunctions {
	
//	/**
//	 * 生成JSAPI微信支付
//	 * 
//	 * @date 2018年6月26日
//	 * @author mintonzhang@163.com
//	 * @param orderNumber
//	 * @param price
//	 * @return 返回xml字符串
//	 */
//	public static String createWeiXinPayXml(String out_trade_no, BigDecimal price, String ip) {
//		/* 公众账号ID appid */
//		String appid = WeixinFunctions.APPID;
//
//		/* 商户号 mch_id */
//		String mch_id = WeixinFunctions.PARTNER;
//
//		/* 随机字符串 nonce_str */
//		// 8位日期
//		String currTime = TenpayUtil.getCurrTime();
//		String strTime = currTime.substring(8, currTime.length());
//		// 四位随机数
//		String strRandom = TenpayUtil.buildRandom(4) + "";
//		// 10位序列号,可以自行调整。
//		String nonce_str = strTime + strRandom;
//
//		/* 商品描述 body */
//		String body = "充值";
//		/* 金额 total_fee */
//		BigDecimal sessionmoney = new BigDecimal(price.toString());
//		String finalmoney = String.format("%.2f", sessionmoney);
//		finalmoney = finalmoney.replace(".", "");
//		int intMoney = Integer.parseInt(finalmoney);
//
//		// 总金额以分为单位，不带小数点
//		int total_fee = intMoney;
//		/* 终端 spbill_create_ip */
//		String spbill_create_ip = ip;
//		/* 通知地址 notify_url */
//		String notify_url = WeixinFunctions.notify_url;
//		/* 交易类型 trade_type */
//		String trade_type = "APP";
//		String divice_info = "WEB";
//		/* 签名 sign */
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", APPID);
//		packageParams.put("mch_id", mch_id);
//		packageParams.put("nonce_str", nonce_str);
//		packageParams.put("body", body);
//		packageParams.put("device_info", divice_info);
//		packageParams.put("out_trade_no", out_trade_no);
//		// packageParams.put("openid", openId);
//		packageParams.put("total_fee", "" + total_fee);
//		packageParams.put("spbill_create_ip", spbill_create_ip);
//		packageParams.put("notify_url", notify_url);
//		packageParams.put("trade_type", trade_type);
//		String sign = SignUtil.createSign(packageParams,PARTNER_KEY);
//		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<body><![CDATA[" + body + "]]></body>" + "<mch_id>"
//				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<notify_url>" + notify_url
//				+ "</notify_url>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<spbill_create_ip>"
//				+ spbill_create_ip + "</spbill_create_ip>" + "<total_fee>" + total_fee + "</total_fee>" + "<trade_type>"
//				+ trade_type + "</trade_type>" + "<sign>" + sign + "</sign>" + "<device_info>" + divice_info
//				+ "</device_info></xml>";
//		return xml;
//	}
//
//	/**
//	 * 生成统一下单
//	 * 
//	 * @date 2018年6月26日
//	 * @author mintonzhang@163.com
//	 * @param orderNumber
//	 * @param price
//	 * @return 返回xml字符串
//	 */
//	public static String createWeiXinPayXmlWeb(String out_trade_no, BigDecimal price, String ip,String title,String trade_type) {
//
//		/* 公众账号ID appid */
//		String appid = WeixinFunctions.APPID;
//
//		/* 商户号 mch_id */
//		String mch_id = WeixinFunctions.PARTNER;
//
//		/* 随机字符串 nonce_str */
//		// 8位日期
//		String currTime = TenpayUtil.getCurrTime();
//		String strTime = currTime.substring(8, currTime.length());
//		// 四位随机数
//		String strRandom = TenpayUtil.buildRandom(4) + "";
//		// 10位序列号,可以自行调整。
//		String nonce_str = strTime + strRandom;
//
//		/* 商户订单号 out_trade_no */
//
//		/* 金额 total_fee */
//		BigDecimal sessionmoney = new BigDecimal(price.toString());
//		String finalmoney = String.format("%.2f", sessionmoney);
//		finalmoney = finalmoney.replace(".", "");
//		int intMoney = Integer.parseInt(finalmoney);
//
//		// 总金额以分为单位，不带小数点
//		int total_fee = intMoney;
//		/* 终端 spbill_create_ip */
//		String spbill_create_ip = ip;
//		/* 通知地址 notify_url */
//		String notify_url = WeixinFunctions.notify_url;
//		/* 签名 sign */
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", appid);
//		packageParams.put("mch_id", mch_id);
//		packageParams.put("nonce_str", nonce_str);
//		packageParams.put("body", title);
//		// packageParams.put("attach", attach);
//		packageParams.put("out_trade_no", out_trade_no);
//
//		// 这里写的金额为1 分到时修改
//		packageParams.put("total_fee", "" + total_fee);
//		packageParams.put("spbill_create_ip", spbill_create_ip);
//		packageParams.put("notify_url", notify_url);
//
//		packageParams.put("trade_type", trade_type);
//		String sign = SignUtil.createSign(packageParams,PARTNER_KEY);
//		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<body><![CDATA[" + title + "]]></body>" + "<mch_id>"
//				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<notify_url>" + notify_url
//				+ "</notify_url>" + "<out_trade_no>" + out_trade_no + "</out_trade_no>" + "<spbill_create_ip>"
//				+ spbill_create_ip + "</spbill_create_ip>" + "<total_fee>" + total_fee + "</total_fee>" + "<trade_type>"
//				+ trade_type + "</trade_type>" + "<sign>" + sign + "</sign>" + "</xml>";
//		return xml;
//
//	}

	/**
	 * 生成微信支付js需要的提交参数
	 * 
	 * @date 2018年6月27日
	 * @author mintonzhang@163.com
	 * @param doXMLParse
	 * @return
	 */
	public static Map<String, String> createJSAPIPayParamter(Map<String, String> doXMLParse) {
		checkMap(doXMLParse);
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			// appId、timeStamp、nonceStr、package、signType
			String appId = doXMLParse.get("appid");
			sortMap.put("appId", appId);
			sortMap.put("nonceStr", doXMLParse.get("nonce_str"));
			sortMap.put("package", "prepay_id=" + doXMLParse.get("prepay_id"));
			sortMap.put("signType", "MD5");
			sortMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
			String sign = SignUtil.createSign(sortMap, WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
			sortMap.put("paySign", sign);
			sortMap.put("prepay_id", doXMLParse.get("prepay_id"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortMap;
	}
	
	/**
	 * 小程序支付
	 * @param doXMLParse
	 * @return
	 */
	public static Map<String, String> createMiniProgramPayParamter(Map<String, String> doXMLParse) {
		checkMap(doXMLParse);
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			// appId、timeStamp、nonceStr、package、signType
			String appId = doXMLParse.get("appid");
			String nonceStr = doXMLParse.get("nonce_str");
			String package_str = "prepay_id=" + doXMLParse.get("prepay_id");
			String signType ="MD5";
			String timeStamp =  String.valueOf(System.currentTimeMillis() / 1000);
			sortMap.put("appId", appId);
			sortMap.put("nonceStr",nonceStr);
			sortMap.put("package",package_str);
			sortMap.put("signType", signType);
			sortMap.put("timeStamp",timeStamp);
			String sign = SignUtil.createSign(sortMap, WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
			sortMap.put("paySign", sign);
			sortMap.remove("appId");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortMap;
	}

	
	public static Map<String, String> createAppPayParamter(Map<String, String> doXMLParse) {
		checkMap(doXMLParse);
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			String appId = doXMLParse.get("appid");
			String nonceStr = doXMLParse.get("nonce_str");
			String prepayid = doXMLParse.get("prepay_id");
			String timeStamp =  String.valueOf(System.currentTimeMillis() / 1000);
			
			sortMap.put("appid", appId);
			sortMap.put("partnerid", WechatPayCoreConfig.wechatPayConfig.getPartnerId());
			sortMap.put("noncestr", nonceStr);
			sortMap.put("package", "Sign=WXPay");
			sortMap.put("timestamp", timeStamp);
			sortMap.put("prepayid", prepayid);
			String sign = SignUtil.createSign(sortMap, WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
			sortMap.put("sign", sign);
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
//		try {
//			/* 公众账号ID appid */
//			String appid = WeixinFunctions.APPID;//
//			String sercet = WeixinFunctions.APP_SERCET;
//			String jsapi_ticket =WeiXinEntity.init(appid, sercet).getJsTicket();
//
//			String currTime = TenpayUtil.getCurrTime();
//			String strTime = currTime.substring(8, currTime.length());
//			// 四位随机数
//			String strRandom = TenpayUtil.buildRandom(4) + "";
//			// 10位序列号,可以自行调整。
//			String nonce_str = strTime + strRandom;
//
//			Long timestamp = System.currentTimeMillis() / 1000;
//
//			String[] jsApiList = { "openLocation", "getLocation", "chooseWXPay" };
//			SortedMap<String, String> packageParams = new TreeMap<>();
//			packageParams.put("noncestr", nonce_str);
//			packageParams.put("timestamp", timestamp.toString());
//			packageParams.put("jsapi_ticket", jsapi_ticket);
//			packageParams.put("url", url);
//
//			String sign = Sha1Util.createSHA1Sign(packageParams);
//
//			SortedMap<String, Object> returnMap = new TreeMap<>();
//			returnMap.put("appId", appid);
//			returnMap.put("nonceStr", nonce_str);
//			returnMap.put("timestamp", timestamp);
//			returnMap.put("signature", sign);
//			returnMap.put("jsApiList", jsApiList);
//			returnMap.put("debug", false);
//			return returnMap;
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
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
	public static String createRefundXml(String transaction_id, String out_refund_no, int total_fee,
			int refund_fee) {
//		/* 随机字符串 nonce_str */
//		// 8位日期
//		String currTime = TenpayUtil.getCurrTime();
//		String strTime = currTime.substring(8, currTime.length());
//		// 四位随机数
//		String strRandom = TenpayUtil.buildRandom(4) + "";
//		// 10位序列号,可以自行调整。
//		String nonce_str = strTime + strRandom;
//		/* 签名 sign */
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("appid", APPID);
//		packageParams.put("mch_id", PARTNER);
//		packageParams.put("nonce_str", nonce_str);
//		packageParams.put("transaction_id", transaction_id);
//		packageParams.put("out_refund_no", out_refund_no);
//		packageParams.put("total_fee",String.valueOf(total_fee));
//		packageParams.put("refund_fee", String.valueOf(refund_fee));
//		String sign = SignUtil.createSign(packageParams, WeChatConfig.weChatConfig.getPartnerKey());
//		String xml = "<xml>" + "<appid>" + APPID + "</appid>" + "<mch_id>" + PARTNER + "</mch_id>" + "<nonce_str>"
//				+ nonce_str + "</nonce_str>" + "<transaction_id>" + transaction_id + "</transaction_id>"
//				+ "<out_refund_no>" + out_refund_no + "</out_refund_no>" + "<total_fee>" + total_fee + "</total_fee>"
//				+ "<sign>" + sign + "</sign>" + "<refund_fee>" + refund_fee + "</refund_fee></xml>";
//		return xml;
		return null;
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
//		/* 随机字符串 nonce_str */
//		// 8位日期
//		String currTime = TenpayUtil.getCurrTime();
//		String strTime = currTime.substring(8, currTime.length());
//		// 四位随机数
//		String strRandom = TenpayUtil.buildRandom(4) + "";
//		// 10位序列号,可以自行调整。
//		String nonce_str = strTime + strRandom;
//		/* 签名 sign */
//		SortedMap<String, String> packageParams = new TreeMap<String, String>();
//		packageParams.put("mch_appid", APPID);
//		packageParams.put("mchid", PARTNER);
//		packageParams.put("nonce_str", nonce_str);
//		packageParams.put("partner_trade_no", out_refund_no);
//		packageParams.put("openid", openId);
//		packageParams.put("check_name", "FORCE_CHECK");
//		packageParams.put("re_user_name", re_user_name);
//		packageParams.put("amount", amount.toString());
//		packageParams.put("desc", "提现");
//		packageParams.put("spbill_create_ip", "192.168.0.109");
//		String sign = SignUtil.createSign(packageParams, PARTNER_KEY);
//		String xml = "<xml>" + "<mch_appid>" + APPID + "</mch_appid>" + "<mchid>" + PARTNER + "</mchid>" + "<nonce_str>"
//				+ nonce_str + "</nonce_str>" + "<partner_trade_no>" + out_refund_no + "</partner_trade_no>" + "<openid>"
//				+ openId + "</openid>" + "<check_name>FORCE_CHECK</check_name>" + "<re_user_name>" + re_user_name
//				+ "</re_user_name>" + "<amount>" + amount + "</amount>" + "<desc>提现</desc>"
//				+ "<spbill_create_ip>192.168.0.109</spbill_create_ip>" + "<sign>" + sign + "</sign></xml>";
//		return xml;
		return null;
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
		try {
			CloseableHttpClient httpclient = HttpClientFactory.getSSLInstance(true, 
					WechatPayCoreConfig.wechatPayConfig.getPartnerId(),
					WechatPayCoreConfig.wechatPayConfig.getCertificatePath(),
					WechatPayCoreConfig.wechatPayConfig.getCertificateFormat());

			HttpPost httpost = HttpClientFactory.getPostMethod(WechatPayCoreConfig.wechatPayConfig.getRefundUrl());

			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));

			HttpResponse response = httpclient.execute(httpost);

			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("json是:" + jsonStr);
			httpclient.close();
			return ParseXmlUtil.doXMLParse(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 统一下单接口 用于生成 预支付id 及二维码id
	 * @param xmlParam
	 * @return
	 */
	public static Map<String, String> unifiedOrder(BaseWeChatPayModel model){
		String xmlParam = model.toXml( WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
		System.out.println("xml是:" + xmlParam);
		CloseableHttpClient httpclient = HttpClientFactory.getInstance();// 先初始化;
		HttpPost httpost = HttpClientFactory.getPostMethod(WechatPayCoreConfig.wechatPayConfig.getUnifiedOrderUrl());
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);

			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("json是:" + jsonStr);
			if (jsonStr.indexOf("FAIL") != -1) {
				return null;
			}
			httpclient.close();
			return  ParseXmlUtil.doXMLParse(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sortMap;
	}
	
	
	static boolean checkMap(Map<String, String> doXMLParse) {
		if(doXMLParse==null||doXMLParse.isEmpty()) {
			throw new RuntimeException("统一支付XML生成失败,无法进行下一步操作. The value from unifiedOrder method is null,please check the parameters.");
		}
		return true;
	}
}

package org.mutils.wechat.wechatpay.core;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.mutils.wechat.wechatpay.core.model.BaseWeChatPayModel;
import org.mutils.wechat.wechatpay.core.model.RefundModel;
import org.mutils.wechat.wechatpay.core.model.WithdrawModel;
import org.mutils.wechat.wechatpay.core.util.ParseXmlUtil;
import org.mutils.wechat.wechatpay.core.util.SignUtil;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.WechatPayCoreConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.thirdpart.HttpClientFactory;

/**
 * 微信配置文件(微信支付，微信公众号)
 * 
 * @author mintonzhang
 * @date 2018年6月22日
 */
public class WeChatPayFunctions extends FunctionRule {

	/**
	 * 创建公众号支付的请求参数 小程序将用其发起微信支付
	 * 
	 * @param model 下单时的包装对象
	 * @return 公众号能发起的请求的包装内容
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createJSAPIPayParamter(BaseWeChatPayModel model) throws MutilsErrorException {
		Map<String, String> doXMLParse = createUnifiedOrder(model);
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
			return sortMap;
		} catch (Exception e) {
			throw new MutilsErrorException(e, "发起JSAPI支付失败");
		}

	}

	/**
	 * 创建小程序支付的请求参数 小程序将用其发起微信支付
	 * 
	 * @param model 下单时的包装对象
	 * @return 小程序能发起的请求的包装内容
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createMiniProgramPayParamter(BaseWeChatPayModel model)
			throws MutilsErrorException {
		Map<String, String> doXMLParse = createUnifiedOrder(model);
		checkMap(doXMLParse);
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			// appId、timeStamp、nonceStr、package、signType
			String appId = doXMLParse.get("appid");
			String nonceStr = doXMLParse.get("nonce_str");
			String package_str = "prepay_id=" + doXMLParse.get("prepay_id");
			String signType = "MD5";
			String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
			sortMap.put("appId", appId);
			sortMap.put("nonceStr", nonceStr);
			sortMap.put("package", package_str);
			sortMap.put("signType", signType);
			sortMap.put("timeStamp", timeStamp);
			String sign = SignUtil.createSign(sortMap, WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
			sortMap.put("paySign", sign);
			sortMap.remove("appId");
			return sortMap;
		} catch (Exception e) {
			throw new MutilsErrorException(e, "发起小程序支付失败");
		}
	}

	/**
	 * 创建APP支付的请求参数 APP将用其发起微信支付
	 * 
	 * @param model 下单时的包装对象
	 * @return APP能发起的请求的包装内容
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createAppPayParamter(BaseWeChatPayModel model) throws MutilsErrorException {
		Map<String, String> doXMLParse = createUnifiedOrder(model);
		checkMap(doXMLParse);
		SortedMap<String, String> sortMap = new TreeMap<>();
		try {
			String appId = doXMLParse.get("appid");
			String nonceStr = doXMLParse.get("nonce_str");
			String prepayid = doXMLParse.get("prepay_id");
			String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
			sortMap.put("appid", appId);
			sortMap.put("partnerid", WechatPayCoreConfig.wechatPayConfig.getPartnerId());
			sortMap.put("noncestr", nonceStr);
			sortMap.put("package", "Sign=WXPay");
			sortMap.put("timestamp", timeStamp);
			sortMap.put("prepayid", prepayid);
			String sign = SignUtil.createSign(sortMap, WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
			sortMap.put("sign", sign);
			return sortMap;
		} catch (Exception e) {
			throw new MutilsErrorException(e, "发起APP支付失败");
		}

	}

	/**
	 * 生成微信JS相关初始化config配置
	 * 
	 * @date 2018年6月27日
	 * @param url
	 * @return
	 */
	public static Map<String, Object> createInitJSConfig(String url) throws MutilsErrorException {
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
	 * 发起微信提现申请
	 * 
	 * @date 2018年7月6日
	 * @param transaction_id 微信支付id
	 * @param out_refund_no  退款单号
	 * @param total_fee      订单总金额
	 * @param refund_fee     退款金额
	 * @return
	 */
	public static String createWithdrawXml(WithdrawModel model) throws MutilsErrorException {
		// TODO 此处逻辑没完
		 String xml = model.toXml(WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
		 slog.info("refund xml is {}", xml);
		 return xml;
	}

	/**
	 * 发起退款申请
	 * 
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createRefundRequest(RefundModel model) throws MutilsErrorException {
		String xmlParam = model.toXml(WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
		slog.info("refund xml is {}", xmlParam);
		try {
			CloseableHttpClient httpclient = HttpClientFactory.getSSLInstance(true,
					WechatPayCoreConfig.wechatPayConfig.getPartnerId(),
					WechatPayCoreConfig.wechatPayConfig.getCertificatePath(),
					WechatPayCoreConfig.wechatPayConfig.getCertificateFormat());
			HttpPost httpost = HttpClientFactory.getPostMethod(WechatPayCoreConfig.wechatPayConfig.getRefundUrl());
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			slog.info("refund json is {}", jsonStr);
			httpclient.close();
			return ParseXmlUtil.doXMLParse(jsonStr);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "发起退款失败");
		}
	}

	/**
	 * 统一下单接口 用于生成 预支付id 及二维码id
	 * 
	 * @param model 预下单的对象
	 * @return
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createUnifiedOrder(BaseWeChatPayModel model) throws MutilsErrorException {
		String xmlParam = model.toXml(WechatPayCoreConfig.wechatPayConfig.getPartnerKey());
		slog.info("createUnifiedOrder xml is {}", xmlParam);
		CloseableHttpClient httpclient = HttpClientFactory.getInstance();// 先初始化;
		HttpPost httpost = HttpClientFactory.getPostMethod(WechatPayCoreConfig.wechatPayConfig.getUnifiedOrderUrl());
		try {
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			HttpResponse response = httpclient.execute(httpost);

			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			slog.info("createUnifiedOrder json is {}", jsonStr);
			if (jsonStr.indexOf("FAIL") != -1) {
				return null;
			}
			httpclient.close();
			return ParseXmlUtil.doXMLParse(jsonStr);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "发起统一下单失败");
		}
	}

	static boolean checkMap(Map<String, String> doXMLParse) throws MutilsErrorException {
		if (doXMLParse == null || doXMLParse.isEmpty()) {
			throw new MutilsErrorException(
					"统一支付XML生成失败,无法进行下一步操作. The value from createUnifiedOrder method is null,please check the parameters.");
		}
		return true;
	}
}

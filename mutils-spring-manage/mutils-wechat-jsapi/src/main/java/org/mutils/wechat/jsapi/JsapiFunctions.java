package org.mutils.wechat.jsapi;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.mutils.wechat.jsapi.model.JsapiOrderPayModel;
import org.mutils.wechat.wechatpay.core.WeChatPayFunctions;
import org.mutils.wechat.wechatpay.core.model.RefundModel;
import org.mutils.wechat.wechatpay.core.util.SignUtil;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.WechatMiniProgramConfig;
import cn.minsin.core.init.WechatPayCoreConfig;

/**
 * 小程序相关功能
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class JsapiFunctions extends WeChatPayFunctions {

	
	/**
	 * 发起退款申请
	 * 
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createMiniProgramRefundParamter(RefundModel model) throws MutilsErrorException {
		//TODO sjapi
		model.setAppid(WechatMiniProgramConfig.wechatMiniProgramConfig.getAppid());
		return createRefundRequest(model);
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
	 * 创建公众号支付的请求参数 小程序将用其发起微信支付
	 * 
	 * @param model 下单时的包装对象
	 * @return 公众号能发起的请求的包装内容
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createJSAPIPayParamter(JsapiOrderPayModel model) throws MutilsErrorException {
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
}

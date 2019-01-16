package org.mutils.wechat.wechatpay.core;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;

import org.apache.http.client.methods.CloseableHttpResponse;
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
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.tools.HttpClientUtil;
import cn.minsin.core.tools.IOUtil;

/**
 * 微信配置文件(微信支付，微信公众号)
 * 
 * @author mintonzhang
 * @date 2018年6月22日
 */
public class WeChatPayFunctions extends FunctionRule {

	protected final static WechatPayCoreConfig payconfig = InitConfig.loadConfig(WechatPayCoreConfig.class);

	
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
		String xml = model.toXml(payconfig.getPartnerKey());
		log.info("refund xml is {}", xml);
		return xml;
	}

	/**
	 * 发起退款申请
	 * 
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	protected static Map<String, String> createRefundRequest(RefundModel model) throws MutilsErrorException {
		String xmlParam = model.toXml(payconfig.getPartnerKey());
		log.info("refund xml is {}", xmlParam);
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			httpclient = HttpClientUtil.getSSLInstance(
					payconfig.getPartnerId(),
					payconfig.getCertificatePath(),
					payconfig.getCertificateFormat());
			HttpPost httpost = HttpClientUtil.getPostMethod(payconfig.getRefundUrl());
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			response = httpclient.execute(httpost);

			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			log.info("refund json is {}", jsonStr);
			return ParseXmlUtil.doXMLParse(jsonStr);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "发起退款失败");
		} finally {
			IOUtil.close(httpclient, response);
		}
	}

	/**
	 * 统一下单接口 用于生成 预支付id 及二维码id
	 * 
	 * @param model 预下单的对象
	 * @return
	 * @throws MutilsErrorException
	 * @throws IOException
	 */
	protected static Map<String, String> createUnifiedOrder(BaseWeChatPayModel model) throws Exception {
		CloseableHttpClient httpclient = HttpClientUtil.getInstance();// 先初始化;
		CloseableHttpResponse response = null;
	
		try {
			HttpPost httpost = HttpClientUtil.getPostMethod(payconfig.getUnifiedOrderUrl());
			String xmlParam = model.toXml(payconfig.getPartnerKey());
			log.info("createUnifiedOrder xml is {}", xmlParam);
			httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
			 response = httpclient.execute(httpost);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			log.info("createUnifiedOrder json is {}", jsonStr);
			if (jsonStr.indexOf("FAIL") != -1) {
				throw new MutilsErrorException(jsonStr);
			}
			return ParseXmlUtil.doXMLParse(jsonStr);
		} finally {
			IOUtil.close(httpclient,response);
		}

	}

	protected static boolean checkMap(Map<String, String> doXMLParse) throws MutilsErrorException {
		if (doXMLParse == null || doXMLParse.isEmpty()) {
			throw new MutilsErrorException(
					"统一支付XML生成失败,无法进行下一步操作. The value from createUnifiedOrder method is null,please check the parameters.");
		}
		return true;
	}
	
	/**
	 * 	生成签名
	 * @param sortMap
	 * @return
	 */
	protected static String createSign(SortedMap<String, String> sortMap) {
		return SignUtil.createSign(sortMap,payconfig.getPartnerKey());
	}
}

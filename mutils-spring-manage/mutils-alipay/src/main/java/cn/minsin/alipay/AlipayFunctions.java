package cn.minsin.alipay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;

import cn.minsin.core.web.VO;

/**
 * 集成支付宝常用功能
 * 
 * @author mintonzhang@163.com 2018年12月6日
 */
public class AlipayFunctions {

	/**
	 * 发起支付宝网站支付
	 * 
	 * @param out_trade_no
	 * @param price
	 * @return
	 */
	public static String createWebAlipayParams(String out_trade_no, BigDecimal price) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
				AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGNTYPE);
		AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
		Map<String, Object> map = new HashMap<>();
		map.put("subject", "赞币充值");
		map.put("out_trade_no", out_trade_no);
		map.put("total_amount", price);
		alipayRequest.setBizContent(JSON.toJSONString(map));
		// alipayRequest.setReturnUrl(AlipayConfig.return_url);// 设置回调地址
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);// 设置回调地址
		map.clear();
		map.put("orderNum", out_trade_no);// 订单号
		try {
			AlipayTradePrecreateResponse response = alipayClient.execute(alipayRequest);

			if (response.isSuccess()) {
				// 拼接路径
				// String url = CommonUtils.properties.getProperty("qrCodeWebPath");
				// map.put("alipay",url+CommonUtils.createQrcode(response.getQrCode()));
				// String s = map.get("alipay").toString().replace("\\", "/");
				return response.getQrCode();
			} else {
				map.put("alipay", "");
			}
		} catch (Exception e) {
			map.put("alipay", "");
		}
		return "";
	}

	/**
	 * 发起支付宝订单生成
	 * 
	 * @param out_trade_no
	 * @param price
	 * @return 2018年7月18日
	 * @author mintonzhang@163.com
	 */
	public static String createAlipayParams(String out_trade_no, BigDecimal price, String title) {
		try {
			// 进行保留两位小数
			price = price.setScale(2, RoundingMode.DOWN);
			// 支付宝接口调用
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
					AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET,
					AlipayConfig.ALIPAY_PUBLIC_KEY, AlipayConfig.SIGNTYPE);
			AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setOutTradeNo(out_trade_no);
			model.setTotalAmount(price.toString());
			model.setSubject(title);
			alipayRequest.setBizModel(model);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);// 设置回调地址
			AlipayTradeAppPayResponse response = alipayClient.sdkExecute(alipayRequest);
			if (response.isSuccess()) {
				return response.getBody();
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 支付宝转账
	 * @param model
	 * @return
	 * 2018年12月6日
	 * @author  mintonzhang@163.com
	 */
	public static VO transfer(TransferModel model) {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL, AlipayConfig.APPID,
				AlipayConfig.RSA_PRIVATE_KEY, AlipayConfig.FORMAT, AlipayConfig.CHARSET, AlipayConfig.ALIPAY_PUBLIC_KEY,
				AlipayConfig.SIGNTYPE);
		
		AlipayFundTransToaccountTransferRequest  alipayRequest = new AlipayFundTransToaccountTransferRequest();
		alipayRequest.setBizContent(JSON.toJSONString(model));
		AlipayFundTransToaccountTransferResponse response = null;
		try {
			response = alipayClient.execute(alipayRequest);
			return VO.builder()
					.put("code", response.getCode())
					.put("sub_msg", response.getSubMsg());
		} catch (AlipayApiException e) {
			e.printStackTrace();
			return null;
		}
	}
}

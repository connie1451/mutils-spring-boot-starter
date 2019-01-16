package cn.minsin.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;

import cn.minsin.alipay.model.PayModel;
import cn.minsin.alipay.model.RefundModel;
import cn.minsin.alipay.model.TransferModel;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.AlipayConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;

/**
 * 集成支付宝常用功能
 * 
 * @author mintonzhang@163.com 2018年12月6日
 */
public class AlipayFunctions extends FunctionRule {

	private final static AlipayConfig config = InitConfig.loadConfig(AlipayConfig.class);

	/**
	 * 发起支付宝网站支付
	 * 
	 * @param out_trade_no
	 * @param price
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AlipayResponse createWebAlipayParams(PayModel payModel) throws MutilsErrorException {
		
		try {
			AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
			alipayRequest.setBizContent(payModel.toString());
			alipayRequest.setNotifyUrl(config.getNotifyUrl());// 设置回调地址
			return initAlipayClient().execute(alipayRequest);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "Create Alipay Web payment failure.");
		}
	}

	/**
	 * 发起支付宝订单生成
	 * 
	 * @param out_trade_no
	 * @param price
	 * @return 2018年7月18日
	 * @throws MutilsErrorException
	 */
	public static AlipayResponse createAlipayParams(PayModel payModel) throws MutilsErrorException {
		try {
			// 进行保留两位小数
			AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
			alipayRequest.setBizContent(payModel.toString());
			alipayRequest.setNotifyUrl(config.getNotifyUrl());// 设置回调地址
			return initAlipayClient().sdkExecute(alipayRequest);
		} catch (AlipayApiException e) {
			throw new MutilsErrorException(e, "Create Alipay mobile payment failure.");
		}

	}

	/**
	 * 支付宝转账
	 * 
	 * @param model
	 * @return 2018年12月6日
	 * @throws MutilsErrorException
	 */
	public static AlipayResponse transfer(TransferModel model) throws MutilsErrorException {
		
		try {
			AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
			alipayRequest.setBizContent(model.toString());
			return initAlipayClient().execute(alipayRequest);
		} catch (AlipayApiException e) {
			throw new MutilsErrorException(e, "Initiation of Alipay transfer failed.");
		}
	}

	/**
	 * 支付宝退款
	 * 
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AlipayResponse refund(RefundModel model) throws MutilsErrorException {
		
		try {
			AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
			alipayRequest.setBizContent(model.toString());
			return initAlipayClient().execute(alipayRequest);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "Initiating Alipay refund failed.");
		}
	}

	static AlipayClient initAlipayClient() {
		return new DefaultAlipayClient(
				config.getServerUrl(),
				config.getAppid(),
				config.getPrivateKey(), 
				config.getFormat(),
				config.getCharset(),
				config.getPublicKey(),
				config.getSignType());
	}
}

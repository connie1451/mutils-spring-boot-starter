package cn.minsin.dianwoda;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.DianWoDaConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.tools.MapUtil;
import cn.minsin.dianwoda.model.OrderModel;

public class DianWoDaFunctions extends FunctionRule {
	

	/**
	 * 派发订单 /api/v3/order-send.json
	 * 
	 * @return
	 * @throws Exception 
	 */
	public JSONObject order_send(OrderModel ot) throws MutilsErrorException {
		return doSend("/api/v3/order-send.json", MapUtil.getMap(ot));
	}

	/**
	 * 模拟发送http请求
	 *
	 * @param api            业务api接口
	 * @param businessParams 业务参数
	 * @return 响应结果
	 */
	static JSONObject doSend(String url, Map<String, Object> businessParams) throws MutilsErrorException {
		if (DianWoDaConfig.dianWoDaConfig == null) {
			throw new MutilsException("点我达配置尚未初始化. DianWoDa config was not initialized.");
		}

		/* 生成签名 */
		String sign = SignUtil.sign(businessParams, DianWoDaConfig.dianWoDaConfig.getSercret());

		businessParams.put("pk", DianWoDaConfig.dianWoDaConfig.getPk());
		businessParams.put("v", DianWoDaConfig.dianWoDaConfig.getVersion());
		businessParams.put("format", DianWoDaConfig.dianWoDaConfig.getFormat());
		businessParams.put("sig", sign);
		businessParams.put("timestamp", DianWoDaConfig.dianWoDaConfig.getTimestamp());
		try {
			HttpPost post = new HttpPost(DianWoDaConfig.dianWoDaConfig.getUrl() + url);
			List<NameValuePair> list = new LinkedList<>();
			businessParams.keySet().forEach(k -> {
				list.add(new BasicNameValuePair(k, businessParams.get(k).toString()));
			});
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);

			CloseableHttpClient build = HttpClientBuilder.create().build();
			slog.info("Request infomation is {}",JSONObject.toJSONString(list));
			CloseableHttpResponse response = build.execute(post);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			response.close();
			build.close();
			slog.info("Request infomation is {}",string);
			return JSON.parseObject(string);
		} catch (Exception e) {
			throw new MutilsErrorException(e,"点我达请求下单失败");
		}
	}

}

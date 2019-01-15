package cn.minsin.yiketong;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.YiKeTongConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.yiketong.model.ResultModel;
import cn.minsin.yiketong.util.ParamUtil;
import cn.minsin.yiketong.util.SignUtil;

/**
 * 移客通号码转发功能 功能列表
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class YiKeTongFunctions extends FunctionRule {

	/**
	 * 手机号进行虚拟号绑定
	 * 
	 * @param area_code
	 * @param tel_a
	 * @param tel_b
	 */
	public ResultModel binding(String area_code, String tel_a, String tel_b) throws MutilsErrorException {
		checkConfig("YiKeTongFunctions",YiKeTongConfig.yiKeTongConfig);
		String url = YiKeTongConfig.yiKeTongConfig.getApiUrl() + "number/axb/binding";

		String ts = String.valueOf(System.currentTimeMillis() / 1000);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appkey", YiKeTongConfig.yiKeTongConfig.getCorpKey());
		params.put("ts", ts);
		params.put("request_id", System.currentTimeMillis());
		params.put("tel_a", tel_a);
		params.put("tel_b", tel_b);
		params.put("area_code", area_code);
		params.put("expiration", "600");

		String orderStr = ParamUtil.createLinkString(params);
		orderStr = orderStr + "&secret=" + YiKeTongConfig.yiKeTongConfig.getCorpSecret();
		System.out.println("orderStr:" + orderStr);

		String sign = SignUtil.encode(orderStr);
		System.out.println("sign:" + sign);

		params.put("sign", sign);

		try {
			HttpPost post = new HttpPost(url);

			StringEntity se = new StringEntity(JSONObject.toJSONString(params), "utf-8");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, Consts.UTF_8.toString()));
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
			post.setEntity(se);

			CloseableHttpClient build = HttpClientBuilder.create().build();
			System.out.println("req=" + JSONObject.toJSONString(params));
			CloseableHttpResponse response = build.execute(post);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);

			return JSON.parseObject(string, ResultModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "移客通绑定发起虚拟号绑定失败");
		}
	}

	/**
	 * 查询号码池使用率 由于服务商规定 只查询60%的数据 以下的data都会为空
	 * 
	 * @return
	 */
	public ResultModel utilization() throws MutilsErrorException {
		checkConfig("YiKeTongFunctions",YiKeTongConfig.yiKeTongConfig);
		try {
			String url = YiKeTongConfig.yiKeTongConfig.getApiUrl() + "monitor/axb/utilization";

			String ts = String.valueOf(System.currentTimeMillis() / 1000);
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("appkey", YiKeTongConfig.yiKeTongConfig.getCorpKey());
			params.put("ts", ts);

			String orderStr = ParamUtil.createLinkString(params);
			orderStr = orderStr + "&secret=" + YiKeTongConfig.yiKeTongConfig.getCorpSecret();
			System.out.println("orderStr:" + orderStr);

			String sign = SignUtil.encode(orderStr);
			System.out.println("sign:" + sign);

			params.put("sign", sign);

			HttpPost post = new HttpPost(url);

			StringEntity se = new StringEntity(JSONObject.toJSONString(params), "utf-8");
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING, Consts.UTF_8.toString()));
			se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json;charset=UTF-8"));
			post.setEntity(se);

			CloseableHttpClient build = HttpClientBuilder.create().build();
			System.out.println("req=" + JSONObject.toJSONString(params));
			CloseableHttpResponse response = build.execute(post);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			System.out.println(string);
			return JSON.parseObject(string, ResultModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "移客通绑定查询号码使用率失败");
		}
	}
}

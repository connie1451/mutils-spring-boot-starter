/**
 * 
 */
package cn.minsin.kuaidi100;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.KuaiDi100Config;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.thirdpart.HttpClientFactory;

/**
 * 快递100物流查询
 * @author mintonzhang 2018年7月19日
 */
public class KuaiDi100Functions extends FunctionRule{
	/**
	 * 查询物流单号
	 * 
	 * @param logisticsCode
	 *            物流公司code
	 * @param logisticsNumber
	 *            物流单号
	 * @return 2018年7月20日
	 */
	public  String getLogistics(String logisticsCode, String logisticsNumber)  throws MutilsErrorException{
		try {
			String param = "{\"com\":\"" + logisticsNumber + "\",\"num\":\"" + logisticsCode + "\"}";
			String sign = KuaiDi100MD5.encode(param + KuaiDi100Config.kuaiDi100Config.getKey() + KuaiDi100Config.kuaiDi100Config.getCustomer());
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("param", param));
			params.add(new BasicNameValuePair("sign", sign));
			params.add(new BasicNameValuePair("customer", KuaiDi100Config.kuaiDi100Config.getCustomer()));
			CloseableHttpClient httpclient = HttpClientFactory.getSSLInstance(false,null,null,null);
			HttpPost post = HttpClientFactory.getPostMethod(KuaiDi100Config.kuaiDi100Config.getUrl());
			post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpclient.execute(post);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("物流json是:" + jsonStr);
			return jsonStr;
		} catch (Exception e) {
			throw new MutilsErrorException(e,"快递100查询物流失败");
		}
	}
}

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

import cn.minsin.core.thirdpart.HttpClientFactory;

/**
 * 快递100物流查询
 * @author mintonzhang 2018年7月19日
 */
public class KuaiDi100Functions {
	private final  String CUSTOMER;

	private final String KEY;

	private final String URL;
	
	public KuaiDi100Functions(String cUSTOMER, String kEY, String uRL) {
		CUSTOMER = cUSTOMER;
		KEY = kEY;
		URL = uRL;
	}
	/**
	 * 查询物流单号
	 * 
	 * @param logisticsCode
	 *            物流公司code
	 * @param logisticsNumber
	 *            物流单号
	 * @return 2018年7月20日
	 * @author mintonzhang@163.com
	 */
	public  String getLogistics(String logisticsCode, String logisticsNumber) {
		try {
			String param = "{\"com\":\"" + logisticsNumber + "\",\"num\":\"" + logisticsCode + "\"}";
			String sign = KuaiDi100MD5.encode(param + KEY + CUSTOMER);
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("param", param));
			params.add(new BasicNameValuePair("sign", sign));
			params.add(new BasicNameValuePair("customer", CUSTOMER));
			CloseableHttpClient httpclient = HttpClientFactory.getSSLInstance(false,null,null,null);
			HttpPost post = HttpClientFactory.getPostMethod(URL);
			post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			HttpResponse response = httpclient.execute(post);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("物流json是:" + jsonStr);
			return jsonStr;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

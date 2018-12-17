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

import cn.minsin.core.thirdpart.ParamUtil;

/**
 * 移客通号码转发功能
 * @author mintonzhang@163.com
 * 2019年1月8日
 */
public class YiKeTongFunctions {
	
	
	private final String corp_key;// = "6845678513658223";
	private final String corp_secret;// = "p6jX1xP71EWBbB1Bcxky51u7e77H39XD";
	private final String api_url;// = "http://api.1ketong.com:81/ykt-pool/";
	
	
	public  YiKeTongFunctions(String corp_key,String corp_secret,String api_url) {
		this.corp_key=corp_key;
		this.corp_secret = corp_secret;
		this.api_url = api_url;
	}
	
	
	/**
	 * 手机号进行虚拟号绑定
	 * @param area_code
	 * @param tel_a
	 * @param tel_b
	 * 2019年1月8日
	 * @author  mintonzhang@163.com
	 */
	public  ResultEntity binding(String area_code,String tel_a,String tel_b){
		String url = api_url+"number/axb/binding";
		
    	String ts = String.valueOf(System.currentTimeMillis()/1000);
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("appkey", corp_key);
    	params.put("ts", ts);
    	params.put("request_id", System.currentTimeMillis());
    	params.put("tel_a", tel_a);
    	params.put("tel_b", tel_b);
    	params.put("area_code", area_code);
    	params.put("expiration", "600");

    	String orderStr = ParamUtil.createLinkString(params);
    	orderStr = orderStr+"&secret="+corp_secret;
		System.out.println("orderStr:"+orderStr);
		
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
			
			return JSON.parseObject(string,ResultEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查询号码池使用率 由于服务商规定 只查询60%的数据 以下的data都会为空
	 * @return
	 * 2019年1月8日
	 * @author  mintonzhang@163.com
	 */
	public ResultEntity utilization() {
		
		try {
			String url = api_url+"monitor/axb/utilization";
			
	    	String ts = String.valueOf(System.currentTimeMillis()/1000);
	    	Map<String, Object> params = new HashMap<String, Object>();
	    	params.put("appkey", corp_key);
	    	params.put("ts", ts);

	    	String orderStr = ParamUtil.createLinkString(params);
	    	orderStr = orderStr+"&secret="+corp_secret;
			System.out.println("orderStr:"+orderStr);
			
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
			return JSON.parseObject(string,ResultEntity.class);
		}catch (Exception e) {
			return null;
		}
	}
}

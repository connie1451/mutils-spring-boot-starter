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

import cn.minsin.core.tools.MapUtil;

public class DianWoDaFunctions {
	//URL：http://docking-normal-qa.dwbops.com
	//URL：http://api.dianwoda.com  
	private String URL;
	//appkey
	private String PK;
	
	private String VERSION = "1.0.0";
	
	private String SERCRET;
	
	private String FORMAT ="json";
	
	private Long TIMESTAMP = System.currentTimeMillis()/1000;
	
	/**
	 * 
	 * @param isDebug 是否调试 true会使用测试地址 false会使用正式地址
	 * @param pK
	 * @param sERCRET
	 */
	public DianWoDaFunctions(boolean isDebug, String pK, String sERCRET) {
		super();
		URL=isDebug?"http://docking-normal-qa.dwbops.com":"http://api.dianwoda.com";
		PK = pK;
		SERCRET = sERCRET;
	}


	/**
	 * 派发订单 /api/v3/order-send.json  
	 * @return
	 */
	public JSONObject order_send(OrderTemplate ot) {
		
		try {
			String doSend = doSend("/api/v3/order-send.json", MapUtil.getMap(ot));
			return JSON.parseObject(doSend);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 模拟发送http请求
	 *
	 * @param api            业务api接口
	 * @param businessParams 业务参数
	 * @return 响应结果
	 */
	private  String doSend(String url, Map<String, Object> businessParams) {
		/* 生成签名 */
		String sign = SignUtil.sign(businessParams, SERCRET);
		
		businessParams.put("pk", PK);
		businessParams.put("v", VERSION);
		businessParams.put("format",FORMAT);
		businessParams.put("sig", sign);
		businessParams.put("timestamp", TIMESTAMP);
		try {
			HttpPost post = new HttpPost(URL+url);
	        List<NameValuePair> list = new LinkedList<>();
	        businessParams.keySet().forEach(k->{list.add(new BasicNameValuePair(k, businessParams.get(k).toString()));});
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
	        post.setEntity(uefEntity);
	        
			CloseableHttpClient build = HttpClientBuilder.create().build();
			System.out.println("req=" + JSONObject.toJSONString(list));
			CloseableHttpResponse response = build.execute(post);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			response.close();
			build.close();
			System.out.println("response= " + string);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
//	
//	public static void main(String[] args) {
////		/* 业务参数 */
//		VO params = VO.builder();
//		params.put("money_rider_prepaid", "0");
//		params.put("consignee_name", "浩子");
//		params.put("city_code", "330100");
//		params.put("cargo_type", "00");
//		params.put("seller_mobile", "18271881787");
//		params.put("seller_lng", "114.211154");
//		params.put("delivery_fee_from_seller", "10");
//		params.put("order_create_time", "1539027679285");
//		params.put("order_original_id", "xw201511091191");
//		params.put("seller_name", "饭来湘");
//		params.put("serial_id", "6");
//		params.put("cargo_num", "0");
//		params.put("consignee_lat", "30.56477");
//		params.put("seller_id", "e595dcc0-446b-4906-955f-d6760d23ad8a");
//		params.put("cargo_weight", "0");
//		params.put("consignee_address", "湖北省武汉市汉阳区王家湾朝阳富苑东(汉阳大道北) 玫瑰东苑101栋");
//		params.put("money_rider_charge", "0");
//		params.put("consignee_lng", "114.2056");
//		params.put("time_waiting_at_seller", "0");
//		params.put("order_price", "2100");
//		params.put("order_is_reserve", "0");
//		params.put("seller_address", "饭来湘");
//		params.put("seller_lat", "30.56273");
//		params.put("consignee_mobile", "17771592353");
//		params.put("money_rider_needpaid","0");
//		params.put("items", "[{\"item_name\":\"水煮肉片 肉沫豆角 2份绿豆汤 2份米饭\",\"unit\":\"份\",\"quantity\":1,\"unit_price\":0,\"discount_price\":null,\"production_time\":0}]");
//		
//		OrderTemplate object = params.getObject(OrderTemplate.class);
//		
//		JSONObject order_send = new DianWoDaFunctions(true, "10215", "bcf085d5517f5e1d66e7f2a851f41403")
//		.order_send(object);
//		
//		
//	}
	
}

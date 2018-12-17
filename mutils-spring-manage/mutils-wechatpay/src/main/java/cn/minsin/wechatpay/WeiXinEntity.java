package cn.minsin.wechatpay;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.thirdpart.HttpClientFactory;


public class WeiXinEntity {

	// 获取access_token的接口地址（GET） 限200（次/天）
	public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//
	// public final static String message_url =
	// "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	//
	// // 获取jsapi_ticket_url的接口地址（GET） 限200（次/天）
	public final static String jsapi_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	//
	// // 自定义菜单创建接口
	// public final static String create_menu_url =
	// "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//
	// // 开发者可通过OpenID来获取用户基本信息。请使用https协议。
	// public final static String info_url =
	// "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN";

	private  AccessToken accessToken;

	private  String AppId;

	private  String AppSecret;
	
	public String getAppId() {
		return AppId;
	}

	public String getAppSecret() {
		return AppSecret;
	}

	private  WeiXinEntity( String appId, String appSecret) {
		AppId = appId;
		AppSecret = appSecret;
	}

	public static WeiXinEntity init(String appId, String appSecret) {
		return new WeiXinEntity(appId,appSecret);
	}

	/**
	 * 取得微信access_token
	 * 
	 * @return
	 * @throws Exception
	 */
	public  AccessToken getAccessToken() throws Exception {
		if (StringUtils.isBlank(AppId) || StringUtils.isBlank(AppSecret)) {
			throw new Exception("Must execute init method");
		}
		if (accessToken != null && accessToken.isExpires()) {
			return accessToken;
		}
		String requestUrl = access_token_url.replace("APPID", AppId).replace("APPSECRET", AppSecret);

		JSONObject jsonObject = httpRequest(requestUrl);

		// 请求成功
		if (null != jsonObject) {
			try {
				String access_token = jsonObject.get("access_token").toString();
				String jsapi_ticketurl = jsapi_ticket_url.replace("ACCESS_TOKEN", access_token);
				jsonObject = httpRequest(jsapi_ticketurl);
				String jsapi_ticke = jsonObject.get("ticket").toString();
				accessToken = new AccessToken();
				accessToken.setAccess_token(access_token);
				accessToken.setExpires_in(7200);
				accessToken.setExpires_time(System.currentTimeMillis() / 1000);
				accessToken.setJsapi_ticket(jsapi_ticke);
			} catch (Exception e) {
				accessToken = null;
			}
		}
		return accessToken;
	}

	public  String getJsTicket() throws Exception {
		if (StringUtils.isBlank(AppId) || StringUtils.isBlank(AppSecret)) {
			throw new Exception("Must execute init method");
		}
		AccessToken token = getAccessToken();
		if (accessToken != null && accessToken.isExpires()) {
			return token.getJsapi_ticket();
		}
		return null;
	}
	
	
	private  JSONObject  httpRequest(String url) {
		CloseableHttpClient httpclient = HttpClientFactory.getSSLInstance(false, null, null, null);
		try {
			HttpResponse response = null;
			response = httpclient.execute(HttpClientFactory.getGetMethod(url));
			if (response != null) {
				String string = EntityUtils.toString(response.getEntity(), "UTF-8");
				return JSON.parseObject(string);
			}
			httpclient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

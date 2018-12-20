package org.mutils.wechat.miniprogram;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.mutils.wechat.miniprogram.model.Code2SessionReturnModel;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.WechatMiniProgramConfig;
import cn.minsin.core.thirdpart.HttpClientFactory;


/**
 * 小程序相关接口
 * @author minsin
 *
 */
public class MiniProgramFunctions {
	
	/**
	 * 获取sessionkey和openid,一般用于小程序授权登录.
	 * @return
	 */
	public static Code2SessionReturnModel jscode2session(String code) {
		try {
			String url ="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
			
			WechatMiniProgramConfig miniProgramConfig = WechatMiniProgramConfig.wechatMiniProgramConfig;
			
			url = url.replace("APPID", miniProgramConfig.getAppid())
				 .replace("SECRET", miniProgramConfig.getAppSecret())
			     .replace("JSCODE", code);
			HttpGet get = HttpClientFactory.getGetMethod(url);
			
			CloseableHttpClient build = HttpClientBuilder.create().build();
			CloseableHttpResponse response = build.execute(get);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			response.close();
			build.close();
			System.out.println("response= " + string);
	        return JSON.parseObject(string,Code2SessionReturnModel.class);
		}catch (Exception e) {
			throw new MutilsException(e, "小程序使用code换取openid等信息失败");
		}
	}

}

package org.mutils.wechat.miniprogram;

import java.security.AlgorithmParameters;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.mutils.wechat.miniprogram.model.Code2SessionReturnModel;
import org.mutils.wechat.miniprogram.model.UserInfoModel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.WechatMiniProgramConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.tools.HttpClientUtil;


/**
 * 小程序相关接口
 * @author minsin
 *
 */
public class MiniProgramFunctions extends FunctionRule {
	
	/**
	 * 获取sessionkey和openid,一般用于小程序授权登录. 
	 * @param code 小程序获取的code
	 * @return
	 * @throws MutilsErrorException
	 */
	public static Code2SessionReturnModel jscode2session(String code)  throws MutilsErrorException  {
		try {
			String url ="https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
			
			WechatMiniProgramConfig miniProgramConfig = WechatMiniProgramConfig.wechatMiniProgramConfig;
			
			url = url.replace("APPID", miniProgramConfig.getAppid())
				 .replace("SECRET", miniProgramConfig.getAppSecret())
			     .replace("JSCODE", code);
			HttpGet get = HttpClientUtil.getGetMethod(url);
			
			CloseableHttpClient build = HttpClientBuilder.create().build();
			CloseableHttpResponse response = build.execute(get);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			response.close();
			build.close();
			slog.info("Code2SessionReturnModel string is {}",string);
	        return JSON.parseObject(string,Code2SessionReturnModel.class);
		}catch (Exception e) {
			throw new MutilsErrorException(e, "小程序使用code换取openid等信息失败");
		}
	}
	
	/**
	 * 解密用户敏感数据获取用户信息
	 * 
	 * @param sessionKey    数据进行加密签名的密钥
	 * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
	 * @param iv            加密算法的初始向量
	 */
	public static UserInfoModel getUserInfo(String encryptedData, String code, String iv) {
		try {
			Code2SessionReturnModel jscode2session = jscode2session(code);
			// 被加密的数据
			byte[] dataByte = Base64.decode(encryptedData);
			// 加密秘钥
			byte[] keyByte = Base64.decode(jscode2session.getSession_key());
			// 偏移量
			byte[] ivByte = Base64.decode(iv);

			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			if (null != resultByte && resultByte.length > 0) {
				String result = new String(resultByte, "UTF-8");
				return JSONObject.parseObject(result, UserInfoModel.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}

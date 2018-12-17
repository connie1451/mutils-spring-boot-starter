package cn.minsin.alipay;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 支付宝相关配置文件
 * 
 * @author minton
 * @email mintonzhang@163.com
 * @date 2018年4月26日
 */
public class AlipayConfig {
	// 1.商户appid
	public static String APPID;

	// 2.私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY;
	// 3.支付宝公钥
	public static String ALIPAY_PUBLIC_KEY;

	// 4.服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url;

	// 5.页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// 商户可以自定义同步跳转地址
	public static String return_url;

	// 6.请求网关地址
	public static String URL;

	// 7.编码
	public static String CHARSET = "UTF-8";

	// 8.返回格式
	public static String FORMAT = "json";

	// 9.加密类型
	public static String SIGNTYPE = "RSA2";

	public static String DOMAIN;

	/**
	 * 读取配置文件
	 */
	public static Properties properties = new Properties();

	static {
		try {
			String path = "alipay.properties";
			InputStream inStream = AlipayConfig.class.getClassLoader().getResourceAsStream(path);
			if (inStream == null) {
				inStream = AlipayConfig.class.getClassLoader().getResourceAsStream("/" + path);
			}
			properties.load(inStream);
			APPID = properties.getProperty("APPID");
			RSA_PRIVATE_KEY = properties.getProperty("RSA_PRIVATE_KEY");
			ALIPAY_PUBLIC_KEY = properties.getProperty("ALIPAY_PUBLIC_KEY");
			URL = properties.getProperty("URL");
			DOMAIN = properties.getProperty("DOMAIN");
			return_url = DOMAIN + "/pay/alipayNotify";
			notify_url = DOMAIN + "/pay/alipayNotify";
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package cn.minsin.core.thirdpart;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

public class HttpClientFactory {
	/**
	 * 获取SSL验证的HttpClient
	 * 
	 * @param httpClient
	 * @param useCert 是否用证书
	 * @param PARTNER 商户号
	 * @param certificatePath 证书地址
	 * @param certificateFormat 证书格式
	 *            是否使用证书 此处写死的微信证书
	 * @return
	 */
	public static CloseableHttpClient getSSLInstance(boolean useCert,String PARTNER,String certificatePath,String certificateFormat) {
		try {

			BasicHttpClientConnectionManager connManager;
			if (useCert) {
				// 证书
				char[] password = PARTNER.toCharArray();
				InputStream instream = new BufferedInputStream(new FileInputStream(certificatePath));
				KeyStore ks = KeyStore.getInstance(certificateFormat);
				ks.load(instream, password);

				// 实例化密钥库 & 初始化密钥工厂
				KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(ks, password);

				// 创建 SSLContext
				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(kmf.getKeyManagers(), null, new SecureRandom());

				SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext,
						new String[] { "TLSv1" }, null, new DefaultHostnameVerifier());

				connManager = new BasicHttpClientConnectionManager(RegistryBuilder.<ConnectionSocketFactory>create()
						.register("http", PlainConnectionSocketFactory.getSocketFactory())
						.register("https", sslConnectionSocketFactory).build(), null, null, null);
			} else {
				connManager = new BasicHttpClientConnectionManager(
						RegistryBuilder.<ConnectionSocketFactory>create()
								.register("http", PlainConnectionSocketFactory.getSocketFactory())
								.register("https", SSLConnectionSocketFactory.getSocketFactory()).build(),
						null, null, null);
			}
			return HttpClientBuilder.create().setConnectionManager(connManager).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HttpClientBuilder.create().build();
	}

	/**
	 * 模拟浏览器post提交
	 * 
	 * @param url
	 * @return
	 */
	public static HttpPost getPostMethod(String url) {
		HttpPost pmethod = new HttpPost(url); // 设置响应头信息
		pmethod.addHeader("Connection", "keep-alive");
		pmethod.addHeader("Accept", "*/*");
		pmethod.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		pmethod.addHeader("Host", "api.mch.weixin.qq.com");
		pmethod.addHeader("X-Requested-With", "XMLHttpRequest");
		pmethod.addHeader("Cache-Control", "max-age=0");
		pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		return pmethod;
	}

	/**
	 * 模拟浏览器GET提交
	 * 
	 * @param url
	 * @return
	 */
	public static HttpGet getGetMethod(String url) {
		HttpGet pmethod = new HttpGet(url);
		// 设置响应头信息
		pmethod.addHeader("Connection", "keep-alive");
		pmethod.addHeader("Cache-Control", "max-age=0");
		pmethod.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
		pmethod.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/;q=0.8");
		return pmethod;
	}

}

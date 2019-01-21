package com.connie.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

/**
 * 需要的jar
 * axis.jar
 * commonlib.jar
 * commons-beanutils-1.7.0.jar
 * commons-codec-1.3.jar
 * commons-discovery-0.2.jar
 * commons-io-1.4.jar
 * commons-lang-2.4.jar
 * commons-logging-1.1.1.jar
 */
public class ClientDemo {


	public  static  void traverseFolder2(String path) {

		File file = new File(path);
		if (file.exists()) {
			File[] files = file.listFiles();
			if (null == files || files.length == 0) {
				System.out.println("文件夹是空的!");
				return;
			} else {
				for (File file2 : files) {
					if (file2.isDirectory()) {
						System.out.println("文件夹:" + file2.getAbsolutePath());
						traverseFolder2(file2.getAbsolutePath());
					} else {
						System.out.println("" + file2.getName());
					}
				}
			}
		} else {
			System.out.println("文件不存在!");
		}
	}
	public static void main(String[] args) {

		String path = "E:\\桌面\\规范\\接口\\WebserviceClientDemo\\WebserviceClientDemo\\lib";
		traverseFolder2(path);


		String url = "http://localhost:8088/JwtSystem/RybdService?wsdl";
		List<String> list = new ArrayList<String>();
		String authStr = "9423423423";
		String jkly="NFC";
		String sfzmzl="居民身份证";
		String sfzmhm="43030119850724321";
		String jh="010011";
		String bmdm="xx市xxxzzz大队";
		
		list.add(authStr);
		list.add(jkly);
		list.add(sfzmzl);
		list.add(sfzmhm);
		list.add(jh);
		list.add(bmdm);
		try {
			String result = ClientDemo.postJavaVer(url, "rybdServcie", list);
			System.out.println("==========返回" + result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 调用java WebService通用接口接口
	 * 
	 * @param url
	 * @param method
	 * @param list
	 * @return
	 */
	public static String postJavaVer(String url, String method,
			List<String> list) throws Exception {
		String res = "";
		try {
			Service service = new Service();
			Call call =  (Call) service.createCall();
			call.setTimeout(90000);
			call.setTargetEndpointAddress(new URL(url));
			call.setOperationName(method);
			call.setUseSOAPAction(true);
			call.setSOAPActionURI(url);

			Object[] obj = new Object[list.size()];
			for (int i = 0; i < list.size(); i++) {
				obj[i] = list.get(i);
			}
			res = (String) call.invoke(obj);
			System.out.println("***********java WebService返回结果：[" + res + "]");
		} catch (Exception e) {
			e.printStackTrace();
			// 第三方服务异常
		}
		return res;
	}
}

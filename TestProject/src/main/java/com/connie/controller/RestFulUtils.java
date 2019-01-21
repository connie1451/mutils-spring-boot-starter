package com.connie.controller;

/**
 * @author zhength
 * @version 1.0.0
 * @email connie1451@163.com
 * @date 2019/1/21 16:40
 * @since 1.8
 */
import java.io.*;
import java.net.*;

/**
 * 创建时间：2017-04-13 上午9:17
 * 描述信息：-------------------------------------------
 * <p>
 * ------------------------------------------------------
 *
 * @author qianxp
 * @version 1.0.0.20170413
 */
public class RestFulUtils {
    public String load(String url,String query) throws Exception {

        URL restURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setAllowUserInteraction(false);
        PrintStream ps = new PrintStream(conn.getOutputStream());
        ps.print(query);
        ps.close();
        BufferedReader bReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line,resultStr="";
        while(null != (line=bReader.readLine())) {
            resultStr +=line;
        }
        bReader.close();
        return resultStr;

    }

    public static void main(String []args) {
        try {
            RestFulUtils restUtil = new RestFulUtils();
            String resultString = restUtil.load("http://localhost:8080/getMsg",
                    null);
            System.out.println(resultString);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}

package cn.minsin.core.tools;

import java.io.Closeable;

import com.alibaba.fastjson.util.IOUtils;

/**
 * 	文件流相关工具类
 * @author mintonzhang
 * @date 2019年1月15日
 */
public class IOUtil extends IOUtils {

	/**
	 * 	关闭流
	 * @param ios
	 */
	public static void close(Closeable... ios) {
		if(ios!=null) {
			for (Closeable x : ios) {
				IOUtils.close(x);
			}
		}
	}
}

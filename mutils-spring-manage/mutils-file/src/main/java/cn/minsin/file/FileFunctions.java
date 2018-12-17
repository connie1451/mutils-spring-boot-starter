package cn.minsin.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import cn.minsin.core.tools.DateUtil;

public class FileFunctions {

	/**
	 * path中不能出现\
	 * 
	 * @param path 2018年9月8日
	 * @author mintonzhang@163.com
	 */
	public static void checkPath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 保存文件 文件名生成策略： 如果文件存在 将会以-副本(xx)的形式进行自动增长 如果文件不存在 将会直接生成这样的规则
	 * 
	 * @param file
	 * @param b        可以从MultipartFile.getBytes()
	 * @param savePath D://upload//
	 * @return
	 * @throws Exception 2018年7月26日
	 * @author mintonzhang@163.com
	 */
	public static String saveFile(String saveDisk, String fileName, byte[] b) {
		try {
			String gName = fileName;
			String savePath = DateUtil.date2String(new Date(), "yyyyMMdd/");
			String path = saveDisk + savePath;
			// 定义上传路径
			checkPath(path);
			int count = 0;
			while (true) {
				String fileUrl = path + gName;
				boolean exists = new File(fileUrl).exists();
				if (exists) {
					int index = fileName.lastIndexOf(".");
					String extension = "";
					if (index > 0) {
						extension = fileName.substring(index, fileName.length());
					}
					count++;
					gName = fileName.replace(extension, "") + "-副本(" + count + ")" + extension;
					continue;
				}
				// 写入文件
				OutputStream out = new FileOutputStream(fileUrl);
				out.write(b);
				out.flush();
				out.close();
				return savePath + gName;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

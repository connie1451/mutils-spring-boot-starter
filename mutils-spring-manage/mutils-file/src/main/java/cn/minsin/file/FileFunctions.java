package cn.minsin.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import cn.minsin.core.init.FileConfig;
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
	 * 保存单个文件
	 * 
	 * @param file
	 * @return
	 */
	public static String saveFile(MultipartFile file) {
		try {

			String fileName = file.getOriginalFilename();
			String gName = fileName;
			String savePath = DateUtil.date2String(new Date(), "yyyyMMdd/");
			String path = FileConfig.fileConfig.getSaveDisk() + savePath;
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
				out.write(file.getBytes());
				out.flush();
				out.close();
				return savePath + gName;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 批量保存文件
	 * 
	 * @param file
	 * @return
	 */
	public static String[] saveFiles(MultipartFile[] file) {
		String[] result = new String[file.length];

		for (int i = 0; i < result.length; i++) {
			String saveFile = saveFile(file[i]);
			if (saveFile != null) {
				result[i] = saveFile;
			}
		}
		return result;
	}

	/**
	 * 判断是否超过最大文件数量
	 *
	 * @param files
	 * @param length     最大length
	 * @param isRequired 是否必填
	 * @return 2018年7月30日
	 */
	public final static boolean maxLength(MultipartFile[] files, int length, boolean isRequired) {
		if (files != null) {
			return files.length > length ? true : false;
		}
		return isRequired;
	}

	/**
	 * 判断是否大于限制的size
	 *
	 * @param files 文件
	 * @param mSize M做单位
	 * @return 大于限制大小返回true 反之false 2018年7月27日
	 */
	public final static boolean checkSize(MultipartFile[] files, int mSize) {
		Long limitSize = (mSize * 1024L * 1024L);
		Long sumSize = 0L;
		if (files != null && files.length > 0) {
			for (MultipartFile multipartFile : files) {
				sumSize += multipartFile.getSize();// size的单位为kb
			}
		}
		return sumSize > limitSize ? true : false;
	}

}

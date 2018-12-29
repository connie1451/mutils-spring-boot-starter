package cn.minsin.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.FileConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.tools.DateUtil;

public class FileFunctions extends FunctionRule {

	/**
	 * path中不能出现\
	 * 
	 * @param path 2018年9月8日
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
	public static String saveFile(MultipartFile file) throws MutilsErrorException {
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
			throw new MutilsErrorException(e, "保存文件失败");
		}
	}

	/**
	 * 批量保存文件 如果一个文件报错，不会阻断保存进程
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String[] saveFiles(MultipartFile[] file) throws MutilsException {
		String[] result = new String[file.length];
		for (int i = 0; i < result.length; i++) {
			String saveFile = null;
			try {
				saveFile = saveFile(file[i]);
			} catch (MutilsErrorException e) {
				slog.info("{} save failed.error message {}", file[i].getOriginalFilename(),e);
			}
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
	public static boolean maxLength(MultipartFile[] files, int length, boolean isRequired) {
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
	 * @return 大于限制大小返回true 反之false
	 */
	public static boolean checkSize(MultipartFile[] files, int mSize) {
		Long limitSize = (mSize * 1024L * 1024L);
		Long sumSize = 0L;
		if (files != null && files.length > 0) {
			for (MultipartFile multipartFile : files) {
				sumSize += multipartFile.getSize();// size的单位为kb
			}
		}
		return sumSize > limitSize ? true : false;
	}

	/**
	 * 
	 * @param f    源目录
	 * @param nf   目标目录
	 * @param flag 是否覆盖原文件夹 true代表把a文件夹整个复制过去，false只复制子文件夹及文件。
	 * @throws MutilsErrorException
	 */
	public static void copy(File f, File nf, boolean flag) throws MutilsErrorException {
		try {
			// 判断是否存在
			if (f.exists()) {
				// 判断是否是目录
				if (f.isDirectory()) {
					if (flag) {
						// 制定路径，以便原样输出
						nf = new File(nf + "/" + f.getName());
						// 判断文件夹是否存在，不存在就创建
						if (!nf.exists()) {
							nf.mkdirs();
						}
					}
					flag = true;
					// 获取文件夹下所有的文件及子文件夹
					File[] l = f.listFiles();
					// 判断是否为null
					if (null != l) {
						for (File ll : l) {
							// 循环递归调用
							copy(ll, nf, flag);
						}
					}
				} else {
					System.out.println("正在复制：" + f.getAbsolutePath());
					System.out.println("到：" + nf.getAbsolutePath() + "\\" + f.getName());
					// 获取输入流
					FileInputStream fis = new FileInputStream(f);
					// 获取输出流
					FileOutputStream fos = new FileOutputStream(nf + "/" + f.getName());

					int i;
					byte[] b = new byte[1024];
					// 读取文件
					while ((i = fis.read(b)) != -1) {
						// 写入文件，复制
						fos.write(b, 0, i);
					}
					fos.close();
					fis.close();
				}
			}
		} catch (Exception e) {
			throw new MutilsErrorException(e, "文件复制失败");
		}
	}

}

package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

public class FileConfig implements InitConfig {
	
	public static FileConfig fileConfig;
	

	//文件所在磁盘
	private String saveDisk;
	
	//项目访问地址 如果有项目名需要写上项目名,必须以/结尾
	private String serverUrl ="http://127.0.0.1:8080/";
	
	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getSaveDisk() {
		return saveDisk;
	}
	
	/**
	 * 文件存放盘符 eg:D:/upload/ or /root/minsin/upload/
	 */
	public void setSaveDisk(String saveDisk) {
		this.saveDisk = saveDisk;
	}

	@Override
	public void done() {
		if(StringUtil.isBlank(saveDisk)) {
			throw new MutilsException("文件上传  初始化失败,请检查配置文件是否正确.");
		}
		if(!saveDisk.endsWith("/")) {
			throw new MutilsException("文件上传  初始化失败,结尾必须为'/'");
		}
		fileConfig = this;
	}
}

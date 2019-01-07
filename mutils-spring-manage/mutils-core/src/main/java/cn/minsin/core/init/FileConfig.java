package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

public class FileConfig extends InitConfig {

	public static FileConfig fileConfig;

	//服务地址
	private String[] serverList;

	// 是否使用本地化保存 如果为true saveDisk,serverUrl 不能为空 如果为false serverList 不能为空
	private boolean isLocal = true;

	// 文件所在磁盘
	private String saveDisk;

	// 项目访问地址 如果有项目名需要写上项目名,必须以/结尾
	private String serverUrl = "http://127.0.0.1:8080/";

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

	public String[] getServerList() {
		return serverList;
	}

	public void setServerList(String[] serverList) {
		this.serverList = serverList;
	}
	
	public boolean isLocal() {
		return isLocal;
	}

	public void setLocal(boolean isLocal) {
		this.isLocal = isLocal;
	}

	@Override
	public void done() {
		log.info("\n如果isLocal为true,saveDisk和serverUrl是必填项.\n"
				+ "如果isLocal为false,serverList是必填项.\n"
				+ "If isLocal is true,saveDisk and serverUrl must not be null.\n"
				+ "If isLocal is false,serverList must not be null.");
		if (isLocal) {
			if (StringUtil.isBlank(saveDisk, serverUrl)) {
				throw new MutilsException("文件上传  初始化失败,请检查配置文件是否正确.");
			}
			if (!saveDisk.endsWith("/")) {
				throw new MutilsException("文件上传  初始化失败,结尾必须为'/'");
			}
		} else {
			if (serverList == null) {
				throw new MutilsException("文件上传  初始化失败,请检查配置文件是否正确.");
			}
			for (String string : serverList) {
				if (!string.endsWith("/")) {
					throw new MutilsException("文件上传服务地址  初始化失败,结尾必须为'/'");
				}
			}
		}
		fileConfig = this;
	}
}

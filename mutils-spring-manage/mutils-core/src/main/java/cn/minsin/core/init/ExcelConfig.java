package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

public class ExcelConfig  implements InitConfig{
	
	public  static ExcelConfig excelConfig;
	
	/**
	 * excel统一错误模板
	 */
	private String errorTemplateUrl;


	public String getErrorTemplateUrl() {
		return errorTemplateUrl;
	}


	public void setErrorTemplateUrl(String errorTemplateUrl) {
		this.errorTemplateUrl = errorTemplateUrl;
	}


	@Override
	public void done() {
		if(StringUtil.isBlank(errorTemplateUrl)) {
			throw new MutilsException("Excel 初始化失败,请检查配置文件是否正确.");
		}
		excelConfig = this;
	}
	
}

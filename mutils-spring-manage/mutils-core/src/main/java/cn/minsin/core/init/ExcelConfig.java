package cn.minsin.core.init;

import java.io.File;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

public class ExcelConfig  implements InitConfig{
	
	public  static ExcelConfig excelConfig;
	
	/**
	 * excel统一错误模板
	 */
	private String errorTemplatePath;
	
	private int errorTemplateSheetIndex=0;
	
	private int errorTemplateRowIndex=0;
	
	private int errorTemplateCellIndex=0;
	
	private String errorTemplateExportName="错误概要";
	

	public String getErrorTemplateExportName() {
		return errorTemplateExportName;
	}


	public void setErrorTemplateExportName(String errorTemplateExportName) {
		this.errorTemplateExportName = errorTemplateExportName;
	}


	public int getErrorTemplateSheetIndex() {
		return errorTemplateSheetIndex;
	}


	public void setErrorTemplateSheetIndex(int errorTemplateSheetIndex) {
		this.errorTemplateSheetIndex = errorTemplateSheetIndex;
	}


	public int getErrorTemplateRowIndex() {
		return errorTemplateRowIndex;
	}


	public void setErrorTemplateRowIndex(int errorTemplateRowIndex) {
		this.errorTemplateRowIndex = errorTemplateRowIndex;
	}


	public int getErrorTemplateCellIndex() {
		return errorTemplateCellIndex;
	}


	public void setErrorTemplateCellIndex(int errorTemplateCellIndex) {
		this.errorTemplateCellIndex = errorTemplateCellIndex;
	}


	public String getErrorTemplatePath() {
		return errorTemplatePath;
	}


	public void setErrorTemplatePath(String errorTemplatePath) {
		this.errorTemplatePath = errorTemplatePath;
	}


	@Override
	public void done() {
		if(StringUtil.isBlank(errorTemplatePath)) {
			throw new MutilsException("Excel 初始化失败,请检查配置文件是否正确.");
		}
		if(!new File(errorTemplatePath).exists()) {
			throw new MutilsException("Excel 初始化失败,错误模板文件:"+errorTemplatePath+" 不存在.");
		}
		excelConfig = this;
	}
	
}

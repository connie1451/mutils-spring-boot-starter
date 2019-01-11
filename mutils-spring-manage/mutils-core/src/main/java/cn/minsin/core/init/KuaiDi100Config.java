package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class KuaiDi100Config extends InitConfig {
	
	public  static KuaiDi100Config kuaiDi100Config;
	
	private String customer;
	
	private  String key;

	private  String url;

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	protected void done() {
		if(StringUtil.isBlank(customer,key,url)) {
			throw new MutilsException("快递100 初始化失败,请检查配置文件是否正确.");
		}
		kuaiDi100Config = this;
	}

	@Override
	protected void showInfomation() {
		// TODO Auto-generated method stub
		
	}
}



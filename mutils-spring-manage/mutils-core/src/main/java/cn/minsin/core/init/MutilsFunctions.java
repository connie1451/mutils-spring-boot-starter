package cn.minsin.core.init;

public enum MutilsFunctions {

	ALIPAY("ALIPAY",1), 
	WECHATPAY("WECHATPAY",2), 
	EXCEL("EXCEL",3), 
	FILE("FILE",4), 
	KUAIDI100("KUAIDI100",5),
	YIKETONG("YIKETONG",6), 
	DIANWODA("DIANWODA",7);

	private String name;
	
	private int index;

	private MutilsFunctions(String name,int index) {
		this.name = name;
		this.index = index;
	}
	public int getIndex() {
		return index;
	}
	public String getName() {
		return name;
	}

}

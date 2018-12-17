package cn.minsin.core.init;

public enum MutilsFunctions {

	ALIPAY("ALIPAY"), WECHATPAY("WECHATPAY"), EXCEL("EXCEL"), FILE("FILE"), KUAIDI100("KUAIDI100"),
	YIKETONG("YIKETONG"), DIANWODA("DIANWODA");

	private String name;

	private MutilsFunctions(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

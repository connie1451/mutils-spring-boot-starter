package cn.minsin.core.init;

public enum MutilsFunctions {

	ALIPAY("ALIPAY(支付宝)",1), 
	WECHATPAY("WECHATPAY(微信支付)",2), 
	EXCEL("EXCEL(excel)",3), 
	FILE("FILE(文件操作)",4), 
	KUAIDI100("KUAIDI100(快递100)",5),
	YIKETONG("YIKETONG(移客通)",6), 
	MINIPROGRAM("MINIPROGRAM(小程序)",8), 
	DIANWODA("DIANWODA(点我达)",7);

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

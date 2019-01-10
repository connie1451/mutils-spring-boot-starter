package cn.minsin.alipay.enum_type;

/**
 * 商家承担分期利率比例
 * @author mintonzhang
 * @date 2019年1月10日
 */
public enum HuaBeiSellerPercent {

	Percent_0(0),//代表用户承担所有分期费
	Percent_100(100);//代表商家承担分期费
	
	private int percent;

	private HuaBeiSellerPercent(int percent) {
		this.percent = percent;
	}

	public int getPercent() {
		return percent;
	}

	public void setPercent(int percent) {
		this.percent = percent;
	}
	
}

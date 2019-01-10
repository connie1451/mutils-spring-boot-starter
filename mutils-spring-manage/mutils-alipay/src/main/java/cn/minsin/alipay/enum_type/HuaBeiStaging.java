package cn.minsin.alipay.enum_type;

/**
 * 花呗分期选项
 * @author mintonzhang
 * @date 2019年1月10日
 */
public enum HuaBeiStaging {
	
	HB_3(3),
	HB_6(5),
	HB_12(12);
	
	private int stag;

	private HuaBeiStaging(int stag) {
		this.stag = stag;
	}

	public int getStag() {
		return stag;
	}

	public void setStag(int stag) {
		this.stag = stag;
	}

}

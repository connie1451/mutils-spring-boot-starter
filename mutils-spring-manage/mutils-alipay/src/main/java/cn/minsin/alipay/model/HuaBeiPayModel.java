package cn.minsin.alipay.model;

import cn.minsin.alipay.AlipayFunctions;
import cn.minsin.alipay.enum_type.HuaBeiSellerPercent;
import cn.minsin.alipay.enum_type.HuaBeiStaging;

/**
 * 分期支付的实体类 可以正常传入正常传入到 {@link AlipayFunctions }使用支付功能
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class HuaBeiPayModel extends PayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5887384829795015728L;

	private String enable_pay_channels = "pcreditpayInstallment";

	// 分期数 默认三期
	private int hb_fq_num = 3;
	// 商家承担比例 只能传入100或者0
	private int hb_fq_seller_percent = 0;

	public String getEnable_pay_channels() {
		return enable_pay_channels;
	}

	public int getHb_fq_num() {
		return hb_fq_num;
	}

	public int getHb_fq_seller_percent() {
		return hb_fq_seller_percent;
	}

	public void setHuaBeiStaging(HuaBeiStaging huaBeiStaging, HuaBeiSellerPercent huaBeiSellerPercent) {
		if (huaBeiStaging == null || huaBeiSellerPercent == null) {
			log.error(
					"HuaBeiStaging or huaBeiSellerPercent is null. so,the value of hb_fq_num is 3,hb_fq_seller_percent is 0.");
			return;
		}
		hb_fq_num = huaBeiStaging.getStag();
		hb_fq_seller_percent = huaBeiSellerPercent.getPercent();
	}

}

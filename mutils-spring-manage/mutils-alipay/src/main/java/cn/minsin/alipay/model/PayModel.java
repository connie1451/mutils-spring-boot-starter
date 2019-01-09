package cn.minsin.alipay.model;

import java.math.BigDecimal;

import cn.minsin.core.rule.ModelRule;

public class PayModel  extends ModelRule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8285357081519576784L;
	//简介
	private String subject;
	//订单号
	private String out_trade_no;
	//金额  两位小数 不能为负数
	private BigDecimal total_amount;
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	
	
	
}

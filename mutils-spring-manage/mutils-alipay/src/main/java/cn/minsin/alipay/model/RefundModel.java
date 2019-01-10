package cn.minsin.alipay.model;

import cn.minsin.core.rule.ModelRule;

/**
 * 支付宝退款
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class RefundModel extends ModelRule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1970408339324879973L;
	
	//退款订单号
	private String out_trade_no;
	//退款金额  需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
	private String refund_amount;
	//支付宝交易号
	private String trade_no;
	//随机数  不是全额退款，部分退款使用该参数
	private String out_request_no = String.valueOf(System.currentTimeMillis());
	//退款原因
	private String refund_reason ="正常退款";

	public String getOut_request_no() {
		return out_request_no;
	}

	public void setOut_request_no(String out_request_no) {
		this.out_request_no = out_request_no;
	}

	public String getRefund_reason() {
		return refund_reason;
	}

	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
}

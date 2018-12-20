package org.mutils.wechat.wechatpay.core.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.mutils.wechat.wechatpay.core.util.SignUtil;

import cn.minsin.core.init.WechatPayCoreConfig;
import cn.minsin.core.rule.ModelRule;


public abstract class BaseWeChatPayModel extends ModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6916140873435262221L;

	//小程序 app 公众号 需要填写对应的appid
	private String appid; 

	private String mch_id = WechatPayCoreConfig.wechatPayConfig.getPartnerId();

	private String nonce_str= String.valueOf(System.currentTimeMillis()); 

	private String sign; 

	private String sign_type = "MD5"; 

	private String body; 

	private String out_trade_no; 

	private int total_fee;

	private String spbill_create_ip = "192.168.1.1"; 

	private String notify_url;

	private String trade_type;
	
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	/**
	 * 生成xml
	 * @return
	 */
	public String toXml(String partnerKey) {
		SortedMap<String, String> treeMap = toTreeMap();
		String sign = SignUtil.createSign(treeMap, partnerKey);
		this.setSign(sign);
		StringBuffer sb = new StringBuffer("<xml>");
		for (Field field : getAllFields()) {
			try {
				if (Modifier.isStatic(field.getModifiers()))
					continue;
				field.setAccessible(true);
				Object object = field.get(this);
				if (object != null) {
					sb.append("<").append(field.getName()).append(">");
					sb.append(object).append("</").append(field.getName()).append(">");
				}
			} catch (Exception e) {
				continue;
			}
		}
		return sb.append("</xml>").toString();
	}
	
	public SortedMap<String, String>  toTreeMap() {
		SortedMap<String, String> tree = new TreeMap<>();
		
		for (Field field : getAllFields()) {
			int modifiers = field.getModifiers();
			if (Modifier.isStatic(modifiers) || !Modifier.isPrivate(modifiers)) {
				continue;
			}
			try {
				String key = field.getName();
				field.setAccessible(true);
				Object object = field.get(this);
				if(object!=null&&!"".equals(object.toString())) {
					tree.put(key, object.toString());
				}
			}catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return tree;
	}

	private Set<Field> getAllFields(){
		Field[] fields = this.getClass().getDeclaredFields();
		Field[] de = BaseWeChatPayModel.class.getDeclaredFields();
		Set<Field> hashset = new HashSet<>();
		hashset.addAll(Arrays.asList(fields));
		hashset.addAll(Arrays.asList(de));
		return hashset;
	}
}

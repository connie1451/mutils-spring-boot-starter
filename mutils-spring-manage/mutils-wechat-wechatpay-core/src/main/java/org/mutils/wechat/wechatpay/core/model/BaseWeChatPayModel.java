package org.mutils.wechat.wechatpay.core.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.SortedMap;

import org.mutils.wechat.wechatpay.core.util.SignUtil;

import cn.minsin.core.rule.ModelRule;


public abstract class BaseWeChatPayModel extends ModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6916140873435262221L;

	private String sign; 
	
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
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
				if (Modifier.isStatic(field.getModifiers())||Modifier.isFinal(field.getModifiers()))
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
}

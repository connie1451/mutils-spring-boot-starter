package org.mutils.wechat.wechatpay.core.model;

import java.lang.reflect.Field;
import java.util.SortedMap;

import org.mutils.wechat.wechatpay.core.util.SignUtil;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.rule.ModelRule;
import cn.minsin.core.tools.StringUtil;


public abstract class BaseWeChatPayModel extends ModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6916140873435262221L;

	@NotNull(value="签名  自动生成无须填写",notNull=false)
	private String sign; 
	
	public String getSign() {
		return sign;
	}

	protected void setSign(String sign) {
		this.sign = sign;
	}

	/**
	 * 生成xml
	 * @return
	 * @throws MutilsErrorException 
	 */
	public String toXml(String partnerKey) throws MutilsErrorException {
		SortedMap<String, String> treeMap = toTreeMap();
		String sign = SignUtil.createSign(treeMap, partnerKey);
		this.setSign(sign);
		StringBuffer sb = new StringBuffer("<xml>");
		for (Field field : getAllFields()) {
			try {
				if(verificationField(field)) continue;
				field.setAccessible(true);
				Object object = field.get(this);
				if (!StringUtil.isBlank(object)) {
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

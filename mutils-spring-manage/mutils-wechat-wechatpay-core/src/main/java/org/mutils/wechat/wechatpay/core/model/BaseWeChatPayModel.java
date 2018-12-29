package org.mutils.wechat.wechatpay.core.model;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

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

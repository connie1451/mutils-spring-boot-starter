package cn.minsin.core.rule;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;

/**
 * 第三方接口所需要继承的父类 这个抽象内不能定义字段
 * 
 * @author minsin
 *
 */
public abstract class ModelRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 57625408003186203L;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	protected SortedMap<String, String> toTreeMap() {
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
				if (object != null && !"".equals(object.toString())) {
					tree.put(key, object.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return tree;
	}

	protected Set<Field> getAllFields() {
		Field[] fields = this.getClass().getDeclaredFields();
		Field[] de = ModelRule.class.getDeclaredFields();
		Set<Field> hashset = new HashSet<>();
		hashset.addAll(Arrays.asList(fields));
		hashset.addAll(Arrays.asList(de));
		return hashset;
	}
}

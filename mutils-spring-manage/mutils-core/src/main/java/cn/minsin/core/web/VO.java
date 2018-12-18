/**
 * 
 */
package cn.minsin.core.web;

import java.io.Serializable;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * 动态构建Vo对象 替代的实体类中的VO
 * 
 * @author mintonzhang 2018年10月12日
 */
public class VO extends HashMap<String, Object> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6208106294465026862L;

	private VO() {
		super();
	}

	/**
	 * builder新对象
	 * 
	 * @return 2018年10月12日
	 * @author mintonzhang@163.com
	 */
	public static VO builder() {
		return new VO();
	}

	/**
	 * Object不能是一个对象
	 * 
	 * @param key
	 * @param value
	 * @return 2018年10月12日
	 * @author mintonzhang@163.com
	 */
	public VO put(String key, Object value) {
		super.put(key, value==null?"":value);
		return this;
	}

	public VO remove(String key) {
		super.remove(key);
		return this;
	}

	public Object getValue(String key) {
		return super.get(key);
	}

	/**
	 * 转换成指定对象
	 * @param clazz
	 * @return
	 * 2018年10月12日
	 * @author  mintonzhang@163.com
	 */
	public <T> T getObject(Class<T> clazz){
		try {
			return JSON.parseObject(this.toString(), clazz);
		}catch (Exception e) {
			throw new RuntimeException("类型转换失败");
		}
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

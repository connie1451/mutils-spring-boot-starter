package cn.minsin.core.rule;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

/**
 * 第三方接口所需要继承的父类
 * @author minsin
 *
 */
public  abstract class ModelRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 57625408003186203L;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}

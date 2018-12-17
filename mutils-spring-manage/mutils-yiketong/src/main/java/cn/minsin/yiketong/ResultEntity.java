package cn.minsin.yiketong;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;

public class ResultEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238311443023476513L;
	
	private int code;
	
	private String message;
	
	private Object data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	

}

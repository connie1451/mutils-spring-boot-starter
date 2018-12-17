/**
 * 
 */
package cn.minsin.core.web;

import java.io.Serializable;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

/**
 * 构建者模式的Result Eg：Result.builderMissParamter().data('name',"张三")
 * 
 * @author mintonzhang 2018年10月10日
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -8603085056620027210L;

	/**
	 * 操作成功
	 */
	public final static int SUCCESS = 200;
	
	/**
	 * 操作失败
	 */
	public final static int FAIL = 201;
	
	/**
	 * 缺少参数
	 */
	public final static int MISSPARAMTER = 300;

	/**
	 * 异常
	 */
	public final static int EXCEPTION = 404;

	private int code;

	private String msg;

	/**
	 * 单个对象
	 */
	private static Object data;

	/**
	 * 多个对象
	 */
	private static HashMap<String, Object> multidata;

	/**
	 * @return the multidata
	 */
	public  HashMap<String, Object> getMultidata() {
		return multidata;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public  void setData(Object data) {
		Result.data = data;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public Result() {
		super();
	}

	private Result(int code, String msg) {
		multidata = new HashMap<>();
		data = null;
		this.code = code;
		this.msg = msg;
	}

	/* 以下为构建方法 */
	public Result data(String key, Object value) {
		multidata.put(key,value);
		return this;
	}

	public Result data(Object value) {
		data = value;
		return this;
	}
	@Override
	public String toString() {
		if (multidata == null || multidata.isEmpty())
			multidata = null;
		return JSON.toJSONString(this);
	}

	/**
	 * code 来自Result中的 SUCCESS 或 EXCEPTION
	 * 
	 * @param code
	 * @param message
	 * @return 2018年10月10日
	 * @author mintonzhang@163.com
	 */
	public static Result builder(int code, String message) {
		return new Result(code, message);
	}

	/**
	 * code 来自Result中的 SUCCESS 或 EXCEPTION
	 * 
	 * @param message
	 *            this default is '操作成功'
	 * @return 2018年10月10日
	 * @author mintonzhang@163.com
	 */
	public static Result builderSuccess(String... message) {
		return new Result(SUCCESS, message != null && message.length > 0 ? message[0] : "操作成功");
	}

	/**
	 * code 来自Result中的 SUCCESS 或 EXCEPTION
	 * 
	 * @param message
	 *            this default is '服务器异常'
	 * @return 2018年10月10日
	 * @author mintonzhang@163.com
	 */
	public static Result builderException(String... message) {
		return new Result(EXCEPTION, message != null && message.length > 0 ? message[0] : "服务器异常,请重新登录试试");
	}

	/**
	 * 构建缺少参数的Result
	 * 
	 * @param message
	 *            this default is '缺少必要参数'
	 * @return 2018年10月10日
	 * @author mintonzhang@163.com
	 */
	public static Result builderMissParamter(String... message) {
		return new Result(MISSPARAMTER, message != null && message.length > 0 ? message[0] : "缺少必要参数,请刷新后重试");
	}
	
	/**
	 * 构建失败消息
	 * 
	 * @param message
	 *            this default is '缺少必要参数'
	 * @return 2018年10月10日
	 * @author mintonzhang@163.com
	 */
	public static Result builderFail(String... message) {
		return new Result(FAIL, message != null && message.length > 0 ? message[0] : "操作失败");
	}
}
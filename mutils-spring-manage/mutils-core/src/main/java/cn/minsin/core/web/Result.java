/**
 * 
 */
package cn.minsin.core.web;

import java.io.Serializable;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.exception.MutilsException;

/**
 * 构建者模式的Result Eg：Result.builderMissParamter().data('name',"张三")
 * 
 * @author mintonzhang 2018年10月10日
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -8603085056620027210L;

	protected Result() {
	}

	protected Result(ResultOptions options, String... msg) {
		if (options == null) {
			throw new MutilsException("ResultOptions must not be null.");
		}
		String rmsg = options.getMsg();
		if (msg != null && msg.length > 0) {
			rmsg = msg[0];
		}
		this.code = options.getCode();
		this.msg = rmsg;
	}

	private int code;

	private String msg;
	
	private Object data;

	private HashMap<String, Object> multidata = new HashMap<>();

	public HashMap<String, Object> getMultidata() {
		return multidata;
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
	
	/**
	 * 重置提示语和code
	 * @param option
	 * @return
	 */
	public Result resetOption(ResultOptions option,String... msg) {
		if (option == null) {
			throw new MutilsException("ResultOptions must not be null.");
		}
		String rmsg = option.getMsg();
		if (msg != null && msg.length > 0) {
			rmsg = msg[0];
		}
		this.code = option.getCode();
		this.msg = rmsg;
		return this;
	}

	/* 以下为构建方法 */
	public Result data(String key, Object value) {
		multidata.put(key, value);
		return this;
	}

	public Result data(Object value) {
		data = value;
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	/**
	 * 构造result
	 * 
	 * @param option  需要实现接口ResultOptions 的枚举 默认枚举是 DefaultResultOptions
	 * @param message 提示给前端的消息
	 * @return
	 */
	public static Result builder(ResultOptions option, String... message) {

		return new Result(option, message);
	}

	/**
	 * code 来自Result中的 SUCCESS 或 EXCEPTION
	 * 
	 * @param message this default is '操作成功'
	 * @return 2018年10月10日
	 */
	public static Result builderSuccess(String... msg) {
		return new Result(DefaultResultOptions.SUCCESS, msg);
	}

	/**
	 * code 来自Result中的 SUCCESS 或 EXCEPTION
	 * 
	 * @param message this default is '服务器异常'
	 * @return 2018年10月10日
	 */
	public static Result builderException(String... msg) {
		return new Result(DefaultResultOptions.EXCEPTION, msg);
	}

	/**
	 * 构建缺少参数的Result
	 * 
	 * @param message this default is '缺少必要参数'
	 * @return 2018年10月10日
	 */
	public static Result builderMissParamter(String... msg) {
		return new Result(DefaultResultOptions.MISSPARAMTER, msg);
	}

	/**
	 * 构建失败消息
	 * 
	 * @param message this default is '缺少必要参数'
	 * @return 2018年10月10日
	 */
	public static Result builderFail(String... msg) {
		return new Result(DefaultResultOptions.FAIL, msg);
	}

	/**
	 * 构建后端用户过期
	 * 
	 * @param message this default is '用户已失效'
	 * @return 2018年10月10日
	 */
	public static Result builderOutTime(String... msg) {
		return new Result(DefaultResultOptions.OUTTIME, msg);
	}
	
	public static void main(String[] args) {
		String string = builderOutTime()
				.data("123123","123123")
				.data
				.toString();
		System.out.println(string);
	}

}
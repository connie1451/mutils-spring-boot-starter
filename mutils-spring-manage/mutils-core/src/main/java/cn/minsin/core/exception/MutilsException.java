package cn.minsin.core.exception;

/**
 * Mutils 基础异常类
 * @author minsin
 *
 */
public class MutilsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1254579703396031354L;

	private String msg;

	public MutilsException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	
	public MutilsException(Throwable cause, String msg) {
		super(msg,cause);
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}
}

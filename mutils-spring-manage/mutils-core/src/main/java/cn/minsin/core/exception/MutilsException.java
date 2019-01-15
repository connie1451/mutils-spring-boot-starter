package cn.minsin.core.exception;

/**
 *  框架内发生一般异常时抛出
 * @author minsin
 *
 */
public class MutilsException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1254579703396031354L;

	public MutilsException(String msg) {
		super(msg);
	}
	
	
	public MutilsException(Throwable cause, String msg) {
		super(msg,cause);
	}
	public MutilsException(Throwable cause) {
		super(cause);
	}
}

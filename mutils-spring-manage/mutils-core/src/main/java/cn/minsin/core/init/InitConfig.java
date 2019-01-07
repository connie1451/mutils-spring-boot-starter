package cn.minsin.core.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InitConfig {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * 这个方法用于配置已经初始化完成之后执行
	 */
	public abstract void done();
}

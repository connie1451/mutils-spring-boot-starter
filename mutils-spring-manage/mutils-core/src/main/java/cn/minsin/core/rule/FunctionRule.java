package cn.minsin.core.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;

/**
 * Functions 所需要继承的基类
 * 
 * @author minsin
 *
 */
public abstract class FunctionRule {

	protected static final Logger log = LoggerFactory.getLogger(FunctionRule.class);
	
	protected static void checkConfig(String functions,InitConfig config) {
		if(config==null) {
			throw new MutilsException( functions+" cannot be used.Maybe you forgot to select or configure functions.");
		}
	}
	
}

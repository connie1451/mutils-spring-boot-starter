package cn.minsin.core.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Functions 所需要集成的基类
 * 
 * @author minsin
 *
 */
public abstract class FunctionRule {

	protected static final Logger slog = LoggerFactory.getLogger(FunctionRule.class);

	protected  Logger  log = LoggerFactory.getLogger(this.getClass());

}

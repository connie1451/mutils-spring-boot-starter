package cn.minsin.core.init.core;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class InitConfig {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private static Logger slog = LoggerFactory.getLogger(InitConfig.class);

	private final static Set<InitConfig> starters = new HashSet<>();
	
	private static boolean isInit =false;

	protected InitConfig() {
		starters.add(this);
	}
	public static void init(MutilsFunctions[] functions) {
		if (functions == null) {
			slog.info("Function initialized failed, Please check config.");
			return;
		}
		if(!isInit) {
			isInit = true;
		}else {
			slog.error("The function has been initialized and the initialization failed.");
			return;
		}
		slog.info("The selected function is about to be initialized.");
		
		for (MutilsFunctions mutilsFunctions : functions) {
			Type clazz = mutilsFunctions.getClazz();
			for (InitConfig config : starters) {
				Type class1 = config.getClass();
				if (clazz.equals(class1)) {
					String artifactId = mutilsFunctions.getArtifactId();
					config.showInfomation();
					config.done();
					slog.info("{} was initialize successful.", artifactId);
				}
			}
		}
	}

	protected abstract void done();

	protected abstract void showInfomation();
}

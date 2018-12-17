package cn.minsin.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.MutilsFunctions;

@Configuration
@EnableConfigurationProperties(MutilsProperties.class)
public class MutilsAutoConfigure {

	@Configuration
	static class MutilsParamConfiguration {
		private Logger log = LoggerFactory.getLogger(MutilsParamConfiguration.class);

		private final MutilsProperties properties;

		MutilsParamConfiguration(MutilsProperties properties) {
			super();
			this.properties = properties;
			init();
		}

		public void init() {
			MutilsFunctions[] functions = properties.getFunctions();
			Assert.notNull(functions,"Functions in properties must not be null.");
			for (MutilsFunctions mutilsFunctions : functions) {
				try {
					log.info("{} was initialize successful.",mutilsFunctions.getName());
				}catch (Exception e) {
					throw new MutilsException(mutilsFunctions.getName()+" was initialize failed,Please check properties.");
				}
			}
		}

		public MutilsProperties getProperties() {
			return properties;
		}
	}
}

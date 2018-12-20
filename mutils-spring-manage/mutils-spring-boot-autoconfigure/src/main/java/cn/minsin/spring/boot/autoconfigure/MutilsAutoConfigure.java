package cn.minsin.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.InitConfig;
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
				String name = mutilsFunctions.getName();
				int index = mutilsFunctions.getIndex();
				try {
					InitConfig init=null;
					switch (index) {
					case 1:
						init= properties.getAlipay();
						break;
					case 2:
						init= properties.getWechatPayCoreConfig();
						break;
					case 3:
						init= properties.getExcel();
						break;
					case 4:
						init= properties.getFile();
						break;
					case 5:
						init= properties.getKuaidi100();
						break;
					case 6:
						init= properties.getYiketong();
						break;
					case 7:
						init= properties.getDianwoda();
						break;
					case 8:
						init= properties.getWechatMiniProgramConfig();
						break;
					}
					init.done();
					log.info("{} was initialize successful.",name);
				}catch (Exception e) {
					throw new MutilsException(e,name+" was initialize failed,Please check properties.");
				}
			}
		}

		public MutilsProperties getProperties() {
			return properties;
		}
	}
}

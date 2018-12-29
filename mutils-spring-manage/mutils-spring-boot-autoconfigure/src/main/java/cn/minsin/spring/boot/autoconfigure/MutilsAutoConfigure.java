package cn.minsin.spring.boot.autoconfigure;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
			log.info("配置文件中的function参数配置之后才能初始化. Choosed functions will be initialize.");
			MutilsFunctions[] functions = properties.getFunctions();
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
						init= properties.getWechatPayCore();
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
						init= properties.getWechatMiniProgram();
						break;
					}
					init.done();
					log.info("{} 功能初始化成功.{} was initialize successful.",name,name);
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

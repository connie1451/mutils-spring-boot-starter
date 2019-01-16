package cn.minsin.core.init.core;

import cn.minsin.core.init.AlipayConfig;
import cn.minsin.core.init.ExcelConfig;
import cn.minsin.core.init.FileConfig;
import cn.minsin.core.init.KuaiDi100Config;
import cn.minsin.core.init.UnionPayConfig;
import cn.minsin.core.init.WechatAppConfig;
import cn.minsin.core.init.WechatJsapiConfig;
import cn.minsin.core.init.WechatMiniProgramConfig;
import cn.minsin.core.init.WechatPayCoreConfig;
import cn.minsin.core.init.YiKeTongConfig;

public enum MutilsFunctions {
	ALIPAY("mutils-alipay", AlipayConfig.class),
	EXCEL("mutils-excel", ExcelConfig.class),
	FILE("mutils-file", FileConfig.class), 
	KUAIDI100("mutils-kuaidi100",  KuaiDi100Config.class),
	YIKETONG("mutils-yiketong", YiKeTongConfig.class),
	WECHAT_APP("mutils-wechat-app",  WechatAppConfig.class),
	WECHAT_JSAPI("mutils-wechat-jsapi",  WechatJsapiConfig.class),
	WECHAT_MINIPROGRAM("mutils-wechat-miniprogram",  WechatMiniProgramConfig.class),
	WECHATPAY_CORE("mutils-wechat-wechatpay-core",  WechatPayCoreConfig.class),
	UNION_PAY("mutils-union-pay",  UnionPayConfig.class);

	private String artifactId;

	private Class<? extends InitConfig> clazz;

	private MutilsFunctions(String artifactId, Class<? extends InitConfig> clazz) {
		this.artifactId = artifactId;
		this.clazz = clazz;
	}

	public String getArtifactId() {
		return artifactId;
	}



	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}



	public Class<? extends InitConfig> getClazz() {
		return clazz;
	}
}

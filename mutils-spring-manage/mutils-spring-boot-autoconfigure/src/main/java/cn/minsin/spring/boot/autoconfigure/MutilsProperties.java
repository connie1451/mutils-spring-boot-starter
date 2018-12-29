package cn.minsin.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.init.MutilsFunctions;

@ConfigurationProperties(prefix = MutilsProperties.MUTILS_PREFIX)
public class MutilsProperties {
	
	public static final String MUTILS_PREFIX = "mutils";

	/**
	 * 选择需要初始化的功能,必填
	 */
	private MutilsFunctions[] functions;
	/**
	 * 支付宝初始化参数 {@code cn.minsin.core.init.AlipayConfig}
	 */
	private Alipay alipay = new Alipay();

	private Excel excel = new Excel();
	
	private File file = new File();
	
	private KuaiDi100 kuaidi100 = new KuaiDi100();
	
	private  WechatPayCore wechatPayCore = new WechatPayCore();
	
	private YiKeTong yiketong = new YiKeTong();
	
	private DianWoDa dianwoda = new DianWoDa();
	
	private WechatMiniProgram wechatMiniProgram = new WechatMiniProgram();
	

	public MutilsFunctions[] getFunctions() {
		return functions;
	}

	public void setFunctions(MutilsFunctions[] functions) {
		this.functions = functions;
	}

	public Alipay getAlipay() {
		return alipay;
	}

	public void setAlipay(Alipay alipay) {
		this.alipay = alipay;
	}

	public Excel getExcel() {
		return excel;
	}

	public void setExcel(Excel excel) {
		this.excel = excel;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public KuaiDi100 getKuaidi100() {
		return kuaidi100;
	}

	public void setKuaidi100(KuaiDi100 kuaidi100) {
		this.kuaidi100 = kuaidi100;
	}

	public WechatPayCore getWechatPayCore() {
		return wechatPayCore;
	}

	public void setWechatPayCore(WechatPayCore wechatPayCore) {
		this.wechatPayCore = wechatPayCore;
	}

	public YiKeTong getYiketong() {
		return yiketong;
	}

	public void setYiketong(YiKeTong yiketong) {
		this.yiketong = yiketong;
	}

	public DianWoDa getDianwoda() {
		return dianwoda;
	}

	public void setDianwoda(DianWoDa dianwoda) {
		this.dianwoda = dianwoda;
	}

	public WechatMiniProgram getWechatMiniProgram() {
		return wechatMiniProgram;
	}

	public void setWechatMiniProgram(WechatMiniProgram wechatMiniProgram) {
		this.wechatMiniProgram = wechatMiniProgram;
	}



	static class Excel extends cn.minsin.core.init.ExcelConfig {
		
		/**
		 * *此属性不用填写
		 * 这是对于ExcelConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.ExcelConfig} 查看各属性的意义
		 */
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
	static class Alipay extends cn.minsin.core.init.AlipayConfig {
		/**
		 * *此属性不用填写
		 * 这是对于AlipayConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.AlipayConfig} 查看各属性的意义
		 */
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
	}
	static class File extends cn.minsin.core.init.FileConfig{
		/**
		 * *此属性不用填写
		 * 这是对于FileConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.AlipayConfig} 查看各属性的意义
		 */
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	}
	static class KuaiDi100 extends cn.minsin.core.init.KuaiDi100Config{
		/**
		 * *此属性不用填写
		 * 这是对于KuaiDi100Config 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.KuaiDi100Config} 查看各属性的意义
		 */
		private String description;
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}
	static class WechatPayCore extends cn.minsin.core.init.WechatPayCoreConfig{
		/**
		 * *此属性不用填写
		 * 这是对于WechatPayConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.WechatPayCoreConfig} 查看各属性的意义
		 */
		private String description;
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}
	static class YiKeTong extends cn.minsin.core.init.YiKeTongConfig{
		/**
		 * *此属性不用填写
		 * 这是对于YiKeTongConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.YiKeTongConfig} 查看各属性的意义
		 */
		private String description;
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}
	static class DianWoDa extends cn.minsin.core.init.DianWoDaConfig{
		/**
		 * *此属性不用填写
		 * 这是对于DianWoDaConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.DianWoDaConfig} 查看各属性的意义
		 */
		private String description;
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}
	static class WechatMiniProgram extends cn.minsin.core.init.WechatMiniProgramConfig{
		/**
		 * *此属性不用填写
		 * 这是对于MiniProgramConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.WechatMiniProgramConfig} 查看各属性的意义
		 */
		private String description;
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

}

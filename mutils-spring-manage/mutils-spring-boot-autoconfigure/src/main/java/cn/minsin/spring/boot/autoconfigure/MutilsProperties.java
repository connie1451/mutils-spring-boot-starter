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
	private AlipayConfig alipay = new AlipayConfig();

	private ExcelConfig excel = new ExcelConfig();
	
	private FileConfig file = new FileConfig();
	
	private KuaiDi100Config kuaidi100 = new KuaiDi100Config();
	
	private WechatPayConfig wechatpay = new WechatPayConfig();
	
	private YiKeTongConfig yiketong = new YiKeTongConfig();
	
	private DianWoDaConfig dianwoda = new DianWoDaConfig();
	
	private MiniProgramConfig miniprogram = new MiniProgramConfig();
	

	public MiniProgramConfig getMiniprogram() {
		return miniprogram;
	}

	public void setMiniprogram(MiniProgramConfig miniprogram) {
		this.miniprogram = miniprogram;
	}

	public DianWoDaConfig getDianwoda() {
		return dianwoda;
	}

	public void setDianwoda(DianWoDaConfig dianwoda) {
		this.dianwoda = dianwoda;
	}

	public KuaiDi100Config getKuaidi100() {
		return kuaidi100;
	}

	public void setKuaidi100(KuaiDi100Config kuaidi100) {
		this.kuaidi100 = kuaidi100;
	}

	public WechatPayConfig getWechatpay() {
		return wechatpay;
	}

	public void setWechatpay(WechatPayConfig wechatpay) {
		this.wechatpay = wechatpay;
	}

	public YiKeTongConfig getYiketong() {
		return yiketong;
	}

	public void setYiketong(YiKeTongConfig yiketong) {
		this.yiketong = yiketong;
	}

	public ExcelConfig getExcel() {
		return excel;
	}

	public void setExcel(ExcelConfig excel) {
		this.excel = excel;
	}

	public MutilsFunctions[] getFunctions() {
		return functions;
	}

	public void setFunctions(MutilsFunctions[] functions) {
		this.functions = functions;
	}
	
	public AlipayConfig getAlipay() {
		return alipay;
	}

	public void setAlipay(AlipayConfig alipay) {
		this.alipay = alipay;
	}
	
	public FileConfig getFile() {
		return file;
	}

	public void setFile(FileConfig file) {
		this.file = file;
	}


	static class ExcelConfig extends cn.minsin.core.init.ExcelConfig {
		
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
	static class AlipayConfig extends cn.minsin.core.init.AlipayConfig {
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
	static class FileConfig extends cn.minsin.core.init.FileConfig{
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
	static class KuaiDi100Config extends cn.minsin.core.init.KuaiDi100Config{
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
	static class WechatPayConfig extends cn.minsin.core.init.WechatPayConfig{
		/**
		 * *此属性不用填写
		 * 这是对于WechatPayConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.WechatPayConfig} 查看各属性的意义
		 */
		private String description;
		
		public String getDescription() {
			return description;
		}
		
		public void setDescription(String description) {
			this.description = description;
		}
	}
	static class YiKeTongConfig extends cn.minsin.core.init.YiKeTongConfig{
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
	static class DianWoDaConfig extends cn.minsin.core.init.DianWoDaConfig{
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
	static class MiniProgramConfig extends cn.minsin.core.init.MiniProgramConfig{
		/**
		 * *此属性不用填写
		 * 这是对于MiniProgramConfig 的一个简单的描述.
		 * 请查看{@link cn.minsin.core.init.MiniProgramConfig} 查看各属性的意义
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

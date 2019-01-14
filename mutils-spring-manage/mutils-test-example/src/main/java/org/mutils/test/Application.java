package org.mutils.test;

import java.util.Map;

import org.mutils.wechat.miniprogram.WechatMiniProgramFunctions;
import org.mutils.wechat.miniprogram.model.MiniProgramOrderPayModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import cn.minsin.core.tools.StringUtil;


@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		MiniProgramOrderPayModel model = new MiniProgramOrderPayModel();
		model.setBody("购买");
		model.setTotal_fee(1);
		model.setOpenid("oiyGa5Ut9uBy7UHG1pqjTgh9fKuE");
		model.setOut_trade_no(StringUtil.getUUIDForLength(24));
		Map<String, String> createMiniProgramPayParamter = WechatMiniProgramFunctions.createMiniProgramPayParamter(model);
		System.out.println(createMiniProgramPayParamter);
		
		
	}

}

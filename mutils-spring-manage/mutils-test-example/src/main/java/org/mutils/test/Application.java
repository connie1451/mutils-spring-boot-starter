package org.mutils.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		PayModel payModel = new PayModel();
//		payModel.setTotal_amount(new BigDecimal("0.01"));
//		payModel.setSubject("123123");
//		AlipayResponse createAlipayParams = AlipayFunctions.createAlipayParams(payModel);
		
	//	MiniProgramOrderPayModel model = new MiniProgramOrderPayModel();
		
//		AppOrderPayModel model = new AppOrderPayModel();
//		model.setBody("下单");
//		model.setTotal_fee(1);
//		model.setOut_trade_no("ajwdjpowadp123123ojwadpowad");
//		WechatAppFunctions.createAppPayParamter(model);
		//WechatMiniProgramFunctions.createMiniProgramPayParamter(model);
	}
	
	
	

}

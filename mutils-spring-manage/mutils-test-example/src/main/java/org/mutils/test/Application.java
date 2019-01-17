package org.mutils.test;

import org.mutils.gexin.push.GexinPushFunctions;
import org.mutils.gexin.push.model.PushModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;



@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		PushModel model = new PushModel();
		model.setClientids(
				"598b79cd344fed940590f74162601d2c",
				"db8f8847a19ebe7850636397f326c252",
				"39d9a8a4909510afd16e3c15064aca56",
				"c5fbc3f33c908643917449d0c3512c81",
				"39d9a8a4909510afd16e3c15064aca56");
		model.setContent("我是曾慧慧");
		model.setTitle("我是曾慧慧");
		IPushResult pushMany = GexinPushFunctions.pushMany(model);
		System.out.println(JSON.toJSONString(pushMany));
//		PayModel payModel = new PayModel();
//		payModel.setTotal_amount(new BigDecimal("0.01"));
//		payModel.setSubject("123123");
//		payModel.setOut_trade_no("12333333333333");
//		AlipayResponse createAlipayParams = AlipayFunctions.createAlipayParams(payModel);
//		String subMsg = createAlipayParams.getBody();
//		System.out.println(subMsg);
	//	MiniProgramOrderPayModel model = new MiniProgramOrderPayModel();
		
//		AppOrderPayModel model = new AppOrderPayModel();
//		model.setBody("下单");
//		model.setTotal_fee(1);
//		model.setOut_trade_no("ajwdjpowadp123123ojwadpowad");
//		Map<String, String> createAppPayParamter = WechatAppFunctions.createAppPayParamter(model);
//		
//		InitConfig.init(null);
//		
//		ResultModel utilization = YiKeTongFunctions.utilization();
//		System.out.println(JSON.toJSONString(utilization));
	//	WechatMiniProgramFunctions.createMiniProgramPayParamter(model);
	}
}

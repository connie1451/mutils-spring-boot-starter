package cn.minsin.dianwoda;

import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.init.DianWoDaConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.web.VO;


/**
 * 测试环境的专用接口
 * 
 * @author minsin
 *
 */
public class TestEnvironmentFunctions  extends FunctionRule{

	/**
	 * 接受订单（测试接口仅测试环境有效）
	 * 
	 * @param order_original_id
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject orderAcceptedTest(String order_original_id) throws Exception {
		checkConfig("DianWoDaFunctions-TestEnvironmentFunctions",DianWoDaConfig.dianWoDaConfig);
		return DianWoDaFunctions.doSend("/api/v3/order-accepted-test.json",
				VO.builder().put("order_original_id", order_original_id));
	}

	/**
	 * 完成到店（测试接口仅测试环境有效）
	 * 
	 * @param order_original_id
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject orderArriveTest(String order_original_id) throws Exception {
		checkConfig("DianWoDaFunctions-TestEnvironmentFunctions",DianWoDaConfig.dianWoDaConfig);
		return DianWoDaFunctions.doSend("/api/v3/order-arrive-test.json",
				VO.builder().put("order_original_id", order_original_id));
	}


	/**
	 * 完成取货（测试接口仅测试环境有效）
	 * @param order_original_id
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject orderFetchTest(String order_original_id) throws Exception {
		checkConfig("DianWoDaFunctions-TestEnvironmentFunctions",DianWoDaConfig.dianWoDaConfig);
		return DianWoDaFunctions.doSend("/api/v3/order-fetch-test.json",
				VO.builder().put("order_original_id", order_original_id));
	}
	
	/**
	 * 完成配送（测试接口仅测试环境有效）
	 * @param order_original_id
	 * @return
	 * @throws Exception 
	 */
	public static JSONObject orderFinishTest(String order_original_id) throws Exception {
		checkConfig("DianWoDaFunctions-TestEnvironmentFunctions",DianWoDaConfig.dianWoDaConfig);
		return DianWoDaFunctions.doSend("/api/v3/order-finish-test.json",
				VO.builder().put("order_original_id", order_original_id));
	}
}

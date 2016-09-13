package com.zwh.social.api.sdk;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.pingplusplus.*;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.zwh.social.api.model.Pay;

/**
 * Ping++ 支付
 * 
 * @author zhaowh
 *
 */
public class PAY_Pingxx {
	/**
	 * pingpp 管理平台对应的应用 ID
	 */
	private static final String appId = "app_n9OWXPbrj9qPrL8q";
	/**
	 * pingpp 管理平台对应的 API key
	 */
	private static final String appKey = "sk_test_fDSqD4Sa5yLCiTGarDznDiv9"; //正式
	

	/**
	 * 创建 Charge
	 * 
	 * 创建 Charge 用户需要组装一个 map 对象作为参数传递给 Charge.create(); map
	 * 里面参数的具体说明请参考：https://pingxx.com/document/api#api-c-new
	 * 
	 * @param pay 支付凭据
	 * @return
	 */
	public static Charge getCharge(Pay pay) {
		Pingpp.apiKey = appKey;
		Charge charge = null;
		Map<String, Object> chargeMap = new HashMap<String, Object>();
		chargeMap.put("amount", pay.getAmount().intValue() * 100);
		chargeMap.put("currency", "cny");
		chargeMap.put("subject", pay.getSubject());
		chargeMap.put("body", pay.getBody());
		chargeMap.put("order_no", pay.getOrderNo());
		chargeMap.put("channel", pay.getChannel());
		chargeMap.put("client_ip", pay.getIp());
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);		
		chargeMap.put("app", app);
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("ordertype", pay.getOrderType().toString());
		chargeMap.put("metadata", initialMetadata);
		try {
			// 发起交易请求
			charge = Charge.create(chargeMap);
			System.out.println(charge);
		} catch (PingppException e) {
			e.printStackTrace();
		}
		return charge;
	}
	/**
	 * 根据Webhooks返回的数据判断是否支付成功，如果成功则返回订单号
	 * @param data
	 * @return
	 */
	public static Pay getPay(String data){
		Pay pay = new Pay();
		if(StringUtils.isNotEmpty(data)){
			data = data.replace("null{", "{");
			JSONObject charge = JSONObject.parseObject(data).getJSONObject("data").getJSONObject("object");
			pay.setOrderNo(charge.getString("order_no"));
		}
		return pay;
	}
}

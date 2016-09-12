package com.zwh.social.api.sdk;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.pingplusplus.*;
import com.pingplusplus.exception.PingppException;
import com.pingplusplus.model.Charge;
import com.zwh.social.api.Config;
import com.zwh.social.api.Constant;
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
	private static final String appKey_test = "sk_test_fDSqD4Sa5yLCiTGarDznDiv9"; //测试
	private static final String appKey_live = "sk_live_ceypkm7XWhW0GdDF2EBaaeJa"; //正式
	
	/**
	 * 根据运行模式判断使用那个key
	 * @return
	 */
	private static String getAppKey(){		
		return appKey_live;
	}
	

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
		Pingpp.apiKey = getAppKey();
		Charge charge = null;
		Map<String, Object> chargeMap = new HashMap<String, Object>();
		chargeMap.put("amount", pay.getAmount().intValue());
		chargeMap.put("currency", "cny");
		chargeMap.put("subject", pay.getOrdersubject());
		if(StringUtils.isNotEmpty(pay.getOrderbody())){
			chargeMap.put("body", pay.getOrderbody());
		}else{
			chargeMap.put("body", "滑雪助手");
		}
		chargeMap.put("order_no", pay.getOrderno());
		chargeMap.put("channel", pay.getChannel());
		chargeMap.put("client_ip", pay.getClientip());
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		chargeMap.put("app", app);
		Map<String, String> initialMetadata = new HashMap<String, String>();
		initialMetadata.put("orderid", pay.getOrderid().toString());
		initialMetadata.put("ordertype", pay.getOrdertype().toString());
		chargeMap.put("metadata", initialMetadata);
		if(pay.getChannel().equals("wx_pub")){ //微信公众号支付需要提供openid
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("open_id", pay.getOpenid());
			chargeMap.put("extra", extra);
		}else if(pay.getChannel().equals("wx_pub_qr")){ //微信扫码支付需要提供product_id
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("product_id", pay.getOrderid().toString());
			chargeMap.put("extra", extra);
		}else if(pay.getChannel().equals("alipay_pc_direct")){ //支付宝PC支付
			Map<String, String> extra = new HashMap<String, String>();
			extra.put("success_url", pay.getSuccessurl());
			chargeMap.put("extra", extra);
		}
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
			String paid = charge.getString("paid");
			if(paid.equals("true")){
				//pay.setOrderstate(Constant.ORDER_STATE_PAID);
			}else{
				//pay.setOrderstate(Constant.ORDER_STATE_FAILED);
			}
			pay.setOrderno(charge.getString("order_no"));
			JSONObject metadata = charge.getJSONObject("metadata");
			pay.setOrderid(Integer.valueOf(metadata.getString("orderid")));
			pay.setOrdertype(Integer.valueOf(metadata.getString("ordertype")));
		}
		return pay;
	}
}

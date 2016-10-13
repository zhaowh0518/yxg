package com.zwh.social.api.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zwh.social.api.Constant;
import com.zwh.social.api.ResultMsg;
import com.zwh.social.api.model.Pay;
import com.zwh.social.api.sdk.PAY_Pingxx;
import com.zwh.social.api.service.IPayService;
import com.zwh.social.api.util.HttpUtil;

/**
 * 支付
 * 
 * @author zhaowh
 *
 */
@Controller
@RequestMapping("/pay")
public class PayController {
	@Autowired
	private IPayService payService;

	Logger logger = Logger.getLogger(PayController.class);
	
	
	/**
	 * 包月订单，返回charge对象 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/month")
	@ResponseBody
	public JSONObject month(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			Integer userId = params.getInteger("userId");
			String channel = params.getString("channel");
			String ip = request.getRemoteAddr();
			Double amount = 100.0;
			String orderNo = payService.getOrderNo();
			String subject = "包月订单";
			String body = "";
			
			Pay pay = getPay(userId, channel, ip, amount, orderNo, subject,	body, Constant.PAY_TYPE_MONTH);
			
			payService.add(pay);
			
			return ResultMsg.success(PAY_Pingxx.getCharge(pay));
		} catch (Exception ex) {
			logger.info("PayController/month : " + ex.getMessage());
			ex.printStackTrace();
			return ResultMsg.error(ex);
		}
	}
	
	/**
	 * VIP订单，返回charge对象 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/vip")
	@ResponseBody
	public JSONObject vip(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			Integer userId = params.getInteger("userId");
			String channel = params.getString("channel");
			String ip = request.getRemoteAddr();
			Double amount = 300.0;
			String orderNo = payService.getOrderNo();
			String subject = "VIP订单";
			String body = "";
			
			Pay pay = getPay(userId, channel, ip, amount, orderNo, subject,	body, Constant.PAY_TYPE_VIP);
			
			payService.add(pay);
			
			return ResultMsg.success(PAY_Pingxx.getCharge(pay));
		} catch (Exception ex) {
			logger.info("PayController/vip : " + ex.getMessage());
			ex.printStackTrace();
			return ResultMsg.error(ex);
		}
	}

	/**
	 * 支付结果同步 你需要在 Ping++ 的管理平台里填写的 Webhooks 通知地址处接收 Webhooks 通知（详见管理平台使用指南），
	 * 你的服务器需要监听这个地址并且接收 Webhooks 通知，接收到 Webhooks 通知后需给 Ping++ 返回服务器状态 2xx。 此时的
	 * event 类型是 charge.succeeded，其字段 data 包含了 object 字段， object 字段的值是一个 Charge
	 * 对象, 如果 Charge 对象中 paid 字段值为 true，则表示订单支付完成。 若你的服务器未正确返回 2xx，Ping++ 会在 25
	 * 小时内向商户服务器最多发送 8 次 Webhooks 通知， 时间间隔分别为
	 * 2min、10min、10min、1h、2h、6h、15h，直到用户向 Ping++ 返回服务器状态 2xx 或者超过最大重发次数为止。
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/paid")
	@ResponseBody
	public String paid(HttpServletRequest request, HttpServletResponse response) {
		try {
			String data = HttpUtil.readPostData(request);
			Pay pay = PAY_Pingxx.getPay(data);
			pay.setState(Constant.ORDER_STATE_PAID);
			payService.update(pay);
			response.setStatus(200);
			return "";
		} catch (Exception ex) {
			logger.info("PayController/paid : " + ex.getMessage());
			ex.printStackTrace();
			response.setStatus(300);
			return "";
		}
	}
	

	private Pay getPay(Integer userId, String channel, String ip,
			Double amount, String orderNo, String subject, String body,Integer type) {
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "127.0.0.1";
		}
		Pay pay  = new Pay();
		pay.setAmount(amount);
		pay.setSubject(subject);
		pay.setBody(body);
		pay.setChannel(channel);
		pay.setCreateDate(new Date());
		pay.setIp(ip);
		pay.setOrderNo(orderNo);
		pay.setState(Constant.ORDER_STATE_READY);
		pay.setUserId(userId);
		pay.setOrderType(type);
		return pay;
	}

}

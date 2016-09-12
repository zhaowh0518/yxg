package com.zwh.social.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.zwh.social.api.Config;
import com.zwh.social.api.ResultMsg;
import com.zwh.social.api.model.User;
import com.zwh.social.api.service.impl.UserService;
import com.zwh.social.api.util.HttpUtil;
import com.zwh.social.api.util.RandomUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	Logger logger = Logger.getLogger(UserController.class);

	/***
	 * 注册
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			Integer age = null;
			Integer gender = null;
			String username = RandomUtil.getRandomDigit(9);
			String password = RandomUtil.getRandomDigit(6);
			String ip = request.getRemoteAddr();
			String fid = null; //渠道号
			String product = null;//产品
			String pid = null; //手机串号
			JSONObject platformInfo = params.getJSONObject("platformInfo");
			if(null != platformInfo){
				fid = platformInfo.getString("fid");
				product = platformInfo.getString("product");
				pid = platformInfo.getString("pid");
			}
			//计算年龄
			if(null != params.get("age")){
				age = params.getInteger("age");
			}
			if(null != params.get("birthday")){
				age = Years.yearsBetween(
						DateTime.parse(params.getString("birthday"), DateTimeFormat.mediumDate()),
						DateTime.now()).getYears();
			}
			//性别 0男 1女
			if(null != params.get("gender")){
				if(params.getString("gender").equals("1")){
					gender = 1;
				}else{
					gender = 0;
				}
			}
			
			User user = new User();
			user.setAge(age);
			user.setSex(gender);
			user.setUsername(username);
			user.setPassword(password);
			user.setFromChannel(fid);
			
			int userId = userService.add(user);
			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("fromChannel", fid);
			result.put("code", 1);
			result.put("isQaSwitch", 0);
			result.put("token", "");
			result.put("isShowService", 0);
			result.put("regTime", user.getRegDate());
			result.put("sessionId", userId);
			result.put("isNewGirlReg", 0);
			result.put("isShowTweetMsg",0);
			
			result.put("user", null);
			
			return ResultMsg.json(result);
			
		} catch (Exception ex) {
			logger.info("UserController/register : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
	
	/***
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			String account = params.getString("account");
			String password = params.getString("password");
			String ip = request.getRemoteAddr();
			String fid = null; //渠道号
			String product = null;//产品
			JSONObject platformInfo = params.getJSONObject("platformInfo");
			if(null != platformInfo){
				fid = platformInfo.getString("fid");
				product = platformInfo.getString("product");
			}
			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("fromChannel", fid);
			result.put("code", 1);
			result.put("isQaSwitch", 0);
			result.put("token", "");
			result.put("isShowService", 0);
			//result.put("regTime", user.getRegDate());
			//result.put("sessionId", userId);
			result.put("isNewGirlReg", 0);
			result.put("isShowTweetMsg",0);
			
			result.put("user", null);
			
			return ResultMsg.json(result);
			
		} catch (Exception ex) {
			logger.info("UserController/login : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
}

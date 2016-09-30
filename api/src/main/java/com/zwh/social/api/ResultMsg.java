package com.zwh.social.api;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
/**
 * 返回客户端的消息封装
 *
 */
public class ResultMsg {	
	/***
	 * 返回错误信息
	 * @param code
	 * @param msg
	 * @param response
	 */
	public static void error(int code, String msg,HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		response.setHeader("errorCode", String.valueOf(code));
		try {
			response.setCharacterEncoding("UTF-8");
			response.setHeader("errorCode", String.valueOf(code));
			if(StringUtils.isNotEmpty(msg)){
				response.setHeader("errorMsg",  URLEncoder.encode(msg, "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/***
	 * 返回JSON格式的数据
	 * @param obj
	 * @return
	 */
	public static String json(Object obj) {
		return JSONObject.toJSONString(obj);
	}
}

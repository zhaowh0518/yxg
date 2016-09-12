package com.zwh.social.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.zwh.social.api.view.YuanfenView;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private UserService userService;

	Logger logger = Logger.getLogger(SearchController.class);

	/***
	 * 获取缘分数据 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/getYuanfen")
	@ResponseBody
	public String getYuanfen(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			Integer pageNum = 0;			
			Integer pageSize = 90;			
			if(null != params){
				pageNum = params.getInteger("pageNum");
				pageSize = params.getInteger("pageSize");
			}
			List<User> userList = userService.getAll();
			if(null != userList && userList.size() > 0){
				Map<String,Object> result = new HashMap<String,Object>();
				result.put("totalCount", userList.size());
				result.put("pageSize", pageSize);
				result.put("pageNum", pageNum);
				List<YuanfenView> listYuanfen = new ArrayList<YuanfenView>();
				for(User user : userList){
					listYuanfen.add(YuanfenView.getYuanfenView(user));
				}
				result.put("listYuanfen", listYuanfen);
				return ResultMsg.json(result);
			}
			
		} catch (Exception ex) {
			logger.info("SearchController/register : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
	/***
	 * 搜索用户
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/searchUser")
	@ResponseBody
	public String searchUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			
			
		} catch (Exception ex) {
			logger.info("SearchController/searchUser : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
}

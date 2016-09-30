package com.zwh.social.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zwh.social.api.ResultMsg;
import com.zwh.social.api.model.User;
import com.zwh.social.api.service.impl.UserService;
import com.zwh.social.api.util.HttpUtil;

@Controller
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private UserService userService;

	Logger logger = Logger.getLogger(SearchController.class);

	/***
	 * 推荐
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public String list(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			User term = JSON.parseObject(params.getString("term"), User.class);
			Integer pageNum = params.getInteger("pageNum");
			Integer pageSize = params.getInteger("pageSize");
			//客户端没有传省份，取用户基本信息中的省份
			if(null == term.getInhabitProvinceId() && null != term.getUserId()){
				User user = userService.get(term.getUserId());
				term.setInhabitProvinceId(user.getInhabitProvinceId());
			}
			List<User> userList = userService.search(term);			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("count", userList.size());
			result.put("list", pager(userList,pageNum,pageSize));
			return ResultMsg.json(result);
			
		} catch (Exception ex) {
			logger.info("SearchController/list : " + ex.getMessage());
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
	@RequestMapping("/user")
	@ResponseBody
	public String user(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			User term = JSON.parseObject(params.getString("term"), User.class);
			Integer pageNum = params.getInteger("pageNum");
			Integer pageSize = params.getInteger("pageSize");
			List<User> userList = userService.search(term);			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("count", userList.size());
			result.put("list", pager(userList,pageNum,pageSize));
			return ResultMsg.json(result);			
			
		} catch (Exception ex) {
			logger.info("SearchController/user : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
	/**
	 * List 分页
	 * @param data
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	private List pager(List data,int pageNum,int pageSize){
		int fromIndex = (pageNum - 1) * pageSize;  
        if (fromIndex >= data.size()) {  
            return null;  
        }  
  
        int toIndex = pageNum * pageSize;  
        if (toIndex >= data.size()) {  
            toIndex = data.size();  
        }  
		return data.subList(fromIndex, toIndex);
	}
}

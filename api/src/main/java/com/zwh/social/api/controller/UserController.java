package com.zwh.social.api.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zwh.social.api.ResultMsg;
import com.zwh.social.api.model.User;
import com.zwh.social.api.model.UserPhoto;
import com.zwh.social.api.service.impl.UserService;
import com.zwh.social.api.util.HttpUtil;
import com.zwh.social.api.util.UploadUtil;

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
			String channel = params.getString("channel");
			Integer sex = params.getInteger("sex");			
			
			User user = new User();
			user.setChannel(channel);
			user.setSex(sex);
			
			int userId = userService.add(user);
			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("userId", userId);			
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
			String username = params.getString("username");
			String pwd = params.getString("pwd");
			Integer userId = userService.login(username, pwd);
			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("userId", userId);
			return ResultMsg.json(result);
			
		} catch (Exception ex) {
			logger.info("UserController/login : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
	/***
	 * 个人信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/detail")
	@ResponseBody
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			Map<String,Object> result = new HashMap<String,Object>();
			Integer userId = params.getInteger("userId");
			if(null != userId && userId > 0){
				User user = userService.get(userId);
				if(null != user){
					result.put("user", user);
				}
			}
			return ResultMsg.json(result);
			
		} catch (Exception ex) {
			logger.info("UserController/detail : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
	/***
	 * 更新个人资料
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String update(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);
			User user = JSON.parseObject(params.toJSONString(), User.class);			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("result", 0);
			if(null != user){
				if(userService.update(user) > 0){
					result.put("result", 1);
				}
			}
			return ResultMsg.json(result);
			
		} catch (Exception ex) {
			logger.info("UserController/update : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
	/***
	 * 上传图片
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public String upload(HttpServletRequest request, HttpServletResponse response) {
		try {
			JSONObject params = HttpUtil.getRequestJson(request);		
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("result", 0);
			Integer userId = params.getInteger("userId");
			Integer type = params.getInteger("type"); //0 头像；1相册
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			if(null != multipartRequest){
				MultipartFile img = multipartRequest.getFile("img");
				if(img==null){
					Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
					Set<Entry<String, MultipartFile>> set=fileMap.entrySet();
					Iterator<Entry<String, MultipartFile>> it=set.iterator();
					while(it.hasNext()){
						Entry<String, MultipartFile> entry = it.next();
					    img = (MultipartFile) entry.getValue();
					    break;
					}
				}
				if(type == 0){
					String head = UploadUtil.upload(request, img, UploadUtil.PATH_USER_HEAD);
					if(StringUtils.isNotEmpty(head)){
						User user = new User();
						user.setUserId(userId);
						user.setHead(head);
						if(userService.update(user) > 0){
							result.put("result", 1);
						}
					}
				}else{
					String photo = UploadUtil.upload(request, img, UploadUtil.PATH_USER_PHOTO);
					if(StringUtils.isNotEmpty(photo)){
						UserPhoto userPhoto = new UserPhoto();
						userPhoto.setUserId(userId);
						userPhoto.setUrl(photo);
						if(userService.addUserPhoto(userPhoto) > 0){
							result.put("result", 1);
						}
					}
				}
			}else{
				ResultMsg.error(0,"空文件", response);
				return "";
			}			
			return ResultMsg.json(result);
			
		} catch (Exception ex) {
			logger.info("UserController/upload : " + ex.getMessage());
			ex.printStackTrace();			
			ResultMsg.error(0, ex.getMessage(), response);
		}
		return "";
	}
}

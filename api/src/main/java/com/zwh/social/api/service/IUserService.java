package com.zwh.social.api.service;

import java.util.List;

import com.zwh.social.api.model.User;
import com.zwh.social.api.model.UserPhoto;

public interface IUserService {
	/**
	 * 搜索
	 * @param term 搜索条件
	 * @return
	 */
	List<User> search(User term);
	/***
	 * 某个性别的所有数据
	 * @param sex
	 * @return
	 */
	List<User> getUserList(Integer sex);
	/***
	 * 获取某个用户的详细信息
	 * @param userId
	 * @return
	 */
	User get(Integer userId);
	/***
	 * 登录
	 * @param username
	 * @param pwd
	 * @return
	 */
	int login(String username,String pwd);
	/**
	 * 新增用户
	 * @param record
	 * @return
	 */
	int add(User record);
	/***
	 * 更新用户
	 * @param record
	 * @return
	 */
	int update(User record);
	/**
	 * 添加相册
	 * @param record
	 * @return
	 */
	int addUserPhoto(UserPhoto record);
}

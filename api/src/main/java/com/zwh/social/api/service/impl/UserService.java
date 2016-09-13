package com.zwh.social.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.zwh.social.api.mapper.UserMapper;
import com.zwh.social.api.mapper.UserPhotoMapper;
import com.zwh.social.api.model.User;
import com.zwh.social.api.model.UserPhoto;
import com.zwh.social.api.service.IUserService;
import com.zwh.social.api.util.DateUtil;

@Service
@Repository
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserPhotoMapper userPhotoMapper;

	@Override
	public int add(User record) {
		String currentDate = DateUtil.getNow();
		record.setDateReg(currentDate);
		record.setDataLastLogin(currentDate);
		userMapper.add(record);
		return record.getUserId();
	}

	@Override
	public User get(Integer userId) {
		User user = userMapper.get(userId);
		List<UserPhoto> photoList = userPhotoMapper.getUserPhotoList(userId);
		if(null != photoList && photoList.size() > 0){
			user.setPhotoCount(photoList.size());
			user.setPhotoList(photoList);
		}
		return user;
	}

	@Override
	public int update(User record) {
		return userMapper.update(record);
	}

	@Override
	public List<User> getUserList(Integer sex) {
		return userMapper.getUserList(sex);
	}

	@Override
	public int login(String username, String pwd) {
		User user = userMapper.login(username, pwd);
		if(null == user){
			return -1;
		}else{
			user.setDataLastLogin(DateUtil.getNow());
			return user.getUserId();
		}
	}

	@Override
	public List<User> search(User term) {
		return userMapper.search(term);
	}

	@Override
	public int addUserPhoto(UserPhoto record) {
		return userPhotoMapper.add(record);
	}
	
}

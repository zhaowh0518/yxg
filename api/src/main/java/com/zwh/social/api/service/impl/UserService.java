package com.zwh.social.api.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.zwh.social.api.mapper.UserMapper;
import com.zwh.social.api.model.User;
import com.zwh.social.api.service.IUserService;

@Service
@Repository
public class UserService implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int add(User record) {
		record.setRegDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		userMapper.add(record);
		return record.getUserid();
	}

	@Override
	public User get(Integer userid) {
		return userMapper.get(userid);
	}

	@Override
	public int update(User record) {
		return userMapper.update(record);
	}

	@Override
	public List<User> getAll() {
		return userMapper.getAll();
	}
	
}

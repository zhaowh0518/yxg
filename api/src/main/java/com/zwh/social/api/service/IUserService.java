package com.zwh.social.api.service;

import java.util.List;

import com.zwh.social.api.model.User;

public interface IUserService {
	List<User> getAll();
	
	int add(User record);

    User get(Integer userid);

    int update(User record);
}

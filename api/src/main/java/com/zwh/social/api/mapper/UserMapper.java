package com.zwh.social.api.mapper;

import java.util.List;

import com.zwh.social.api.model.User;

public interface UserMapper {
	List<User> getAll();
	
    int add(User record);

    User get(Integer userid);

    int update(User record);
}
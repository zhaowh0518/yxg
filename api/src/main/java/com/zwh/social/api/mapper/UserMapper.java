package com.zwh.social.api.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwh.social.api.model.User;

public interface UserMapper {
	List<User> search(User term);

	List<User> getUserList(Integer sex);

	User get(Integer userId);

	User login(@Param("username") String username, @Param("pwd") String pwd);

	int add(User record);

	int update(User record);
}
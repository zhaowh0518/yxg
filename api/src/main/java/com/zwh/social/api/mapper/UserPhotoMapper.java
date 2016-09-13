package com.zwh.social.api.mapper;

import java.util.List;

import com.zwh.social.api.model.UserPhoto;

public interface UserPhotoMapper {
    List<UserPhoto> getUserPhotoList(Integer userId);
    List<UserPhoto> getReviewList(Integer userId);
    int add(UserPhoto record);
    int update(UserPhoto record);
}
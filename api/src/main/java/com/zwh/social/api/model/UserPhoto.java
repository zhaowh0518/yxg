package com.zwh.social.api.model;

import org.apache.commons.lang.StringUtils;

public class UserPhoto {
    private Integer userPhotoId;

    private Integer userId;

    private Integer sex;

    private Integer age;

    private String url;

    private String thumbnail;

    private Integer size;

    private Integer width;

    private Integer height;

    private Integer state;

    private String createDate;

    public Integer getUserPhotoId() {
        return userPhotoId;
    }

    public void setUserPhotoId(Integer userPhotoId) {
        this.userPhotoId = userPhotoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
    	if(StringUtils.isNotEmpty(url)){
    		return url + "@50p";
    	}
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
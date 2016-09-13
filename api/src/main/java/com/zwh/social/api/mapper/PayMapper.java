package com.zwh.social.api.mapper;

import com.zwh.social.api.model.Pay;

public interface PayMapper {
    int add(Pay record);
    
    int update(Pay record);
}
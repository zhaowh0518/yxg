package com.zwh.social.api.service.impl;

import java.util.Date;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.zwh.social.api.mapper.PayMapper;
import com.zwh.social.api.model.Pay;
import com.zwh.social.api.service.IPayService;

@Service
@Repository
public class PayService implements IPayService {
	@Autowired
	private PayMapper payMapper;
	

	@Override
	public int add(Pay record) {
		return payMapper.add(record);
	}

	@Override
	public int update(Pay record) {
		return payMapper.update(record);
	}

	@Override
	public String getOrderNo() {
		//订单编号组成：yyyyMMddHHmmss+四位随机数
		return DateUtil.formatDate(new Date(),"yyyyMMddHHmmss") + RandomUtils.nextInt(4);
	}	
}

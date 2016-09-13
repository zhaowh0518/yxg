package com.zwh.social.api.service;

import com.zwh.social.api.model.Pay;

/**
 * 支付
 * @author zhaowh
 *
 */
public interface IPayService {
	/**
	 * 新增支付凭据
	 * @param record 支付凭据
	 * @return
	 */
    int add(Pay record);
    /**
     * 更新支付凭据
     * @param record 支付凭据
     * @return
     */
    int update(Pay record);
    /**
     * 获取一个全局的订单编号
     * @return
     */
    String getOrderNo();
}

package com.zwh.social.api;

public final class Constant {
	 /**
     * 全局配置信息的名称，配置信息会存贮在HashMap中.
     */
    public static final String CONFIG = "APIConfig";
    /**
     * 支付状态；待支付
     */
    public static final Integer ORDER_STATE_READY = 0;
    /**
     * 支付状态：已支付
     */
    public static final Integer ORDER_STATE_PAID = 1;
    /**
     * 支付类型：包月
     */
    public static final int PAY_TYPE_MONTH = 1;
    /**
     * 支付类型：VIP
     */
    public static final int PAY_TYPE_VIP = 2;
}

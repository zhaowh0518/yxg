package com.zwh.social.api.util;

import java.util.Date;

public class DateUtil {

	public static void main(String[] args) throws Exception {
		System.out.println(DateUtil.getNow());
	}
	/***
	 * 获取当前日期（返回字符串）
	 * @return
	 */
	public static String getNow(){
		return org.apache.commons.httpclient.util.DateUtil.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}
}

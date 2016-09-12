package com.zwh.social.api.util;

import java.util.Random;

public class RandomUtil {

	/**
	 * 生成随机数
	 * 
	 * @param length
	 * @return
	 * @throws Exception
	 */
	public static String getRandomDigit(int length) {
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		try {
			Random random = new Random();
			for (int i = 0; i < length; ++i) {
				int number = random.nextInt(10);
				sb.append(str.charAt(number));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

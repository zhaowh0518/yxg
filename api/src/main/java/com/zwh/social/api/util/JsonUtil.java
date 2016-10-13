package com.zwh.social.api.util;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;

/**
 * JSON工具类
 *
 */
public class JsonUtil {
	/**
	 * 转换成JSON字符串
	 * @param data
	 * @param filterProperty
	 * @return
	 */
	public static JSONObject toJSONString(Object data,final String[] filterProperty) {
		PropertyFilter filter = new PropertyFilter() {
			public boolean apply(Object obj, String name, Object value) {
				for(String n : filterProperty) {
					if(n.equals(name)) return false;
				}
				return true;
			}
		};		
		SerializeWriter sw = new SerializeWriter();
		JSONSerializer js = new JSONSerializer(sw);
		js.getPropertyFilters().add(filter);
		js.write(data);
		return JSONObject.parseObject(sw.toString());
	}
	/**
	 * 把JSON字符串转为List
	 * @param text
	 * @param clazz
	 * @return
	 */
	public static List toList(String text,Class clazz){
		return JSONObject.parseArray(text, clazz);
	}
	/**
	 * 根据关键字找到JSON字符串的List转为List
	 * @param text
	 * param key 
	 * @return
	 */
	public static List toList(String text,String key){
		JSONObject object = JSONObject.parseObject(text);
		return object.getJSONArray(key);
	}
	/**
	 * 把JSON转换为Object
	 * @param text
	 * @return
	 */
	public static Object toObject(String text){
		return JSONObject.parse(text);
	}
	
	
	public static void main(String[] args) {
		
	}
	
}

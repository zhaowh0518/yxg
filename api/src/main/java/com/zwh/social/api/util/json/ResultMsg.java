package com.zwh.social.api.util.json;

import com.alibaba.fastjson.JSONObject;
import com.zwh.social.api.util.JsonUtil;

/**
 * 返回客户端的消息封装
 *
 */
public class ResultMsg {
	/**
	 * 正常
	 */
	private static int STATUS_SUCCESS = 1;
	/**
	 * 错误
	 */
	private static int STATUS_FALIED = 0;
	/**
	 * 异常
	 */
	private static int STATUS_ERROR = -1;
	
	private int status;
	private String msg;
	private Object result;


	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	public ResultMsg(int status, String msg, Object result) {
		super();
		this.status = status;
		this.msg = msg;
		this.result = result;
	}
		
	/**
	 * 返回成功
	 * @param result
	 * @return
	 */
	public static JSONObject success(Object result){
		ResultMsg msg;
		if(result instanceof String){
			msg = new ResultMsg(STATUS_SUCCESS,result.toString(),result);
		}else{
			msg = new ResultMsg(STATUS_SUCCESS,"",result);			
		}
		return JsonUtil.toJSONString(msg, new String[]{});
	}
	
	/**
	 * 返回异常，异常情况
	 * @param result
	 * @return
	 */
	public static JSONObject error(Object result){
		ResultMsg msg=null;
		if(result instanceof Exception){
			Exception e=(Exception) result;
			msg = new ResultMsg(STATUS_ERROR,e.getMessage(),e);
		}else{
			msg =new ResultMsg(STATUS_ERROR,String.valueOf(result),result);
		}
		return JsonUtil.toJSONString(msg, new String[]{});
	}
	
	/**
	 * 返回失败，业务处理失败
	 * @param result
	 * @return
	 */
	public static JSONObject failed(String result){
		ResultMsg msg = new ResultMsg(STATUS_FALIED,result,null);
		return JsonUtil.toJSONString(msg, new String[]{});
	}
}

package com.zwh.social.api.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {	
	/**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            /*
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            */
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            
            conn.setRequestProperty("Accept-Charset", "gbk");
            conn.setRequestProperty("contentType", "gbk");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    } 
    /**
     * 读取request中包含的信息
     * @param request
     * @return
     */
    public static String readPostData(HttpServletRequest request) {
    	String data = null;
    	try{
    		BufferedReader reader = request.getReader();
    		String line;
    		 while ((line = reader.readLine()) != null) {
    			 data += line;
             }
    	}catch(Exception ex){
    		
    	}
    	return data;
    }
    /**
     * 读取Map中的参数并转化为URL的参数
     * @param params
     * @param charset
     * @return
     * @throws IOException
     */
    public static String getQueryString(Map<String, String> params, String charset) throws IOException {
        if ((params == null) || (params.isEmpty())) {
          return null;
        }

        StringBuilder query = new StringBuilder();
        boolean hasParam = false;

        for (String key : params.keySet()) {
          if (hasParam)
        	  query.append("&");
          else {
        	  hasParam = true;
          }
          query.append(key).append("=").append(java.net.URLEncoder.encode(params.get(key), charset));
        }
        return query.toString();
    }
    /**
     * 读取request中的键值参数并以Map方式返回
     * @param request
     * @return
     */
    public static Map<String,Object> getRequetMap(HttpServletRequest request){
    	 Map<String,Object>  map = new HashMap<String,Object> ();
         Enumeration names = request.getParameterNames();
         while (names.hasMoreElements()) {
           String name = (String) names.nextElement();
           if(StringUtils.isNotEmpty(name)){
	           String value = request.getParameterValues(name)[0];
	           if(StringUtils.isNotEmpty(value) && !"undefined".equals(value)){
	        	   map.put(name, value);
	           }
           }
         }
         return map;
    }
    /**
     * 读取request中的params，返回JSON格式
     * @param request
     * @return
     */
    public static JSONObject getRequestJson(HttpServletRequest request){
    	try {
    		String params = request.getParameter("p");
    		if(StringUtils.isNotEmpty(params)){
    			return JSONObject.parseObject(params);
    		}
        } catch (Exception e) {
            // 解析失败的不做任何处理
        }
		return null;    	
    }
    /**
     * 获取request中的参数并转化为JSON格式
     * @param request
     * @return
     */
    public static JSONObject getRequestJson2(HttpServletRequest request){
    	try {
            InputStream inputStream = request.getInputStream();
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            byte[] temp = new byte[1024];
            int sin = 0;
            while ((sin = inputStream.read(temp)) > 0) {
                bo.write(temp, 0, sin);
            }
            byte[] reqParam = bo.toByteArray();
            // 将字节转换为字符
            String json = URLDecoder.decode(new String(reqParam, "UTF-8"), "UTF-8");
            System.out.println(json);
            if (StringUtils.isNotEmpty(json)) {
                return JSONObject.parseObject(json);
            }
        } catch (Exception e) {
            // 解析失败的不做任何处理
        }
		return null;    	
    }
    
    /**
     * 获取当前程序资源文件的物理地址
     * @param request
     * @return
     */
    public String getCurrentPath(HttpServletRequest request){
    	return request.getSession().getServletContext().getRealPath("/")+"resources"+"/";
    }
}

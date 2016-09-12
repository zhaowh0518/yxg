package com.zwh.social.api.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
/**
 * 使用阿里云的OSS上传文件
 * 视频文件由于要处理截图，所以需要上传到独立的杭州节点
 * @author zhaowh
 *
 */
public class UploadUtil {
	/**
	 * Access Key ID
	 */
	private static final String accessKeyId = "4C0zsHgP6EPQLP7X";
	/**
	 * Access Key Secret
	 */
	private static final String accessKeySecret = "ih49kcTxtzIAlDnDJVv001AANHS0aK";
	/**
	 * 是否上传视频
	 */
	private static Boolean isUploadVideo = false;

	public static Boolean getIsUploadVideo() {
		return isUploadVideo;
	}
	/**
	 * 如果需要上传视频需要设置为true
	 * @param isUploadVideo
	 */
	public static void setIsUploadVideo(Boolean isUploadVideo) {
		UploadUtil.isUploadVideo = isUploadVideo;
	}

	/**
	 * Bucket的名称
	 */
	private static final String bucketName_LIVE = "vipski";
	private static final String bucketName_TEST = "vipski-test";
	private static final String bucketName_VIDEO_LIVE = "vipski-video";
	private static final String bucketName_VIDEO_TEST = "vipski-video-test";

	/**
	 * 根据系统运行模式取Buket
	 * 
	 * @return
	 */
	private static String getBuketName(boolean type) {
		return bucketName_LIVE;
	}

	/**
	 * Bucket的域名
	 */
	private static final String endpoint_LIVE = "http://oss-cn-beijing-internal.aliyuncs.com";
	private static final String endpoint_TEST = "http://oss-cn-beijing.aliyuncs.com";
	private static final String endpoint_VIDEO_LIVE = "http://oss-cn-hangzhou-internal.aliyuncs.com";
	private static final String endpoint_VIDEO_TEST = "http://oss-cn-hangzhou.aliyuncs.com";

	private static String getEndpoint(boolean type) {
		return endpoint_LIVE;
	}

	/**
	 * MTS服务的地址
	 */
	private static final String MTS_URL = "http://mts.aliyuncs.com";
	/**
	 * 服务器地址
	 */
	private static final String LOCATION = "oss-cn-hangzhou";
	/**
	 * 用户信息中的图片地址
	 */
	public static final String PATH_USER_INFO = "user/person/";

	public static final String PATH_USER_STORY = "user/story/";
	public static final String PATH_USER_MESSAGE = "user/msg/";
	public static  final String PATH_USER_TRACE="user/skitrace/";
	/**
	 * OCS中的活动上传目录
	 */
	public static final String PATH_PARTY_OCS = "ocs/party/";

	/**
	 * 上传头像或者音频工具类
	 * 
	 * @param request
	 * @param file
	 * @return 上传完的文件的全路径
	 */
	public static String uploads(HttpServletRequest request,
			MultipartFile file, String path) {
		return uploads(request,file,path,false);
	}

	/**
	 * 上传视频并自动截图
	 * 
	 * @param request
	 * @param file
	 * @param path
	 * @return 截图的地址为返回的视频地址修改.mp4为.jpg即可
	 */
	public static String uploadVideo(HttpServletRequest request,
			MultipartFile file, String path) {
		UploadUtil.setIsUploadVideo(true);
		String fileName = uploads(request,file,path,true);
		// 截图,取第一秒的视频截图
		if (StringUtils.isNotEmpty(fileName)) {
			String imageUrl = fileName.substring(0, fileName.lastIndexOf("."))
					+ ".jpg";
			snapshot(fileName, imageUrl, 10L);
		}
		UploadUtil.setIsUploadVideo(false);
		return fileName;
	}
   
	/**
	 * 上传头像或者音频工具类
	 * 
	 * @param request
	 * @param file
	 * @return 上传完的文件的全路径
	 */
	public static String uploads(HttpServletRequest request,
			MultipartFile file, String path,boolean type) {
		if (null == file) {
			return null;
		}
		String fileName = path + file.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		fileName = path + UUID.randomUUID().toString() + "." + extName;
		try {
			InputStream stream = file.getInputStream();
			OSSClient client = new OSSClient(getEndpoint(type), accessKeyId,
					accessKeySecret);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(file.getSize());
			PutObjectResult result = client.putObject(getBuketName(type), fileName,
					stream, meta);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fileName;
	}

	/**
	 * 生成缩略图后上传
	 * 
	 * @param request
	 * @param file
	 * @return 上传完的文件的全路径
	 */
	public static String cutImageUploads(HttpServletRequest request,
			MultipartFile file, String path, int width, int hight) {
		if (null == file) {
			return null;
		}
		String fileName = path + file.getOriginalFilename();

		String extName = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		fileName = path + UUID.randomUUID().toString() + "." + extName;
		try {
			InputStream stream = file.getInputStream();
			// 获得文件的类型
			String contentType = getFileTypeByextName(extName);
			if (contentType == null)
				return null;
			byte[] retbytearray = ImageUtil.cutImage(stream, extName, width,
					hight, contentType);

			InputStream isout = new ByteArrayInputStream(retbytearray);

			OSSClient client = new OSSClient(getEndpoint(false), accessKeyId,
					accessKeySecret);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(retbytearray.length);
			PutObjectResult result = client.putObject(getBuketName(false), fileName,
					isout, meta);
			// stream.close();
			// isout.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fileName;
	}

	/**
	 * 视频截图
	 * 
	 * @param videoUrl
	 *            视频地址
	 * @param imageUrl
	 *            截图地址
	 * @param time
	 *            截图时长，单位ms
	 */
	public static void snapshot(String videoUrl, String imageUrl, Long time) {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("AccessKeyId", accessKeyId);
			params.put("Action", "SubmitSnapshotJob");
			params.put("Format", "JSON");
			params.put("Version", "2014-06-18");

			params.put("SignatureMethod", "Hmac-SHA1");
			params.put("SignatureNonce", RandomUtil.getRandomDigit(6));
			params.put("SignatureVersion", "1.0");

			JSONObject input = new JSONObject();
			input.put("Bucket", getBuketName(true));
			input.put("Location", LOCATION);
			input.put("Object", videoUrl);
			params.put("Input", input.toJSONString());

			JSONObject config = new JSONObject();
			JSONObject output = new JSONObject();
			output.put("Bucket", getBuketName(true));
			output.put("Location", LOCATION);
			output.put("Object", imageUrl);
			config.put("OutputFile", output);
			config.put("Time", time);
			params.put("SnapshotConfig", config.toJSONString());
			// 时间戳,日期格式按照ISO8601标准表示，并需要使用UTC时间。格式为：YYYY-MM-DDThh:mm:ssZ
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("GMT"));
			String timestamp = df.format(new Date());
			timestamp = timestamp.replace(" ", "T");
			timestamp = timestamp + "Z";
			params.put("Timestamp", timestamp);

			params.put("Signature", signature(params, accessKeySecret));

			String result = HttpUtil.sendGet(MTS_URL,
					HttpUtil.getQueryString(params, "utf-8"));
			System.out.println(new Date() + ":" + result);
		} catch (Exception ex) {
			System.out.println(new Date());
			ex.printStackTrace();
		}
	}

	/**
	 * 对url的参数做编码
	 * 
	 * @param param
	 *            参数
	 * @return
	 */
	private static String encodeParam(String param) {
		try {
			String result = java.net.URLEncoder.encode(param, "utf-8");
			return result.replace("+", "%20").replace("*", "%2A")
					.replace("%7E", "~");
		} catch (Exception ex) {

		}
		return "";
	}

	/**
	 * 签名
	 * 
	 * @param params
	 *            参数
	 * @return
	 */
	private static String signature(Map<String, String> params,
			String accessKeySecret) {
		String result = "";
		String[] sortedKeys = (String[]) params.keySet().toArray(new String[0]);
		Arrays.sort(sortedKeys);
		StringBuilder sb = new StringBuilder();
		for (String key : sortedKeys) {
			sb.append("&").append(encodeParam(key)).append("=")
					.append(encodeParam((String) params.get(key)));
		}
		try {
			StringBuilder stringToSign = new StringBuilder();
			stringToSign.append("GET").append("&");
			stringToSign.append(encodeParam("/")).append("&");
			stringToSign.append(encodeParam(sb.toString().substring(1)));

			Mac mac = Mac.getInstance("HmacSHA1");
			String key = accessKeySecret + "&";
			mac.init(new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA1"));
			byte[] signData = mac.doFinal(stringToSign.toString().getBytes(
					"UTF-8"));

			return EncryptUtil.BASE64Encrypt(signData);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		/*
		 * String filename = "E:\\temp\\1.jpg"; File file = new File(filename);
		 * 
		 * ObjectMetadata objectMeta = new ObjectMetadata();
		 * objectMeta.setContentLength(file.length()); // 可以在metadata中标记文件类型
		 * objectMeta.setContentType("image/jpeg");
		 * 
		 * InputStream input = new FileInputStream(file); OSSClient client = new
		 * OSSClient(endpoint,accessKeyId, accessKeySecret);
		 * 
		 * client.putObject(getBuketName(), "tt/2.jpg", input, objectMeta);
		 * 
		 * System.out.println("OK");
		 */
		String videoUrl = "test/c5656ea4-647f-4dd0-bb9d-0091e492776b.mp4";
		// videoUrl = "/cms/others/1a8628b8-bc1e-44b7-9ba9-af5d602619bf.mp4";
		String imageUrl = "test/s2.jpg";
		Long time = 12000L;
		UploadUtil.snapshot(videoUrl, imageUrl, time);
	}

	public static String getFileTypeByextName(String extName) {
		String contentType = null;
		if ("JPEG".equalsIgnoreCase(extName) || "JPG".equalsIgnoreCase(extName)) {
			contentType = "image/jpeg";
		} else if ("PNG".equalsIgnoreCase(extName)) {
			contentType = "image/png";
		} else if ("BMP".equalsIgnoreCase(extName)) {
			contentType = "image/bmp";
		} else if ("GIF".equalsIgnoreCase(extName)) {
			contentType = "image/gif";
		} else if ("ICO".equalsIgnoreCase(extName)) {
			contentType = "image/ico";
		}
		return contentType;
	}
	/**
	 * 上传压缩的描点文件方法类
	 * 
	 * @param request
	 * @param file(File)
	 * @return 上传完的文件的全路径
	 */
	public static String uploads(HttpServletRequest request,
			File file, String path) {
		if (null == file) {
			return null;
		}
		String fileName = path + file.getName();
		String extName = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		fileName = path + UUID.randomUUID().toString() + "." + extName;
		try {
			FileInputStream stream = new FileInputStream(file);

			OSSClient client = new OSSClient(getEndpoint(false), accessKeyId,
					accessKeySecret);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(file.length());
			PutObjectResult result = client.putObject(getBuketName(false), fileName,
					stream, meta);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fileName;
	}
	public static InputStream download(String filename){
		OSSClient client = new OSSClient(getEndpoint(false), accessKeyId,accessKeySecret);
		return downloadFile(client,getBuketName(false),filename);
	}
	/**
	 * 下载oss文件，输出输入流
	 * @param client
	 * @param bucketName 服务器域名
	 * @param Objectkey  上传时的文件名
	 * @return  返回输入流
	 */
    private static InputStream downloadFile(OSSClient client, String bucketName, String Objectkey) {
        //client.getObject(new GetObjectRequest(bucketName, Objectkey),new File(filename));
    	InputStream in=null;
    	try{
    	OSSObject oss=client.getObject(new GetObjectRequest(bucketName, Objectkey));
    	in=oss.getObjectContent();
    	}catch(Exception e){
    		e.printStackTrace();
    	}
       return in;
    }
}

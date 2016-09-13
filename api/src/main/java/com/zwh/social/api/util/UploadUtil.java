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
	 * Bucket的名称
	 */
	private static final String bucketName = "vipski-test";

	/**
	 * Bucket的域名
	 */
	private static final String endpoint = "http://oss-cn-beijing.aliyuncs.com";

	/**
	 * 头像
	 */
	public static final String PATH_USER_HEAD = "socail/user/head/";
	/**
	 * 相册
	 */
	public static final String PATH_USER_PHOTO = "socail/user/photo/";

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param file
	 * @return 上传完的文件的全路径
	 */
	public static String upload(HttpServletRequest request,
			MultipartFile file, String path) {
		if (null == file) {
			return null;
		}
		String fileName = path + file.getOriginalFilename();
		String extName = fileName.substring(fileName.lastIndexOf(".") + 1,
				fileName.length());
		fileName = path + UUID.randomUUID().toString() + "." + extName;
		try {
			InputStream stream = file.getInputStream();
			OSSClient client = new OSSClient(endpoint, accessKeyId,
					accessKeySecret);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(file.getSize());
			PutObjectResult result = client.putObject(bucketName, fileName,	stream, meta);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return fileName;
	}
}

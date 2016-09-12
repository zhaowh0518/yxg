package com.zwh.social.api.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil {

	public static void main(String[] args) throws Exception {

		String token = "123456";
		String base64token = EncryptUtil.encryptBASE64(token);
		System.out.println(EncryptUtil.SHA1(base64token.trim()));

	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public static String encryptBASE64(String keys) throws Exception {
		byte[] key = keys.getBytes();
		return (new BASE64Encoder()).encodeBuffer(key);
	}

	/**
	 * SHA1加密
	 * 
	 * @param decript
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String SHA1(String decript) throws NoSuchAlgorithmException {
		MessageDigest digest = java.security.MessageDigest.getInstance("SHA-1");
		digest.update(decript.getBytes());
		byte messageDigest[] = digest.digest();
		String result = String.format("%0" + (messageDigest.length * 2) + "X",
				new BigInteger(1, messageDigest));
		return result.toLowerCase();
	}

	/**
	 * MD5加密,返回字符串方式
	 * 
	 * @param s
	 * @return
	 */
	public static String MD5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * MD5加密,返回byte[]
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] MD5Encrypt(String s) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			return md5.digest(s.getBytes("utf-8"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String BASE64Encrypt(byte[] key) {
		String edata = null;
		try {
			edata = (new BASE64Encoder()).encodeBuffer(key).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return edata;
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] BASE64Decrypt(String data) {
		if (data == null)
			return null;
		byte[] edata = null;
		try {
			edata = (new BASE64Decoder()).decodeBuffer(data);
			return edata;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 *            24位密钥
	 * @param str
	 *            源字符串
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeySpecException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static byte[] DES3Encrypt(String key, String str)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, UnsupportedEncodingException,
			InvalidKeySpecException, IllegalBlockSizeException,
			BadPaddingException {

		byte[] newkey = key.getBytes();

		SecureRandom sr = new SecureRandom();

		DESedeKeySpec dks = new DESedeKeySpec(newkey);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");

		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");

		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		byte[] bt = cipher.doFinal(str.getBytes("utf-8"));

		return bt;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String DES3Decrypt(byte[] edata, String key) {
		String data = "";
		try {
			if (edata != null) {
				byte[] newkey = key.getBytes();
				DESedeKeySpec dks = new DESedeKeySpec(newkey);
				SecretKeyFactory keyFactory = SecretKeyFactory
						.getInstance("DESede");
				SecretKey securekey = keyFactory.generateSecret(dks);
				Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
				cipher.init(Cipher.DECRYPT_MODE, securekey, new SecureRandom());
				String newData = new String(edata);
				// if (!newData.endsWith("=")){
				// data = URLDecoder.decode(newData,"utf-8");
				// }
				byte[] bb = cipher.doFinal(edata);
				data = new String(bb, "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}

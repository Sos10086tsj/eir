package com.chinesedreamer.eir.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.lang3.StringUtils;

/**
 * Description: Auth:Paris Date:Nov 10, 2016
 **/
public class EncryptionUtil {
	private final static String DEFAULT_CHART = "UTF-8";

	/**
	 * md加密32位
	 * 
	 * @param unencryptedText
	 * @return
	 * @throws Exception
	 */
	public static String md5L32(String unencryptedText, String charSet) throws Exception {
		if (StringUtils.isEmpty(unencryptedText)) {
			return unencryptedText;
		}
		if (StringUtils.isEmpty(charSet)) {
			charSet = DEFAULT_CHART;
		}
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte[] bytes = md5.digest(unencryptedText.getBytes(charSet));
		StringBuilder buffer = new StringBuilder();
		for (byte b : bytes) {
			int bt = b & 0xff;
			if (bt < 16) {
				buffer.append(0);
			}
			buffer.append(Integer.toHexString(bt));
		}
		return buffer.toString();
	}

	/**
	 * md加密32位
	 * 
	 * @param unencryptedText
	 * @param upperCase
	 * @return
	 * @throws Exception
	 */
	public static String md5L32(String unencryptedText, String charSet, boolean upperCase) throws Exception {
		String str = md5L32(unencryptedText, charSet);
		if (upperCase) {
			return str.toUpperCase();
		} else {
			return str.toLowerCase();
		}
	}

	/************ DES PART START *****************/
	public static class DES {
		public static class MODEL {
			public static String ECB = "ECB";
			public static String CBC = "CBC";
			public static String CTR = "CTR";
			public static String OFB = "OFB";
			public static String CFB = "CFB";
		}

		public static class CIPHER {
			public static String PKCS5PADDING = "PKCS5PADDING";
			public static String PKCS7PADDING = "PKCS7PADDING";
			public static String ZEROPADDING = "ZEROPADDING";
			public static String ISO10126 = "ISO10126";
			public static String ANSIX923 = "ANSIX923";
		}
	}

	/**
	 * Des加密，默认 DES/ECB/PKCS5Padding
	 * 
	 * @param content
	 * @param key
	 * @param charSet
	 * @param cipher
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByDES(String content, String key, String charSet, String cipher) throws Exception {
		if (StringUtils.isEmpty(charSet)) {
			charSet = DEFAULT_CHART;
		}
		if (StringUtils.isEmpty(cipher)) {
			cipher = "DES/" + DES.MODEL.ECB + "/" + DES.CIPHER.PKCS5PADDING;
		}
		DESKeySpec dks = new DESKeySpec(key.getBytes(charSet));
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(dks);
		Cipher cip = Cipher.getInstance(cipher);
		cip.init(Cipher.ENCRYPT_MODE, sk);
		return cip.doFinal(content.getBytes(charSet));
	}

	/**
	 * Des 解密，默认 DES/ECB/PKCS5Padding
	 * @param content
	 * @param key
	 * @param charSet
	 * @param cipher
	 * @return
	 * @throws Exception
	 */
	public static String decryptByDES(byte[] content, String key, String charSet, String cipher) throws Exception {
		if (StringUtils.isEmpty(charSet)) {
			charSet = DEFAULT_CHART;
		}
		if (StringUtils.isEmpty(cipher)) {
			cipher = "DES/" + DES.MODEL.ECB + "/" + DES.CIPHER.PKCS5PADDING;
		}
		DESKeySpec desKS = new DESKeySpec(key.getBytes(charSet));
		SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
		SecretKey sk = skf.generateSecret(desKS);
		Cipher cip = Cipher.getInstance(cipher);
		cip.init(Cipher.DECRYPT_MODE, sk);
		byte[] result = cip.doFinal(content);
		return new String(result, charSet);
	}
}

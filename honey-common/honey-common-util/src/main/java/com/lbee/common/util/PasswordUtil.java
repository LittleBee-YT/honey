package com.lbee.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
* @version: v1.0
* @Description:密码md5设定
* 
*/
public class PasswordUtil {

	public PasswordUtil() {
		super();
	}
	
	/**
	 * 转换字节数组为16进制字符串
	 * @param bs
	 * @return
	 */
    private static String byteToHexString(byte[] bs) {
        // StringBuilder 性能好，但非线程安全
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bs.length; i++) {
            String hex = Integer.toHexString(bs[i] & 0xFF);
            if (hex.length() == 1) {
                hex = "0" + hex;
            }
            sb.append(hex);                     
        }
         return sb.toString();  
    }
    
    /**
     * 无盐值计算摘要
     * @param ins
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getMD5(String ins) throws NoSuchAlgorithmException, UnsupportedEncodingException {
         MessageDigest md = MessageDigest.getInstance("MD5");
         byte[] result = md.digest(ins.getBytes("UTF-8"));
         return byteToHexString(result);
    }
    
    /**
     * 有盐值计算摘要
     * @param ins
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getMD5WithSalt(String ins, String salt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
         MessageDigest md = MessageDigest.getInstance("MD5");
         byte[] result = md.digest(ins.concat("{" + salt + "}").getBytes());
         return byteToHexString(result);
    }
	
}

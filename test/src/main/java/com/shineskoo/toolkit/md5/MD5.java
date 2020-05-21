package com.shineskoo.toolkit.md5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * MD5加密工具包
 */
public class MD5 {

    /**
     * MD5加密(String)
     */
    public String Encrypt(String toEncrypt) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toEncrypt.getBytes());
            byte b[] = md.digest();
            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5加密(byte[])
     */
    public String Encrypt(byte[] byteList) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(byteList);
            byte b[] = md.digest();
            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            //32位加密
            return buf.toString();
            // 16位的加密
            //return buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * MD5解密
     */
    public String Decrypt(String toDecrypt) throws Exception {
        throw new Exception("MD5不支持解密");
    }
}

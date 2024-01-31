package com.rookie.bigdata.encryption;

import cn.hutool.core.util.HexUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Class MD5Util
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/25 14:15
 * @Version 1.0
 */
public class MD5Util {

    //MD5
    public static void main(String[] args) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String pwd = "a";

        byte[] md5Bytes = md.digest(pwd.getBytes("UTF-8"));
        //将字节数组转换为十六进制字符数组
        System.out.println(new String(HexUtil.encodeHex(md5Bytes)));
        System.out.println(Base64Util.encode(md5Bytes));

        System.out.println(digest(pwd));


    }


    public static String digest(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            //将字节数组转换为十六进制字符数组
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static String StringToMd5(byte[] b) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.reset();
            md.update(b);
            byte[] hash = md.digest();
            StringBuffer outStrBuf = new StringBuffer(32);
            for (int i = 0; i < hash.length; i++) {
                int v = hash[i] & 0xFF;
                if (v < 16) {
                    outStrBuf.append('0');
                }
                outStrBuf.append(Integer.toString(v, 16).toLowerCase());
            }
            return outStrBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new String(b);
        }
    }


}

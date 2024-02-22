package com.rookie.bigdata.at0x7c00;

import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;

/**
 * @Class MD5Util
 * @Description https://www.cnblogs.com/at0x7c00/category/1099884.html
 * <p>
 * https://www.cnblogs.com/at0x7c00/p/7519600.html
 * @Author rookie
 * @Date 2024/2/21 9:20
 * @Version 1.0
 */
public class MD5Util {

    /**
     * MD5一般和Base64配合使用，用来加密得到固定长度的Base64码。如果不用Base64编码，Spring的Hex对MD5数据进行了友好的输出。
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        String pwd = "a";

        byte[] md5Bytes = md.digest(pwd.getBytes("UTF-8"));
        System.out.println(new String(Hex.encode(md5Bytes)));
        System.out.println(Base64Util.encode(md5Bytes));


    }
}

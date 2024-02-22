package com.rookie.bigdata.at0x7c00;

import java.security.MessageDigest;

/**
 * @Class SHAUtil
 * @Description https://www.cnblogs.com/at0x7c00/category/1099884.html
 * <p>
 * https://www.cnblogs.com/at0x7c00/p/7519600.html
 * @Author rookie
 * @Date 2024/2/21 9:26
 * @Version 1.0
 */
public class SHAUtil {
    public static void main(String[] args) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        String str = "Hello";
        String str2 = Base64Util.encode(md.digest(str.getBytes()));
        System.out.println(str2);
    }
}

package com.rookie.bigdata.encryption;

import java.security.MessageDigest;

/**
 * @Class SHAUtil
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/25 14:21
 * @Version 1.0
 */
public class SHAUtil {

    //SHA的全称叫安全散列算法（Secure Hash Algorithm），它是比MD5更安全消息摘要算法。
    public static void main(String[] args) throws Exception{
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        String str = "Hello";
        String str2 = Base64Util.encode(md.digest(str.getBytes()));
        System.out.println(str2);
    }
}

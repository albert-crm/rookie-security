package com.rookie.bigdata.chenwewi;

/**
 * @Class SHACoder
 * @Description https://blog.csdn.net/chenwewi520feng/article/details/131280324
 * @Author rookie
 * @Date 2023/12/26 16:19
 * @Version 1.0
 */


import java.security.MessageDigest;


public class SHACoder {
    /**
     * SHA-1加密
     */
    public static byte[] encodeSHA(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA");

        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * SHA-256加密
     */
    public static byte[] encodeSHA256(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * SHA-384加密
     */
    public static byte[] encodeSHA384(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA-384");

        // 执行消息摘要
        return md.digest(data);
    }

    /**
     * SHA-512加密
     */
    public static byte[] encodeSHA512(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA-512");

        // 执行消息摘要
        return md.digest(data);
    }

    public static void main(String[] args) {

    }
}

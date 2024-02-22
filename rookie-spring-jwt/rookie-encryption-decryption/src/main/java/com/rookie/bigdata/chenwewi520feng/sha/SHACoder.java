package com.rookie.bigdata.chenwewi520feng.sha;

import java.security.MessageDigest;
/**
 * @Class SHACoder
 * @Description
 * @Author rookie
 * @Date 2024/2/22 16:49
 * @Version 1.0
 */
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
}

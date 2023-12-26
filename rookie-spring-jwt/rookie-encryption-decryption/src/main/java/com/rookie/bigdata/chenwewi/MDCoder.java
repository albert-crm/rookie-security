package com.rookie.bigdata.chenwewi;

/**
 * @Class MDCoder
 * @Description https://blog.csdn.net/chenwewi520feng/article/details/131280324
 * jdk示例
 * @Author rookie
 * @Date 2023/12/26 16:16
 * @Version 1.0
 */

import cn.hutool.core.util.HexUtil;

import java.security.MessageDigest;


public class MDCoder {
    public static byte[] encodeMD2(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD2");
        // 执行消息摘要
        return md.digest(data);
    }

    public static byte[] encodeMD5(byte[] data) throws Exception {
        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 执行消息摘要
        return md.digest(data);
    }


    public static void main(String[] args) throws Exception {
        String data = "中国人";

        byte[] bytes = encodeMD2(data.getBytes("UTF-8"));


        System.out.println(new String(HexUtil.encodeHex(bytes)));

        byte[] bytes5 = encodeMD5(data.getBytes("UTF-8"));


        System.out.println(new String(HexUtil.encodeHex(bytes5)));


    }
}

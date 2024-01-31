package com.rookie.bigdata.chenwewi;

import java.util.Base64;

/**
 * @Class Base64Coder
 * @Description https://blog.csdn.net/chenwewi520feng/article/details/131280324
 * JDK示例
 * @Author rookie
 * @Date 2023/12/26 15:29
 * @Version 1.0
 */
public class Base64Coder {
    /**
     * 字符编码
     */
    public final static String ENCODING = "UTF-8";

    /**
     * Base64编码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String data) throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] b = data.getBytes(ENCODING);
        return encoder.encodeToString(b);
    }

    /**
     * Base64解码
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decode(String data) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] b = decoder.decode(data);
        return new String(b, ENCODING);
    }

    public static void main(String[] args) throws Exception {
        String abc1 = encode("abcabcabcabcabcabcabcabcabcabc");
        String abc2 = encode("abc");
        System.out.println("abc1: " + abc1);
        System.out.println("abc2: " + abc2);


    }


}


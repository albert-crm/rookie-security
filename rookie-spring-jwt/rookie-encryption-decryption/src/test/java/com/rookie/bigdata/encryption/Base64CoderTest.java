package com.rookie.bigdata.encryption;


import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;

/**
 * @Class Base64CoderTest
 * @Description https://blog.csdn.net/chenwewi520feng/article/details/131280324
 * @Author rookie
 * @Date 2023/12/26 15:49
 * @Version 1.0
 */
class Base64CoderTest {

    @Test
    public void test1() throws UnsupportedEncodingException {
        String a = "名";
        System.out.println("UTF-8编码长度:"+a.getBytes("UTF-8").length);
        System.out.println("GBK编码长度:"+a.getBytes("GBK").length);
        System.out.println("GB2312编码长度:"+a.getBytes("GB2312").length);
        System.out.println("==========================================");

        String c = "0x20001";
        System.out.println("UTF-8编码长度:"+c.getBytes("UTF-8").length);
        System.out.println("GBK编码长度:"+c.getBytes("GBK").length);
        System.out.println("GB2312编码长度:"+c.getBytes("GB2312").length);
        System.out.println("==========================================");

        char[] arr = Character.toChars(0x20001);
        String s = new String(arr);
        System.out.println("char array length:" + arr.length);
        System.out.println("content:|  " + s + " |");
        System.out.println("String length:" + s.length());
        System.out.println("UTF-8编码长度:"+s.getBytes("UTF-8").length);
        System.out.println("GBK编码长度:"+s.getBytes("GBK").length);
        System.out.println("GB2312编码长度:"+s.getBytes("GB2312").length);
        System.out.println("==========================================");
    }




}

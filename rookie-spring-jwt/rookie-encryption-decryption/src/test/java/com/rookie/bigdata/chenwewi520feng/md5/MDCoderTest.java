package com.rookie.bigdata.chenwewi520feng.md5;

import com.rookie.bigdata.chenwewi.MDCoder;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class MDCoderTest
 * @Description
 * @Author rookie
 * @Date 2024/2/22 15:14
 * @Version 1.0
 */
class MDCoderTest {


    @Test
    public final void testEncodeMD2() throws Exception {
        String str = "MD2消息摘要";

        // 获得摘要信息
        byte[] data1 = MDCoder.encodeMD2(str.getBytes());
        byte[] data2 = MDCoder.encodeMD2(str.getBytes());

        String data3=new String(data1);
        String data4=new String(data2);

        boolean result = Arrays.equals(data1, data2);
        System.out.println("arr1和arr2是否相等: " + result);
//        assertEquals(data1, data2);
        assertArrayEquals(data1,data2);
        // 校验
        assertEquals(data3, data4);
    }

    @Test
    public final void testEncodeMD5() throws Exception {
        String str = "MD5消息摘要";

        // 获得摘要信息
        byte[] data1 = MDCoder.encodeMD5(str.getBytes());
        byte[] data2 = MDCoder.encodeMD5(str.getBytes());

        String data3=new String(data1);
        String data4=new String(data2);


//        assertEquals(data1, data2);
        // 校验
        assertEquals(data3, data4);
    }


//    @Test
//    public final void testEncodeMD2() throws Exception {
//        String str = "MD2消息摘要";
//
//        // 获得摘要信息
//        byte[] data1 = MDCoder.encodeMD2(str.getBytes());
//        byte[] data2 = MDCoder.encodeMD2(str.getBytes());
//
//        // 校验
//        assertEquals(data1, data2);
//    }
//
//    @Test
//    public final void testEncodeMD5() throws Exception {
//        String str = "MD5消息摘要";
//
//        // 获得摘要信息
//        byte[] data1 = MDCoder.encodeMD5(str.getBytes());
//        byte[] data2 = MDCoder.encodeMD5(str.getBytes());
//
//        // 校验
//        assertEquals(data1, data2);
//    }
}

package com.rookie.bigdata.chenwewi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class MDCoderCommonsTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/26 17:01
 * @Version 1.0
 */
class MDCoderCommonsTest {

    @Test
    void encodeMD5() {
    }

    @Test
    void encodeMD5Hex() {
    }

    @Test
    public final void testEncodeMD5() throws Exception {
        String str = "MD5消息摘要";

        // 获得摘要信息
        byte[] data1 = MDCoderCommons.encodeMD5(str);
        byte[] data2 = MDCoderCommons.encodeMD5(str);

        assertArrayEquals(data1,data2);
        // 校验
//        assertEquals(data1, data2);
    }

    @Test
    public final void testEncodeMD5Hex() throws Exception {
        String str = "MD5Hex消息摘要";

        // 获得摘要信息
        String data1 = MDCoderCommons.encodeMD5Hex(str);
        String data2 = MDCoderCommons.encodeMD5Hex(str);

        System.err.println("原文：\t" + str);
        System.err.println("MD5Hex-1：\t" + data1);
        System.err.println("MD5Hex-2：\t" + data2);

        // 校验
        assertEquals(data1, data2);
    }
}



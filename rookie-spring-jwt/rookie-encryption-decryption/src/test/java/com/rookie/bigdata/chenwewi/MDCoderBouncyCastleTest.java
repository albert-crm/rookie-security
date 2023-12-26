package com.rookie.bigdata.chenwewi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class MDCoderBouncyCastleTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/26 16:59
 * @Version 1.0
 */
class MDCoderBouncyCastleTest {

    @Test
    void encodeMD4() {
    }

    @Test
    void encodeMD4Hex() {
    }

    @Test
    public final void testEncodeMD4() throws Exception {
        String str = "MD4消息摘要";

        // 获得摘要信息
        byte[] data1 = MDCoderBouncyCastle.encodeMD4(str.getBytes());
        byte[] data2 = MDCoderBouncyCastle.encodeMD4(str.getBytes());

        // 校验
        assertArrayEquals(data1, data2);
//        assertEquals(data1, data2);
    }

    @Test
    public final void testEncodeMD4Hex() throws Exception {
        String str = "MD4Hex消息摘要";

        // 获得摘要信息
        String data1 = MDCoderBouncyCastle.encodeMD4Hex(str.getBytes());
        String data2 = MDCoderBouncyCastle.encodeMD4Hex(str.getBytes());

        System.err.println("原文：\t" + str);
        System.err.println("MD4Hex-1：\t" + data1);
        System.err.println("MD4Hex-2：\t" + data2);

//        assertArrayEquals(data1, data2);
        // 校验
        assertEquals(data1, data2);
    }
}


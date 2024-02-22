package com.rookie.bigdata.chenwewi520feng.base64;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class Base64CoderBouncyCastleTest
 * @Description
 * @Author rookie
 * @Date 2024/2/22 14:53
 * @Version 1.0
 */
class Base64CoderBouncyCastleTest {
    @Test
    public final void test() throws Exception {
        String inputStr = "Java加密与解密";
        // 进行Base64编码
        String code = Base64CoderBouncyCastle.encode(inputStr);
        System.err.println("编码后:\t" + code);
        // 进行Base64解码
        String outputStr = Base64CoderBouncyCastle.decode(code);
        System.err.println("解码后:\t" + outputStr);
        // 验证Base64编码解码一致性
        assertEquals(inputStr, outputStr);

    }

    @Test
    public final void demo() throws Exception {
        String str = "Base64 编码";
        // Base64编码
        String data = Base64CoderBouncyCastle.encode(str);
        System.err.println("编码后:\t" + new String(data));

        // Base64解码
        String output = Base64CoderBouncyCastle.decode(data);
        System.err.println("解码后:\t" + new String(output));

    }

    @Test
    public final void demo2() throws Exception {
        String str = "Base64 编码";
        // Url Base64编码
        String data = Base64CoderBouncyCastle.urlEncode(str);
        System.err.println("编码后:\t" + new String(data));

        // Url Base64解码
        String output = Base64CoderBouncyCastle.urlDecode(data);
        System.err.println("解码后:\t" + new String(output));
    }
}


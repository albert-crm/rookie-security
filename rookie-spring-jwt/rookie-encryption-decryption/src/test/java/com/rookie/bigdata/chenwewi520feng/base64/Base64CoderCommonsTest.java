package com.rookie.bigdata.chenwewi520feng.base64;

import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class Base64CoderCommonsTest
 * @Description
 * @Author rookie
 * @Date 2024/2/22 14:59
 * @Version 1.0
 */
class Base64CoderCommonsTest {
    @Test
    public final void test() throws Exception {
        String inputStr = "Java加密与解密";

        // 进行Base64编码
        String code = Base64CoderCommons.encode(inputStr);
        System.err.println("编码后:\t" + code);

        // 进行Base64解码
        String outputStr = Base64CoderCommons.decode(code);
        System.err.println("解码后:\t" + outputStr);

        // 验证Base64编码解码一致性
        assertEquals(inputStr, outputStr);
    }

    @Test
    public final void testSafe() throws Exception {
        String inputStr = "Java加密与解密";

        // 进行Base64编码
        String code = Base64CoderCommons.encodeSafe(inputStr);
        System.err.println("编码后:\t" + code);

        // 进行Base64解码
        String outputStr = Base64CoderCommons.decode(code);
        System.err.println("解码后:\t" + outputStr);

        // 验证Base64编码解码一致性
        assertEquals(inputStr, outputStr);
    }

    @Test
    public final void demo() throws Exception {
        String str = "Base64 编码1";

        // Base64编码
        String data = Base64CoderCommons.encode(str);
        System.err.println("编码后:\t" + new String(data));

        // Base64解码
        String output = Base64CoderCommons.decode(data);
        System.err.println("解码后:\t" + new String(output));
    }

    @Test
    public final void demo2() throws Exception {
        String str = "Base64 编码2";
        byte[] input = str.getBytes();

        // Base64编码
        byte[] data = Base64.encodeBase64(input);
        System.err.println("编码后:\t" + new String(data));

        // Base64解码
        byte[] output = Base64.decodeBase64(data);
        System.err.println("解码后:\t" + new String(output));

    }

    @Test
    public final void demo3() throws Exception {
        String str = "Base64 编码3";

        // Base64编码
        String data = Base64CoderCommons.encodeSafe(str);
        System.err.println("编码后:\t" + new String(data));

        // Base64解码
        byte[] output = Base64.decodeBase64(data);
        System.err.println("解码后:\t" + new String(output));

    }
}


package com.rookie.bigdata.chenwewi;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Class Base64CoderTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/26 16:31
 * @Version 1.0
 */
class Base64CoderTest {

    @Test
    void encode() throws Exception{
        String inputStr = "Java加密与解密";
        // 进行Base64编码
        String code = Base64Coder.encode(inputStr);
        System.err.println("Base64编码后:\n\t" + code);

        // 进行Base64解码
        String outputStr = Base64Coder.decode(code);
        System.err.println("Base64解码后:\n\t" + outputStr);

        // 验证Base64编码解码一致性
        assertEquals(inputStr, outputStr);
    }

    @Test
    void decode() throws Exception{
        String str = "Java加密与解密";
        // Base64编码
        String data = Base64Coder.encode(str);
        System.err.println("编码后:\n\t" + data);

        // Base64解码
        String output = Base64Coder.decode(data);
        System.err.println("解码后:\n\t" + output);
    }
}

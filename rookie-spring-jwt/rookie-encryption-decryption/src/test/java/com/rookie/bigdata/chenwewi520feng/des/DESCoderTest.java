package com.rookie.bigdata.chenwewi520feng.des;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.Test;

/**
 * @Class DESCoderTest
 * @Description
 * @Author rookie
 * @Date 2024/2/23 9:59
 * @Version 1.0
 */
class DESCoderTest {

    @Test
    public final void test() throws Exception {
        String inputStr = "DES";
        byte[] inputData = inputStr.getBytes();
        System.err.println("原文:\t" + inputStr);

        // 初始化密钥
        byte[] key = DESCoder.initKey();
        System.err.println("密钥:\t" + Base64.encodeBase64String(key));


        // 加密
        inputData = DESCoder.encrypt(inputData, key);
        System.err.println("加密后:\t" + Base64.encodeBase64String(inputData));

        key=Base64.decodeBase64(Base64.encodeBase64String(key));
        // 解密
        byte[] outputData = DESCoder.decrypt(inputData, key);
        String outputStr = new String(outputData);
        System.err.println("解密后:\t" + outputStr);

        // 校验
        assertEquals(inputStr, outputStr);
    }


}

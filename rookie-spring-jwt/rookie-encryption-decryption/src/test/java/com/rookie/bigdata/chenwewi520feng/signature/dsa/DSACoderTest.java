package com.rookie.bigdata.chenwewi520feng.signature.dsa;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class DSACoderTest
 * @Description
 * @Author rookie
 * @Date 2024/2/23 10:51
 * @Version 1.0
 */

import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DSACoderTest {
    /**
     * 公钥
     */
    private byte[] publicKey;

    /**
     * 私钥
     */
    private byte[] privateKey;

    /**
     * 初始化密钥
     *
     * @throws Exception
     */
    @BeforeEach
    public void initKey() throws Exception {
        Map<String, Object> keyMap = DSACoder.initKey();

        publicKey = DSACoder.getPublicKey(keyMap);
        privateKey = DSACoder.getPrivateKey(keyMap);

        System.err.println("公钥: \n" + Base64.encodeBase64String(publicKey));
        System.err.println("私钥： \n" + Base64.encodeBase64String(privateKey));
    }

    /**
     * 校验签名
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        String inputStr = "DSA数字签名";
        byte[] data = inputStr.getBytes();

        // 产生签名
        byte[] sign = DSACoder.sign(data, privateKey);
        System.err.println("签名:\r" + Hex.encodeHexString(sign));

        // 验证签名
        boolean status = DSACoder.verify(data, publicKey, sign);
        System.err.println("状态:\r" + status);
        assertTrue(status);

    }
}

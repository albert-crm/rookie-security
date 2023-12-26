package com.rookie.bigdata.rsa;

/**
 * @Class GenerateRSAKeys
 * @Description https://blog.csdn.net/qq_35427589/article/details/133745697
 * @Author rookie
 * @Date 2023/12/26 13:39
 * @Version 1.0
 */


import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * RSA公私钥生成
 *
 * @author 共饮一杯无
 */
public class GenerateRSAKeys {
    public static void main(String[] args) throws Exception {
        // 创建一个RSA密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        // 设置密钥长度，通常为2048位
        keyPairGenerator.initialize(2048);

        // 生成RSA密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        System.out.println("公钥: " + Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        System.out.println("私钥: " + Base64.getEncoder().encodeToString(privateKey.getEncoded()));
    }
}


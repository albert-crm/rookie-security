package com.rookie.bigdata.jwt;

/**
 * @Class JwtExample
 * @Description https://blog.csdn.net/weixin_46654114/article/details/132977044
 * @Author rookie
 * @Date 2023/12/25 10:42
 * @Version 1.0
 */


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;

public class JwtExample {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {

        // 生成RSA公私钥对
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        String publicKeyString = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String privateKeyString = Base64.getEncoder().encodeToString(privateKey.getEncoded());

        // 将公钥字符串转换为公钥对象
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        publicKey = KeyFactory.getInstance("RSA").generatePublic(publicKeySpec);

        // 将私钥字符串转换为私钥对象
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        privateKey = KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);

        // 构建JWT token
        String jwtToken = Jwts.builder()
                .setSubject("user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .claim("test", "sdfs")
                .compact();
        System.out.println(jwtToken);

        // 验证JWT token
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(jwtToken);

        // 获取JWT token中的主题信息和自定义声明
        String subject = jws.getBody().getSubject();
        String test = (String) jws.getBody().get("test");
        System.out.println("Subject: " + subject);
        System.out.println("Test: " + test);
    }
}

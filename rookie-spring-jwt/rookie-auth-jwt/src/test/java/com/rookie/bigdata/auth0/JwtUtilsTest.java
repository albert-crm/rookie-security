package com.rookie.bigdata.auth0;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class JwtUtilsTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/25 15:26
 * @Version 1.0
 */

@SpringBootTest
//@ActiveProfiles("uat")
class JwtUtilsTest {


    @Test
    void create() {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 3); // 3天

        Map<String, Object> header = new HashMap<>();
        header.put("alg", "SHA256");
        header.put("type", "JWT");
        String token = JWT.create()
                //.withHeader(header) 一般不写，直接用默认配置
                .withHeader(header) // header
                .withClaim("uid", "001") // payload
                .withClaim("name", "zhangsan") // payload
                .withClaim("admin", true) // payload
                .withExpiresAt(instance.getTime()) // 指定令牌过期时间（3天后过期）
                //chen1020 是加密所用的盐值，自定义
                .sign(Algorithm.HMAC256("chen1020")); // signature
        System.out.println(token);
    }


    @Test
    void verify(){
        // 创建验证对象
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("chen1020")).build();
        // 验证token
        DecodedJWT verify = verifier.verify("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYiLCJ0eXAiOiJKV1QifQ.eyJ1aWQiOiIwMDEiLCJuYW1lIjoiemhhbmdzYW4iLCJhZG1pbiI6dHJ1ZSwiZXhwIjoxNzAzNzQ4NTA5fQ.CmHfGAXzdTpVpFKvuuzADUop6UoILkkNLiBl4RXBaFE");
        // 获取用户信息（验证通过后才能获取）
        System.out.println(verify.getClaims());
        System.out.println("-------------------------------------");
        System.out.println("uid: " + verify.getClaims().get("uid") + " name: " + verify.getClaims().get("name"));
        System.out.println("-------------------------------------");
        System.out.println("uid: " + verify.getClaim("uid") + " name: " + verify.getClaim("name"));
    }

}

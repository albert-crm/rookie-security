package com.rookie.bigdata.security.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Classname CustomAuthenticationProviderTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/3/14 13:40
 * @Version 1.0
 */
class CustomAuthenticationProviderTest {

    @Test
    void authenticate() {
    }

    @Test
    void supports() {


    }


//    @Test
//    public void create() {
//        Calendar instance = Calendar.getInstance();
//        instance.add(Calendar.DATE, 3); // 3天
//
//        Map<String, Object> header = new HashMap<>();
//        header.put("alg", "SHA256");
//        header.put("type", "JWT");
//        String token = JWT.create()
//                .withHeader(header) // header
//                .withClaim("uid", "001") // payload
//                .withClaim("name", "zhangsan") // payload
//                .withClaim("admin", true) // payload
//                .withExpiresAt(instance.getTime()) // 指定令牌过期时间（3天后过期）
//                .sign(Algorithm.HMAC256("chen1020")); // signature
//        System.out.println(token);
//    }


}

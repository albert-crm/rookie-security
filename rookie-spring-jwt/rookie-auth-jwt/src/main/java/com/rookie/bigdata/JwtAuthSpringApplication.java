package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

/**
 * @Class JwtSpringApplication
 * @Description
 * @Author rookie
 * @Date 2023/12/25 10:11
 * @Version 1.0
 */
@SpringBootApplication
public class JwtAuthSpringApplication {
    public static void main(String[] args) {
//        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        SpringApplication.run(JwtAuthSpringApplication.class);
    }
}

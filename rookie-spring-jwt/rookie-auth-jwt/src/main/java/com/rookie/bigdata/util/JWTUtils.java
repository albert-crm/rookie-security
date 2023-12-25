package com.rookie.bigdata.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * @Class JwtUtils
 * @Description https://blog.csdn.net/m0_54355172/article/details/128070287
 * @Author rookie
 * @Date 2023/12/25 13:32
 * @Version 1.0
 */
public class JWTUtils {

    // 盐值
    private static final String SALT = "LyeXro0VaE^!p";

    /**
     * @param map 负载map
     * @return String
     * @Description 创建token
     */
    public static String getToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 5);

        JWTCreator.Builder builder = JWT.create();

        map.forEach((k, v) -> {
            builder.withClaim(k, v);
        });

        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SALT));

        return token;
    }

    /**
     * 验证令牌的过程： 验证签名（SignatureVerificationException） → token是否过期（TokenExpiredException） → 签名算法（AlgorithmMismatchException）
     *
     * @param token
     * @Description 验证token合法性，不合法就会抛出异常
     */
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SALT)).build().verify(token);
    }

}

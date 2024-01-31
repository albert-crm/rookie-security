package com.rookie.bigdata.zhihu;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;
import java.util.List;

/**
 * @Class JwtUtils
 * @Description https://zhuanlan.zhihu.com/p/377720622
 * @Author rookie
 * @Date 2023/12/26 17:12
 * @Version 1.0
 */
public class JwtUtils {
    private static final String PREFIX = "密钥的前缀";
    private static final String SUFFIX = "密钥的后缀";

    /**
     * 产生token，永不过期
     *
     * @param userId 用户ID
     * @param ip     IP
     * @param secret 密钥
     * @return
     */
    public static String create(String userId, String ip, String secret) {
        return create(userId, ip, secret, null);
    }

    public static String create(String userId, String ip, String secret, String other) {
        return create(userId, ip, secret, other, null);
    }

    /**
     * 产生token
     *
     * @param userId 用户ID
     * @param ip     IP
     * @param secret 密钥
     * @param expire 过期时间，单位：毫秒
     * @return
     */
    public static String create(String userId, String ip, String secret, String other, Long expire) {

        JWTCreator.Builder builder = JWT.create().withAudience(userId, ip, String.valueOf(System.currentTimeMillis()), other);
        if (expire != null && expire > 0) {
            builder.withExpiresAt(new Date(System.currentTimeMillis() + expire));
        }
        return builder.sign(Algorithm.HMAC256(PREFIX + secret + SUFFIX));
    }

    /**
     * 获取token中的数据
     *
     * @param token token
     * @return
     * @throws JWTDecodeException
     */
    public static List<String> getAudienceList(String token) throws JWTDecodeException {
        try {
            return JWT.decode(token).getAudience();
        } catch (JWTDecodeException e) {
            throw new JWTDecodeException(e.getMessage());
        }
    }

    /**
     * 验证token是否有效
     *
     * @param token  token
     * @param secret 密钥
     * @return
     * @throws JWTVerificationException
     */
    public static boolean verify(String token, String secret) throws JWTVerificationException {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(PREFIX + secret + SUFFIX)).build();
            jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException(e.getMessage());
        }
    }
}


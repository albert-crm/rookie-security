package com.rookie.bigdata.zhihu;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.KeyUse;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;

/**
 * @Class JwtRsaUtils
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/26 17:52
 * @Version 1.0
 */
@Slf4j
public class JwtRsaUtils {

    /**
     * 提供公钥字符串，返回RSAKey
     *
     * @param keyId
     * @param publicKey
     * @return
     */
    public static RSAKey getRsaKey(String keyId, String publicKey) throws InvalidKeySpecException {
        return getRsaKey(keyId, RsaUtils.getPublicKey(publicKey));
    }

    /**
     * 提供公钥和私钥字符串，返回RSAKey
     *
     * @param keyId
     * @param publicKey
     * @param privateKey
     * @return
     */
    public static RSAKey getRsaKey(String keyId, String publicKey, String privateKey) throws InvalidKeySpecException {
        return getRsaKey(keyId, RsaUtils.getPublicKey(publicKey), RsaUtils.getPrivateKey(privateKey));
    }

    /**
     * 提供公钥，返回RSAKey
     *
     * @param keyId
     * @param publicKey
     * @return
     */
    public static RSAKey getRsaKey(String keyId, PublicKey publicKey) {
        return new RSAKey.Builder((RSAPublicKey) publicKey)
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(keyId)
                .build();
    }

    /**
     * 提供公钥和私钥，返回RSAKey
     *
     * @param keyId
     * @param publicKey
     * @param privateKey
     * @return
     */
    public static RSAKey getRsaKey(String keyId, PublicKey publicKey, PrivateKey privateKey) {
        return new RSAKey.Builder((RSAPublicKey) publicKey)
                .privateKey(privateKey)
                .keyUse(KeyUse.SIGNATURE)
                .algorithm(JWSAlgorithm.RS256)
                .keyID(keyId)
                .build();
    }

    /**
     * 根据RSAKey签名
     *
     * @param rsaKey
     * @return
     * @throws JOSEException
     */
    public static String sign(RSAKey rsaKey) throws JOSEException {
        return sign(rsaKey, new JWTClaimsSet.Builder().build());
    }

    /**
     * 根据RSAKey签名
     *
     * @param rsaKey
     * @param aud
     * @return
     * @throws JOSEException
     */
    public static String sign(RSAKey rsaKey, String... aud) throws JOSEException {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .audience(Arrays.asList(aud))
                .build();
        return sign(rsaKey, claimsSet);
    }

    /**
     * 根据RSAKey签名，可设置过期时间
     *
     * @param rsaKey
     * @param expire 过期时间，单位毫秒
     * @param aud
     * @return
     * @throws JOSEException
     */
    public static String sign(RSAKey rsaKey, long expire, String... aud) throws JOSEException {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .audience(Arrays.asList(aud))
                .expirationTime(new Date(System.currentTimeMillis() + expire))
                .build();
        return sign(rsaKey, claimsSet);
    }

    /**
     * 根据RSAKey签名，可设置过期时间
     *
     * @param rsaKey
     * @param issuer  iss
     * @param subject sub
     * @param expire  过期时间，单位毫秒
     * @param aud
     * @return
     * @throws JOSEException
     */
    public static String sign(RSAKey rsaKey, String issuer, String subject, long expire, String... aud) throws JOSEException {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .issuer(issuer)
                .subject(subject)
                .audience(Arrays.asList(aud))
                .expirationTime(new Date(System.currentTimeMillis() + expire))
                .build();
        return sign(rsaKey, claimsSet);
    }

    /**
     * 签名
     *
     * @param rsaKey
     * @param claimsSet
     * @return
     * @throws JOSEException
     */
    public static String sign(RSAKey rsaKey, JWTClaimsSet claimsSet) throws JOSEException {
        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaKey.getKeyID()).build(),
                claimsSet);
        signedJWT.sign(new RSASSASigner(rsaKey));
        return signedJWT.serialize();
    }

    /**
     * 验证签名
     *
     * @param rsaKey
     * @param token
     * @return
     */
    public static boolean verify(RSAKey rsaKey, String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            if (signedJWT.getJWTClaimsSet().getExpirationTime() != null) {
                if (!new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime())) {
                    return false;
                }
            }

            RSASSAVerifier verifier = new RSASSAVerifier(rsaKey);
            return signedJWT.verify(verifier);
        } catch (ParseException e) {
            log.debug("解析JWT失败", e);
            return false;
        } catch (JOSEException e) {
            log.debug("解析JWT时密钥错误", e);
            return false;
        }
    }

    /**
     * 验证签名并返回JWT对象
     *
     * @param rsaKey
     * @param token
     * @return 如果返回值为null就表示验证失败
     */
    public static SignedJWT verifyWithData(RSAKey rsaKey, String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);

            if (signedJWT.getJWTClaimsSet().getExpirationTime() != null) {
                if (!new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime())) {
                    return null;
                }
            }

            RSASSAVerifier verifier = new RSASSAVerifier(rsaKey);
            if (signedJWT.verify(verifier)) {
                return signedJWT;
            } else {
                return null;
            }
        } catch (ParseException e) {
            log.debug("解析JWT失败", e);
            return null;
        } catch (JOSEException e) {
            log.debug("解析JWT时密钥错误", e);
            return null;
        }
    }
}

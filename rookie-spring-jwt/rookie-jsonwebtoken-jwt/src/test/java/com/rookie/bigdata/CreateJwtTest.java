package com.rookie.bigdata;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @Class CreateJwtTest
 * @Description https://www.cnblogs.com/wrc-blog/p/14256071.html
 * https://blog.csdn.net/u010142437/article/details/16370017
 * https://vimsky.com/examples/detail/java-class-org.springframework.security.jwt.JwtHelper.html
 * @Author rookie
 * @Date 2023/12/25 10:31
 * @Version 1.0
 */
public class CreateJwtTest {

    @Test
    public void createJWT() {
        //基于私钥生成JWT
        //创建一个密钥工厂
        //私钥的位置  本模块中resources中的changgou.jks就是私钥，public.key就是公钥
        ClassPathResource classPathResource = new ClassPathResource("changgou.jks");
        //密钥库的密码
        String keyPass = "changgou";
        /**
         * 参数1 私钥的位置
         * 参数2 密钥库的密码
         */
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(classPathResource, keyPass.toCharArray());
        //基于工厂获取私钥
        //密钥的别名
        String alias = "changgou";
        //密钥的密码
        String password = "changgou";
        /**
         * 参数1 密钥的别名
         * 参数2 密钥的密码
         */
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(alias, password.toCharArray());
        //将当前的私钥转为RSA的私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        //生成jwt
        Map<String, String> map = new HashMap<>();
        map.put("company", "heima");
        map.put("address", "beijing");

        /**
         * 参数1 当前的令牌的内容
         * 参数2 签名（用RSA的私钥来做签名）
         */
        Jwt jwt = JwtHelper.encode(JSON.toJSONString(map), new RsaSigner(rsaPrivateKey));
        String jwtEncoded = jwt.getEncoded();
        System.out.println(jwtEncoded);
    }


    @Test
    public void parseJwt() {
        //基于公钥解析jwt 这是直接复制的CreateJwtTest.class的打印结果
        String jwt = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJhZGRyZXNzIjoiYmVpamluZyIsImNvbXBhbnkiOiJoZWltYSJ9.UQpPl2iPWDYoCg1aGW4oF5XOoo8Bldo2iK3nJ9yoUqNCP66YOWKlC3CNdVw3ReJBR-N5_jXqo4GA3vbMm-2gdPy5x5oQ7kc2_O68mVpom7aNaYNfQ9Nzm4DpikZuhhICGMHbRTsLOoH096FlOS4nAxk0j69OCejUBWjswgBbe6d0G8I0rKLHMqB5YA8oq5ewGEI7__cWhIeFq8ob_KdXMesRgz_YtCGtEuafWJxTMkcXwfIcpRvfCFnAdZsZ8_AqZ26ArGMBoUBn4zth7-XPYLLY-ekUVXmIgKEElYNcwBC4mVuIHK5kvNkbWJAE8MBjCsdioXYixrAIoONK7AYcjg";
        //公钥,直接复制的Public.key里的公钥
//        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvFsEiaLvij9C1Mz+oyAmt47whAaRkRu/8kePM+X8760UGU0RMwGti6Z9y3LQ0RvK6I0brXmbGB/RsN38PVnhcP8ZfxGUH26kX0RK+tlrxcrG+HkPYOH4XPAL8Q1lu1n9x3tLcIPxq8ZZtuIyKYEmoLKyMsvTviG5flTpDprT25unWgE4md1kthRWXOnfWHATVY7Y/r4obiOL1mS5bEa/iNKotQNnvIAKtjBM4RlIDWMa6dmz+lHtLtqDD2LF1qwoiSIHI75LQZ/CNYaHCfZSxtOydpNKq8eb1/PGiLNolD4La2zf0/1dlcr5mkesV570NxRmU1tFm8Zd3MZlZmyv9QIDAQAB-----END PUBLIC KEY-----";
        String publicKey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAirK2tgQKLXnnDnYhHD7PeObWKWwE5y6wMjXIZB1zlOYHnyADtABD9nqDJ7CGcu0FuCY2CRIKPBrb3MKFw5riUm9knN7JNh2xowCfeF1NNIPrmSRIthaZdMJfugUQLOGevG//t9z3R57LU0pFw6JJZPsCo1yz/0339UmdPWTUHEw6kojvFmo7MoJWiYNdgYLBiBWqHCUtswto4PB/adrazET9+XrnU4aiAt4fJ7qWnJYjvo4EhkC9nzoBkDRrJvObHfrtyBpcK0/98o67f5lNQ7Yb/x7F0hu+bmCL/a5jZYZTCd8zbalBtR3M4BKnpb1a1180WUy068ZUelMm8uTKlQIDAQAB-----END PUBLIC KEY-----";
        //解析和验签jwt，获取令牌
        Jwt token = JwtHelper.decodeAndVerify(jwt, new RsaVerifier(publicKey));
        //解析令牌，获取令牌中的载荷
        String claims = token.getClaims();
        System.out.println(claims);//打印结果为{"address":"beijing","company":"heima"}
    }

}

package com.rookie.bigdata.zhihu;

import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class JwtRsaUtilsTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/26 17:54
 * @Version 1.0
 */
class JwtRsaUtilsTest {

    @Test
    void getRsaKey() {
    }

    @Test
    void testGetRsaKey() {
    }

    @Test
    void test01(){
//        // keyId 是随便填的字符串
//        RSAKey rsaKey = JwtRsaUtils.getRsaKey(keyId, publicKey, privateKey);
//        String token = JwtRsaUtils.sign(rsaKey, issuer, subject, expireTime,
//                defaultAccount.getId().toString(),
//                defaultAccount.getUserId().toString(),
//                defaultAccount.getCustomerId().toString(),
//                defaultAccount.getName(),
//                defaultAccount.getAccountType());
//
//// 直接解析不验证，这一步是不需要密钥的，因为是明文的
//        SignedJWT jwt = SignedJWT.parse(token);
//        List<String> audience = jwt.getJWTClaimsSet().getAudience();
//
//// 验证
//        if (JwtRsaUtils.verify(rsaKey, token)) {
//            // 验证通过
//        }
//
//// 验证并返回payload
//        SignedJWT jwt = JwtRsaUtils.verifyWithData(rsaKey, token);
//        if (jwt != null) {
//            // 验证通过
//            List<String> audience = jwt.getJWTClaimsSet().getAudience();
//        }
    }
}

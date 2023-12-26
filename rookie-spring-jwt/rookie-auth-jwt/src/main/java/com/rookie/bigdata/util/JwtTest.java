//package com.rookie.bigdata.util;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwt;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Class JwtTest
// * @Description https://segmentfault.com/a/1190000040517605
// * @Author rookie
// * @Date 2023/12/26 12:08
// * @Version 1.0
// */
//public class JwtTest {
//
//    public static void main(String[] args) {
//        String str = makeToken();
//        parseToken(str);
//    }
//
//    private static void parseToken(String str) {
//        // 放入签发的密钥，对JWT进行解密
//        Jwt info = Jwts.parser().setSigningKey("changanbujian").parse(str);
//        System.out.println(info);
//    }
//
//    private static String makeToken() {
//        Map<String, Object> header = new HashMap<>();
//        header.put("alg", "HS256");
//        header.put("typ", "JWT");
//
//        Claims claims = new DefaultClaims();
//        claims.setId("ccc").setSubject("aaa").setIssuer("bbb").setExpiration(new Date(System.currentTimeMillis() + 1800 * 1000));
//        String secret = "changanbujian";
//        // 上面讲过JWT需要Base64的形式
//        byte[] saltBase64 = DatatypeConverter.parseBase64Binary(secret);
//        SignatureAlgorithm hs256 = SignatureAlgorithm.HS256;
//        SecretKeySpec secretKeySpec = new SecretKeySpec(saltBase64, hs256.getJcaName());
//        String jwtToken = Jwts.builder().setHeader(header).setClaims(claims).signWith(hs256, secretKeySpec).compact();
//        return jwtToken;
//    }
//}

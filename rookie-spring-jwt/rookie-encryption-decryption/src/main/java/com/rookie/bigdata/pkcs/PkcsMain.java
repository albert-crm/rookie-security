package com.rookie.bigdata.pkcs;

import org.apache.tomcat.util.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/**
 * @Class PkcsMain
 * @Description 数字签名
 * @Author rookie
 * @Date 2023/12/26 9:58
 * @Version 1.0
 */
public class PkcsMain {
//    数字签名流程
//    本地发送请求时（本地已对请求根据私钥进行加签）      接收方平台根据公钥进行验签          判断是否合法
//
//    接收来自平台的响应时（平台已根据私钥进行加签）      需要根据本地公钥对响应进行验签    判断是否合法
private static Signature signature;

    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = Pkcs8Utils.loadPrivateKey("pkcs/private#8.pem");

        // 用私钥对信息生成数字签名
        signature = Signature.getInstance("SHA1WithRSA");//SHA1WithRSA
        signature.initSign(privateKey);
        String currentTime = System.currentTimeMillis() + "";

        String json = "{\"id\":123}";

        String body = String.format("appId=%s&bizContent=%s&timestamp=%s", "123", json, currentTime);
        signature.update(body.getBytes(StandardCharsets.UTF_8));

        // byte[] bytes = Base64.encodeBase64(signature.sign());
        //生成的sign值
        String sign = new String(Base64.encodeBase64(signature.sign()));
        System.out.println(sign);

    }





}

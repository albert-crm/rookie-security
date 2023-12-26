package com.rookie.bigdata.pkcs;

/**
 * @Class RSATest2
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/26 10:37
 * @Version 1.0
 */


import org.apache.tomcat.util.codec.binary.Base64;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * SHA1WithRSA-数字签名
 * Created by yanshao on 2018/12/12.
 */
// TODO: 2018/12/12 数字签名就发现这个能用
// TODO: 2018/12/12 原文地址：https://blog.csdn.net/qq_23974323/article/details/77678491
public class RSATest2 {

    //加密算法
    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    //私钥
    private static final String privateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCqrpUHZx/NSRsK" +
            "jQc9i/gCGzU9Q3qRf44pm0lML9FfJf+m9mo9qdAEsMl3N0Y/nVcdGWb8VBry+dyl" +
            "mKNR4VPfFNGQKWW8OtvUlT9l3I9/MTd/ZsoVt2dQdBvOp7+9hrClS+rmw/HFz81l" +
            "RCFcku8HIIIcFxPQylRgSxHI8PMVJEodBsQSRvOzGtzWTfhTfG0Y0sgZPt75hKjd" +
            "J1rTo4264AG3YzlxHlmqrrQxxmYEKEnNplmNVga2bXvPICZ2VPbl9w/52mlSobg3" +
            "6VPKMzdlhTUgFsADPMaG2Pf3GqOa2GTSupzgTyz/54LyF6gCbXcAknRFgH5eyAv5" +
            "tPqV9CsrAgMBAAECggEAAXIHCxABgfCLjRRSql/EEuh+E+29XPwSjSGmhkGlaUPe" +
            "HWDa13jXrSJ+IkdSjflcIn/zklF4BPS+vJxFTc01s57ug2UGWoi5EdzNs6Qhhvc4" +
            "vBh3v6VU96Z0EdTz17wLROsWqyufoYg3+hKQocMQySOqVmiPn2YHPuWD2grIVDZ9" +
            "68mC1FykGEcv9De0m6yVEsfZDXNUxm3cz1758iBqakvyOVxGsI+V+e7/iSxJiwIB" +
            "6f3NSGQVtEsqwyhnl6dZRYDtnq5iUiwUOshl5Z2CYBfBcyTpMKC2RuHp3u9THHpc" +
            "3TFE4I1Li5HkiFy+ai6QKl2M85ce3GCXmjyw7n2vmQKBgQDTHihNX6uu6YlVFcRa" +
            "XhhGigyLrIP8LbLd4r/dKMXuGa8XqkLPlDtXelh2n565Lo5DPGlpANi3Jp495Hzn" +
            "bL+YnHJs7boVOGtORB0XHUbiMaTlT85snpvVjwFngHvY/ZxtXclpsnX563AvzCyl" +
            "amrKEhV17BFZbRQOTEL1UNp57QKBgQDO98AyfVgs1tCdtLOsFSFiWrAsJJimBS7b" +
            "ec+W/UPGDAl3hFzQzJ2SUvF4EneatYVOEDdHLUzVnT7XXeA/eMZL56N8BTSffh8q" +
            "XK0E21K8tW4hRbze6CIjbsJ1x7ZLZaoM4Ub2YAvkulF0PcasX0kWl+bv/DnkI09x" +
            "/n3vgbO2dwKBgQCoAnj6UmeztEDJiKARdo6FHHmtciY7Ozb8Y+Zin38c5C22fJXc" +
            "0k+DZ2cdSBwtrQIkOeB9YuIUp1QJV1ubZKz5S4+4ZlvPZW3oBEbOTUtK2U0r/J3/" +
            "TR4hD0SD1Pk6j2G8m4Wdaxt+P8KxFyB0p8LCey++/5Yy/56VXlVvGuAzZQKBgQCU" +
            "VfcXeMTIplGwpkGcFSzvLCZWDQim/NH/lYdWJUD84cWrNl+7ett4cyADueClLnJT" +
            "Z8Xmqq4F8ASJIQxHEY21+1gt3CFCKoe1ueR7taHQBIzhJfVfIarOEGUpOzEJSt0d" +
            "DBzrGh2MGomksV4CTuy4V7i5yeHIBBK9lfO2xBQEswKBgQCIzYO53kTFl6YGjmWO" +
            "qUJsT+5WegR4GdxtqYpQGPC1RmU7ig1TZzen+X3xB+lIHqgA1HvTr6M+tPkmnMwU" +
            "iARPOgjXY0zmsStXaHQYKruT3EjZRs2GnmVpVOAj1asqi+/2t0NgLgB5gPLYMXS+" +
            "BGf01OehvUt5Ge+OChDBXSW5Bw==";

    //公钥
    private static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqq6VB2cfzUkbCo0HPYv4" +
            "Ahs1PUN6kX+OKZtJTC/RXyX/pvZqPanQBLDJdzdGP51XHRlm/FQa8vncpZijUeFT" +
            "3xTRkCllvDrb1JU/ZdyPfzE3f2bKFbdnUHQbzqe/vYawpUvq5sPxxc/NZUQhXJLv" +
            "ByCCHBcT0MpUYEsRyPDzFSRKHQbEEkbzsxrc1k34U3xtGNLIGT7e+YSo3Sda06ON" +
            "uuABt2M5cR5Zqq60McZmBChJzaZZjVYGtm17zyAmdlT25fcP+dppUqG4N+lTyjM3" +
            "ZYU1IBbAAzzGhtj39xqjmthk0rqc4E8s/+eC8heoAm13AJJ0RYB+XsgL+bT6lfQr" +
            "KwIDAQAB";


    /**
     * 使用私钥给入参签名
     *
     * @param privateKey 私钥
     * @param param      签名的数据
     * @return 返回入参签名16进制字符串
     */
    public static String sign(String privateKey, String param) {
        try {
            //获取privatekey
            byte[] privateKeyByte = new Base64().decode(privateKey);
            KeyFactory keyfactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec encoderule = new PKCS8EncodedKeySpec(privateKeyByte);
            PrivateKey key = keyfactory.generatePrivate(encoderule);

            //用私钥给入参加签
            Signature sign = Signature.getInstance(SIGN_ALGORITHMS);
            sign.initSign(key);
            sign.update(param.getBytes());

            byte[] signature = sign.sign();
            //将签名的入参转换成16进制字符串
            return bytesToHexStr(signature);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 用公钥验证签名
     *
     * @param param     入参
     * @param signature 使用私钥签名的入参字符串
     * @param publicKey 公钥
     * @return 返回验证结果
     */

    public static boolean verifyRes(String param, String signature, String publicKey) {
        try {
            //获取公钥
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] publicKeyByte = new Base64().decode(publicKey);
            X509EncodedKeySpec encodeRule = new X509EncodedKeySpec(publicKeyByte);
            PublicKey key = keyFactory.generatePublic(encodeRule);

            //用获取到的公钥对   入参中未加签参数param 与  入参中的加签之后的参数signature 进行验签
            Signature sign = Signature.getInstance(SIGN_ALGORITHMS);
            sign.initVerify(key);
            sign.update(param.getBytes());

            //将16进制码转成字符数组
            byte[] hexByte = hexStrToBytes(signature);
            //验证签名
            return sign.verify(hexByte);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * byte数组转换成十六进制字符串
     *
     * @param bytes byte数组
     * @return 返回十六进制字符串
     */
    private static String bytesToHexStr(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer("");
        for (int i = 0; i < bytes.length; ++i) {
            stringBuffer.append(Integer.toHexString(0x0100 + (bytes[i] & 0x00FF)).substring(1).toUpperCase());
        }
        return stringBuffer.toString();
    }

    /**
     * 十六进制字符串转成byte数组
     *
     * @param hexStr 十六进制字符串
     * @return 返回byte数组
     */
    private static byte[] hexStrToBytes(String hexStr) {
        byte[] bytes = new byte[hexStr.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(hexStr.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public static void main(String[] args) {
        String param = "yanshao";
        String sign = sign(privateKey, param);
        System.out.println("签名后的参数>>>" + sign);
        System.out.println("验证结果>>>" + verifyRes(param, sign, publicKey));
    }

}

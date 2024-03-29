package com.rookie.bigdata.at0x7c00;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


import java.security.*;


/**
 * @Class RSAUtil
 * @Description
 * @Author rookie
 * @Date 2024/2/21 11:34
 * @Version 1.0
 */
public class RSAUtil {

    public final static String ALGORITHM = "RSA";
    public final static String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥密钥对
     *
     * @return
     * @throws Exception
     */
    public static KeyPair getKey() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
        return generator.generateKeyPair();
    }

    private static Key getPublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key k = keyFactory.generatePublic(keySpec);
        return k;
    }

    private static Key getPrivateKey(String key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Util.decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key k = keyFactory.generatePrivate(keySpec);
        return k;
    }

    /**
     * 使用公钥进行加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptByPublicKey(String data, String key) throws Exception {

        Key k = getPublicKey(key);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);

        byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));

        return Base64Util.encode(bytes);
    }

    /**
     * 使用私钥进行加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptByPrivateKey(String data, String key) throws Exception {

        Key k = getPrivateKey(key);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);

        byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));

        return Base64Util.encode(bytes);
    }

    /**
     * 使用密钥进行解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByPrivateKey(String data, String key) throws Exception {
        Key k = getPrivateKey(key);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);

        byte[] bytes = cipher.doFinal(Base64Util.decode(data));

        return new String(bytes, "UTF-8");
    }

    /**
     * 使用公钥进行解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decryptByPublicKey(String data, String key) throws Exception {
        Key k = getPublicKey(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);

        byte[] bytes = cipher.doFinal(Base64Util.decode(data));

        return new String(bytes, "UTF-8");
    }

    /**
     * 使用私钥进行签名
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String sign(String data, String key) throws Exception {
        PrivateKey k = (PrivateKey) getPrivateKey(key);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(k);
        signature.update(data.getBytes("UTF-8"));
        return Base64.encode(signature.sign());
    }

    /**
     * 使用公钥进行签名验证
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static boolean signVerify(String data, String key, String sign) throws Exception {
        PublicKey k = (PublicKey) getPublicKey(key);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(k);
        signature.update(data.getBytes("UTF-8"));
        return signature.verify(Base64.decode(sign));
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = getKey();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();

        String privateKeyStr = Base64.encode(privateKey.getEncoded());
        String publicKeyStr = Base64.encode(publicKey.getEncoded());

        System.out.println("私钥：" + privateKeyStr);
        System.out.println("公钥：" + publicKeyStr);

        String data = "Hello,RSA,Hello,RSAHello,RSAHello,RSAHello,RSAHello,RSAHello,RSA";
        System.out.println("---------------公钥加密，私钥解密-----------------");
        String encryptedData = encryptByPublicKey(data, publicKeyStr);
        System.out.println("加密后：" + encryptedData);

        String decryptedData = decryptByPrivateKey(encryptedData, privateKeyStr);
        System.out.println("解密后：" + decryptedData);
        System.out.println("---------------私钥加密，公钥解密-----------------");

        encryptedData = encryptByPrivateKey(data, privateKeyStr);
        System.out.println("加密后：" + encryptedData);
        decryptedData = decryptByPublicKey(encryptedData, publicKeyStr);
        System.out.println("解密后：" + decryptedData);

        String sign = sign(data, privateKeyStr);
        System.out.println("签名：" + sign);
        System.out.println("签名验证：" + signVerify(data, publicKeyStr, sign));


    }


//
//    public final static String ALGORITHM = "RSA";
//
//    public static KeyPair getKey() throws Exception {
//        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
//        return generator.generateKeyPair();
//    }
//
//    public static void main(String[] args) throws Exception {
//        KeyPair keyPair = getKey();
//        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//
//        String privateKeyStr = Base64.encode(privateKey.getEncoded());
//        String publicKeyStr = Base64.encode(publicKey.getEncoded());
//
//        System.out.println("私钥：" + privateKeyStr);
//        System.out.println("公钥：" + publicKeyStr);
//
//        String abc = encryptByPublicKey("abc", publicKeyStr);
//        System.out.println("加密数据：" + abc);
//
//        String jabc = decryptByPrivateKey(abc, privateKeyStr);
//        System.out.println("加密数据：" + jabc);
//
//
//    }
//
//
//    /**
//     * 使用公钥加密
//     *
//     * @param data
//     * @param key
//     * @return
//     * @throws Exception
//     */
//    public static String encryptByPublicKey(String data, String key) throws Exception {
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.decode(key));
//        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
//
//        Key k = keyFactory.generatePublic(keySpec);
//
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.ENCRYPT_MODE, k);
//
//        byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));
//
//        return Base64Util.encode(bytes);
//    }
//
//
//
//    /**
//     * Exception in thread "main" java.security.spec.InvalidKeySpecException: Only RSAPrivate(Crt)KeySpec and PKCS8EncodedKeySpec supported for RSA private keys
//     *
//     * @param data
//     * @param key
//     * @return
//     * @throws Exception
//     */
//    public static String decryptByPrivateKey1(String data, String key) throws Exception {
//
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.decode(key));
//        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
//
//        Key k = keyFactory.generatePrivate(keySpec);
//
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, k);
//
//        byte[] bytes = cipher.doFinal(Base64Util.decode(data));
//
//        return new String(bytes, "UTF-8");
//    }
//
//
//    /**
//     * 使用私钥解密
//     *
//     * @param data
//     * @param key
//     * @return
//     * @throws Exception
//     */
//    public static String decryptByPrivateKey(String data, String key) throws Exception {
//
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64Util.decode(key));
//        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
//
//        Key k = keyFactory.generatePrivate(keySpec);
//
//        Cipher cipher = Cipher.getInstance(ALGORITHM);
//        cipher.init(Cipher.DECRYPT_MODE, k);
//
//        byte[] bytes = cipher.doFinal(Base64Util.decode(data));
//
//        return new String(bytes, "UTF-8");
//    }


}

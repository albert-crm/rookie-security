package com.rookie.bigdata.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * @Class AESUtil
 * @Description https://www.cnblogs.com/at0x7c00/p/7688082.html
 * @Author rookie
 * @Date 2023/12/25 14:26
 * @Version 1.0
 */
public class AESUtil {


    static final String ALGORITHM = "AES";

    /**
     * 生成文本格式的DES Key
     *
     * @return
     * @throws Exception
     */
    public static String getKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
        generator.init(new SecureRandom());//加盐
        return Base64Util.encode(generator.generateKey().getEncoded());
    }

    /**
     * 从文本 格式DES Key转换成SecretKey对象
     *
     * @param key
     * @return
     */
    public static SecretKey parseKeyFromString(String key) throws Exception {
        SecretKey secretKey = new SecretKeySpec(Base64Util.decode(key), ALGORITHM);
        return secretKey;
    }

    /**
     * DES 加密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrypt(String data, String key) throws Exception {
        SecretKey secretKey = parseKeyFromString(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64Util.encode(bytes);
    }

    /**
     * DES 解密
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        SecretKey secretKey = parseKeyFromString(key);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] bytes = cipher.doFinal(Base64Util.decode(data));
        return new String(bytes, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        String str = "Hello,AES";
        String key = getKey();
        System.out.println("原文：" + str);
        System.out.println("密钥：" + key);
        String encryptedStr = encrypt(str, key);
        System.out.println("加密后:" + encryptedStr);
        String decryptedStr = decrypt(encryptedStr, key);
        System.out.println("解密后：" + decryptedStr);
    }

}

package com.rookie.bigdata.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @Class DESUtil
 * @Description https://www.cnblogs.com/at0x7c00/p/7688082.html
 * https://www.cnblogs.com/at0x7c00/p/7519600.html
 * @Author rookie
 * @Date 2023/12/25 14:24
 * @Version 1.0
 */
public class DESUtil {

//    1)DES(Data Encryption Standard,数据加密标准)加密之后，密文的长度的8的整数倍，秘钥长度是8字节(password)，即64bit位
//
//    2)AES(Advanced Encryption Standard,高级加密标准)加密和解密，秘钥长度是16字节，即128bit位
//
//    3)对称加密密钥长度分析：
//
//            4）对称加密的特点：加密计算量小、速度块，适合对大量数据进行加密的场景。（记住这个特点，实际使用是会用到的）


    static final String ALGORITHM = "DES";

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
        DESKeySpec desKeySpec = new DESKeySpec(Base64Util.decode(key));
        SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey secretKey = factory.generateSecret(desKeySpec);
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
        String str = "Hello,DES";
        String key = getKey();
        System.out.println("原文：" + str);
        System.out.println("密钥：" + key);
        String encryptedStr = encrypt(str, key);
        System.out.println("加密后:" + encryptedStr);
        String decryptedStr = decrypt(encryptedStr, key);
        System.out.println("解密后：" + decryptedStr);
    }

}

package com.rookie.bigdata.rsa;

import com.rookie.bigdata.pkcs.Pkcs8Utils;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Class RsaMain
 * @Description https://blog.csdn.net/weixin_40147979/article/details/127847342
 * https://blog.csdn.net/qq_35427589/article/details/133745697
 * @Author rookie
 * @Date 2023/12/26 10:51
 * @Version 1.0
 */
public class RsaMain {


    /**
     * 通过加载公钥与私钥的pcks8文件进行数据的加密与解密
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String originalText = "Hello, RSA encryption and decryption!";


        //公钥与私钥需要生成为pkcs8格式的文件
        //1、获取公钥
        PublicKey publicKey = RSAUtil.loadPublicKey("rsa/public#8.pem");
        //2、获取私钥
        PrivateKey privateKey = RSAUtil.loadPrivateKey("rsa/private#8.pem");


        // 使用公钥加密数据
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = encryptCipher.doFinal(originalText.getBytes());

        // 使用私钥解密数据
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = decryptCipher.doFinal(encryptedBytes);

        String decryptedText = new String(decryptedBytes);

        //new BASE64Encoder().encode(decryptedBytes)
        System.out.println("加密后的数据: " + new BASE64Encoder().encode(decryptedBytes));
//        System.out.println("加密后的数据: " + Base64.getEncoder().encodeToString(encryptedBytes));
        System.out.println("解密后的数据: " + decryptedText);


    }
}

package com.rookie.bigdata.encryption;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;


/**
 * @Class RASUtil
 * @Description https://www.cnblogs.com/at0x7c00/p/7688124.html
 * @Author rookie
 * @Date 2023/12/25 14:35
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

    /**
     * 使用私钥获取公钥
     *
     * @param key
     * @return
     * @throws Exception
     */
    private static Key getPublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64Util.decode(key));
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        Key k = keyFactory.generatePublic(keySpec);
        return k;
    }

    /**
     * 使用公钥获取私钥
     *
     * @param key
     * @return
     * @throws Exception
     */
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


//    私钥加密和公钥加密的数据字节数不能超过245个字节，否则会出现下述的异常
//    javax.crypto.IllegalBlockSizeException: Data must not be longer than 245 bytes
//
//    分段加密思想：
//    设置一个常量，表示RSA一次加密的最大字节数245byte；然后将明文字段加密若干次，若待加密的明文的字节数正好是245的整数倍，
//    则直接加密完即可；若加密到剩余不足245字节的明文，使用普通方式进行加密即可；
//
//    分段加密用到的类：
//    Cipher：加密/解密算法核心类
//    ByteArrayOutputStream:字节数组输出流
//
//    Cipher类加密三部曲：
//            1)创建Cipher类的对象
//    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//        2)初始化加密/解密的方式：加密、解密
//            cipher.init(加密/解密，key)；
//            3）加密/解密
//    doFinal() ---- 完成多部分加密或解密操作，这取决于该密码如何初始化。
//    doFinal(byte[] input)  ---- 在单一部分操作中加密或解密数据，或完成多部分操作
//    doFinal(byte[] input, int inputOffset, int inputLen) ---- 在单一部分操作中加密或解密数据，或完成多部分操作

//    每一次的生成的公钥和私钥都是不一样的
//
//    保存方式：
//    将生成的私钥和公钥以常量的形式保存起来，
//    使用秘钥工厂KeyFactory来创建秘钥工厂对象 kf = KeyFactory.getInstance("算法");
//
//    使用工厂对象 kf 来生成公钥和私钥
//
//    其中私钥使用PKCS8EncodeKeySpec(参数:KeySpec)编码规范：Only RSAPrivate(Crt)KeySpec and PKCS8EncodedKeySpec supported for RSA private keys
//
//    公钥使用的编码规范 ：Only RSAPublicKeySpec and X509EncodedKeySpec supported for RSA public keys

}

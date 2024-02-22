package com.rookie.bigdata.at0x7c00.dh.dh01;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @Class DHTest
 * @Description https://www.jianshu.com/p/4c9bebbda2e2
 * https://blog.csdn.net/fengzun_yi/article/details/104497160
 * @Author rookie
 * @Date 2024/2/21 17:43
 * @Version 1.0
 * <p>
 * DH算法的目的仅在于<b>双方在安全的环境下协商一个加解密的密钥</b>，因此仅仅用于密钥分配，不能用于加解密消息。
 * <p>
 * 应用场景
 * 仅用于密钥交换场景，不适用于数据传输的加解密，如AB两个系统需要交换密钥，则过程如下：
 * <p>
 * A系统构建密钥：构建一对公私密钥Private Key1和Public Key1；
 * A系统向B系统公布自己的公钥（Public Key1）；
 * B系统使用A公布的公钥（Public Key1）建立一对密钥：Private Key2和Public Key2；
 * B系统向A系统公布自己的公钥Public Key2；
 * A系统使用自己的私钥Private Key1和B系统的公钥Public Key2构建本地密钥；
 * B系统使用自己的私钥Private Key2和A系统的公钥Public Key1构建本地密钥；
 * <p>
 * 关键点：B系统使用A系统的公钥建立加密用的Key；
 * <p>
 * 本地密钥用来加解密数据；
 * <p>
 * 虽然AB系统使用了不同的密钥建立自己的本地密钥，但是AB系统获得本地密钥是一致的。
 *
 *
 *流程描述
 *
 * sequenceDiagram
 * A->> A: 构建密钥对：private key1 和 public key1
 * A->> B: 公布自己的公钥: public key1
 * B->> B: 使用public key1构建自己的密钥对 private key2 和 public key2；
 * B-->> A: 返回自己的public key2；
 * A->> A: 使用private key1 和 public key2 构建本地密钥；
 * B->> B : 使用private key2 和 public key1构建本地密钥；
 *
 */
public class DHTest {
    // 密钥交换算法
    public static final String KEY_ALGORITH = "DH";
    // 对称加密算法
    public static final String SECRET_ALGORITH = "AES";
    // DH算法的密钥长度
    private static final int KEY_SIZE = 512;
    // Map的一些参数
    private static final String MAP_KEY_PUBLIC = "DHPublicKey";
    private static final String MAP_KEY_PRIVATGE = "DHPrivateKey";

    //我们在使用AES算法的过程中,使用了相同的密钥,那么如果在实际应用中: 在网络上的两个用户之间需要传输文件时,A使用自己的密钥向B发送了加密文件,那么B就需要A的密钥来解密文件。
    //
    //那么这就会出现安全隐患:
    //
    //在不安全的信道上A发送的加密文件被黑客拿到了,但是由于是加密过的,所以没有什么用。但是如果被黑客拿到了密钥呢?
    //
    //要解决这个问题,就需要使用密钥交换算法: DH算法(Diffie-Hellman算法)。DH算法解决了在双方不直接传递密钥的情况下完成密钥交换。
    //————————————————
    //
    //                            版权声明：本文为博主原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接和本声明。
    //
    //原文链接：https://blog.csdn.net/m0_64529944/article/details/131858579


    public static void main(String[] args) throws Exception {
        // A系统构建自己的公私钥
        Map<String, Key> aKeyMap = initASysKey();
        byte[] aPubKey = aKeyMap.get(MAP_KEY_PUBLIC).getEncoded();
        byte[] aPriKey = aKeyMap.get(MAP_KEY_PRIVATGE).getEncoded();
        logKeyMap("A", aPubKey, aPriKey);

        // A将自己的公钥发给B,B构建自己的公私钥
        // 一般都是发送二进制或者base64等数据
        Map<String, Key> bKeyMap = initBSysKey(aPubKey);
        byte[] bPubKey = bKeyMap.get(MAP_KEY_PUBLIC).getEncoded();
        byte[] bPriKey = bKeyMap.get(MAP_KEY_PRIVATGE).getEncoded();
        logKeyMap("B", bPubKey, bPriKey);

        // A B系统产生自己的本地对称加密算法密钥
        byte[] aSecretKey = getSecretKey(bPubKey, aPriKey);
        byte[] bSecretKey = getSecretKey(aPubKey, bPriKey);

        // 转换为字符串比较下
        String aSecKeyStr = toBase64(aSecretKey);
        String bSecKeyStr = toBase64(bSecretKey);
        log("A SecretKey : %s", aSecKeyStr);
        log("B SecretKey : %s", bSecKeyStr);
        log("A B SecretKey equeals : %s", aSecKeyStr.equals(bSecKeyStr));

        log("%s", aSecretKey.length);

        // A加密数据,B解密数据,能正常加解密
        String input = "A要发送给B的数据";
        byte[] data = encrypt(aSecretKey, input.getBytes());
        byte[] rs = decrypt(bSecretKey, data);
        log("A Send : %s , B Recive : %s ", toBase64(data), new String(rs));
    }

    /***
     * A系统产生密钥,这个过程不需要参数,由DH算法计算得出<br/>
     * 内部使用一些安全的随机函数随机计算出一个公私钥<br>
     * 计算后的公私钥要存储下来,存储二进制数据
     *
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initASysKey() throws Exception {
        // 使用DH算法生成公司密钥
        KeyPairGenerator keyPairGr = KeyPairGenerator.getInstance(KEY_ALGORITH);
        keyPairGr.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGr.generateKeyPair();

        // 可以使用DB算法专业密钥前行转换获取的PublicKey和PrivateKey
        // DHPublicKey dhPK = (DHPublicKey) publicKey;
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        // 存储,传输密钥
        Map<String, Key> keyMap = new HashMap<String, Key>(2);
        keyMap.put(MAP_KEY_PUBLIC, publicKey);
        keyMap.put(MAP_KEY_PRIVATGE, privateKey);
        return keyMap;
    }

    /**
     * B构建自己的公私钥,要使用A的公钥构建<br>
     * DH算法接受公钥,并构建自己的密钥对<br>
     * 这个过程中用到了一些密钥格式转换对象,不是重点<br>
     * B也要保持自己的密钥对,二进制形式
     *
     * @param pubKey
     * @return
     * @throws Exception
     */
    public static Map<String, Key> initBSysKey(byte[] pubKey) throws Exception {

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITH);
        // 密钥格式转换对象
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        // 转换PublicKey格式
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        // 构建DH算法参数
        DHParameterSpec dhParamSpec = ((DHPublicKey) publicKey).getParams();
        // 使用DH算法创建密钥对
        KeyPairGenerator keyPairGr = KeyPairGenerator.getInstance(keyFactory.getAlgorithm());
        keyPairGr.initialize(dhParamSpec);
        KeyPair keyPair = keyPairGr.generateKeyPair();
        // 创建的公私钥
        DHPublicKey publicKey1 = (DHPublicKey) keyPair.getPublic();
        DHPrivateKey privateKey = (DHPrivateKey) keyPair.getPrivate();
        // 存储传输密钥
        Map<String, Key> keyMap = new HashMap<String, Key>(2);
        keyMap.put(MAP_KEY_PUBLIC, publicKey1);
        keyMap.put(MAP_KEY_PRIVATGE, privateKey);
        return keyMap;
    }

    /**
     * 使用对方的公钥和自己的私钥构建对称加密的SecretKey<br>
     *
     * @param publicKey
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static byte[] getSecretKey(byte[] publicKey, byte[] privateKey) throws Exception {
        // 建立密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITH);
        // 密钥编码转换对象
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKey);
        // 转换公钥格式
        PublicKey pubKey = keyFactory.generatePublic(x509KeySpec);
        // 密钥编码转换对象
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKey);
        // 转换私钥格式
        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        // 利用公钥和私钥创建本地密钥
        KeyAgreement keyAgree = KeyAgreement.getInstance(keyFactory.getAlgorithm());
        keyAgree.init(priKey);
        keyAgree.doPhase(pubKey, true);
        // 创建了一个本地密钥
        SecretKey secretKey = keyAgree.generateSecret(SECRET_ALGORITH);
        return secretKey.getEncoded();
    }

    /**
     * 使用本地密钥加密数据
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] encrypt(byte[] key, byte[] data) throws Exception {
        // 构建本地密钥
        SecretKey secretKey = new SecretKeySpec(key, SECRET_ALGORITH);
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    /**
     * 使用本地密钥解密数据
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] key, byte[] data) throws Exception {
        // 构建本地密钥
        SecretKey secretKey = new SecretKeySpec(key, SECRET_ALGORITH);
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    public static void log(String tmp, Object... params) {
        System.out.println(String.format(tmp, params));
    }

    public static void logKeyMap(String sysName, byte[] pubKey, byte[] priKey) {
        log("%s Public Key : %s", sysName, toBase64(pubKey));
        log("%s Private Key : %s", sysName, toBase64(priKey));
    }

    private static String toBase64(byte[] data) {
        return new String(Base64.getEncoder().encode(data));
    }

}

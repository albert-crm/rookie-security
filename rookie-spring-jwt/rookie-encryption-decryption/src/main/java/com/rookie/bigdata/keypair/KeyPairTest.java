package com.rookie.bigdata.keypair;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.*;

/**
 * @Class KeyPairTest
 * @Description https://zhuanlan.zhihu.com/p/355237988
 * @Author rookie
 * @Date 2023/12/25 16:51
 * @Version 1.0
 */
public class KeyPairTest {

    private static final String ALGOGRITHM = "RSA";
    private static final String PUBLIC_KEY_PATH = "./public.key";
    private static final String PRIVATE_KEY_PATH = "./private.key";


    public static void main(String[] args) throws Exception {
        testGenerate();
        testEncryptAndDecrypt();
    }


    public static void testGenerate() throws Exception {
        //KeyPairGenerator引擎类用于产生密钥对，JDK(7)默认支持的算法有，DiffieHellman、DSA、RSA、EC
        KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGOGRITHM);
        //产生密钥对
        KeyPair keyPair = generator.generateKeyPair();
        //获取公钥
        PublicKey publicKey = keyPair.getPublic();
        //获取私钥
        PrivateKey privateKey = keyPair.getPrivate();

        //将公钥与私钥写入文件，以备后用
        writeKey(PUBLIC_KEY_PATH, publicKey);
        writeKey(PRIVATE_KEY_PATH, privateKey);
    }


    public static void testEncryptAndDecrypt() throws Exception {
        Cipher cipher = Cipher.getInstance(ALGOGRITHM);
        //读取私钥，进行加密
        PrivateKey privateKey = (PrivateKey) readKey(PRIVATE_KEY_PATH);
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        //加密
        String sendInfo = "《Java精讲》公众号";
        byte[] results = cipher.doFinal(sendInfo.getBytes());

        //读取公钥，进行解密
        PublicKey publicKey = (PublicKey) readKey(PUBLIC_KEY_PATH);
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        //解密
        byte[] deciphered = cipher.doFinal(results);
        //得到明文
        String recvInfo = new String(deciphered);
        System.out.println(recvInfo);
    }

    public static void writeKey(String path, Key key) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(key);
        oos.close();
    }

    public static Key readKey(String path) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream bis = new ObjectInputStream(fis);
        Object object = bis.readObject();
        bis.close();
        return (Key) object;
    }

}

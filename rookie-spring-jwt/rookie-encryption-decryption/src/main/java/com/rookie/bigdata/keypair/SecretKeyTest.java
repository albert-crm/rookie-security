package com.rookie.bigdata.keypair;

/**
 * @Class SecretKeyTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/25 17:01
 * @Version 1.0
 */

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class SecretKeyTest {
    //对称加密算法，Java默认支持的算法有：AES、ARCFOUR、Blowfish、DES、DESede、HmacMD5、
    //HmacSHA1 HmacSHA256 HmacSHA384 HmacSHA512、RC2
    private static final String ALGORITHM = "DES";


    public static void main(String[] args) throws Exception {

        testEncrypt();
        testDecrypt();
    }

    public static void testEncrypt() throws Exception {
        //获取加/解密器
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //生成秘密密钥
        SecretKey key = KeyGenerator.getInstance(ALGORITHM).generateKey();
        //将秘密写入文件中
        writeSecretKey("./xtayfjpk.key", key, false);
        //加密时，必须初始化为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);

        String myInfo = "《Java精讲》公众号";
        //加密
        byte[] results = cipher.doFinal(myInfo.getBytes());
        writeFile("./content.dat", results);
    }


    public static void testDecrypt() throws Exception {
        //读取秘密密钥
        SecretKey key = readSecretKey("./xtayfjpk.key", false);
        //读取加密后的数据
        byte[] contents = readFile("./content.dat");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        //解密时，必须初始化为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //解密，得到明文
        byte[] origins = cipher.doFinal(contents);
        System.out.println(new String(origins));
    }

    /**
     * 读取文件内容至字节数组中
     **/
    public static byte[] readFile(String path) throws Exception {
        FileInputStream cntInput = new FileInputStream(path);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int b = -1;
        while ((b = cntInput.read()) != -1) {
            baos.write(b);
        }
        cntInput.close();
        byte[] contents = baos.toByteArray();
        baos.close();
        return contents;
    }

    /**
     * 将二进制数据写入文件
     **/
    public static void writeFile(String path, byte[] content) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        fos.write(content);
        fos.close();
    }

    public static SecretKey readSecretKey(String path, boolean encoded) throws Exception {
        if (encoded) {
            byte[] keyData = readFile(path);
            KeySpec keySpec = new DESKeySpec(keyData);
            //当读取秘密密钥编码数据时须使用此方式生成秘密密钥
            //Java文档中虽然明确说明SecretKeyFactory.getInstance支持AES算法，但事实上却不支持(JDK7)
            SecretKey key = SecretKeyFactory.getInstance(ALGORITHM).generateSecret(keySpec);
            return key;
        } else {
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream bis = new ObjectInputStream(fis);
            Object object = bis.readObject();
            bis.close();
            return (SecretKey) object;
        }
    }

    /**
     * 秘密密钥写入文件有两种方式，一是利用Java对象的序列化机制，二是获取其编码数据(字节数据)写入文件
     **/
    public static void writeSecretKey(String path, SecretKey secretKey, boolean encoded) throws Exception {
        FileOutputStream fos = new FileOutputStream(path);
        if (encoded) {
            fos.write(secretKey.getEncoded());
            fos.close();
        } else {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(secretKey);
            oos.close();
        }
    }
}

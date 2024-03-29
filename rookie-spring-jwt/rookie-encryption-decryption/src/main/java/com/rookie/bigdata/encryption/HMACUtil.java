package com.rookie.bigdata.encryption;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Class HMACUtil
 * @Description https://www.cnblogs.com/at0x7c00/p/7519600.html
 * @Author rookie
 * @Date 2023/12/25 14:22
 * @Version 1.0
 */
public class HMACUtil {

    //HMAC
    public static void main(String[] args) throws Exception {

        String data = "Hello";
        String key = getKey();
        System.out.println("key:" + key);

        String mac = encryptHmac(key.getBytes(), data.getBytes());
        System.out.println(mac);

        String key1 = getKey();
        System.out.println("key:" + key1);
        System.out.println(encryptHmac(key1.getBytes(), data.getBytes()));

    }

    public static String getKey() throws Exception {
        KeyGenerator generator = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = generator.generateKey();
        byte[] bytes = key.getEncoded();
        return Base64Util.encode(bytes);
    }

    public static String encryptHmac(byte[] key, byte[] data) throws Exception {
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);

        byte[] resultBytes = mac.doFinal(data);
        String resultString = Base64Util.encode(resultBytes);
        return resultString;
    }
}

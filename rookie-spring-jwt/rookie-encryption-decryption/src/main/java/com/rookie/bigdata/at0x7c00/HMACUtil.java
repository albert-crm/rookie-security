package com.rookie.bigdata.at0x7c00;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Class HMACUtil
 * @Description https://www.cnblogs.com/at0x7c00/category/1099884.html
 * <p>
 * https://www.cnblogs.com/at0x7c00/p/7519600.html
 * @Author rookie
 * @Date 2024/2/21 9:27
 * @Version 1.0
 */
public class HMACUtil {

    public static void main(String[] args) throws Exception {

        String data = "Hello";
        String key = getKey();
        System.out.println("key:" + key);

        String mac = encryptHmac(key.getBytes(), data.getBytes());
        System.out.println(mac);
//key:thObyFXgWF4HCs1LYku2IQxq/QROAA1IqB9OXk3dYI7FFdeit+4ahvneTl9U8su4zVrgKJ936bxD
//fYOVotUYpw==
//JUiA2Dy6cOmNIcPuguej+g==
//LT+GkEH76CTG6E2OxloFgw==
//        key:LvSyYyXk3s30BLCVTkDk28t3GH03gWAjB12U7xgT84tHmioGCePFts/XmOf+cRd/qdpXM+wft3B/
//44cJjNxbHA==
//1SvG/XJCdNIq44BFxLQXtg==
//LxYB5j2iJWvppNkt/oOeyg==


        System.out.println(encryptHmac(key.getBytes(), "Hello2".getBytes()));

    }

    public static String getKey()throws Exception{
        KeyGenerator generator = KeyGenerator.getInstance("HmacMD5");
        SecretKey key = generator.generateKey();
        byte[] bytes = key.getEncoded();
        return Base64Util.encode(bytes);
    }

    public static String encryptHmac(byte[] key,byte[] data)throws Exception{
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(secretKey);

        byte[] resultBytes = mac.doFinal(data);
        String resultString = Base64Util.encode(resultBytes);
        return resultString;
    }
}

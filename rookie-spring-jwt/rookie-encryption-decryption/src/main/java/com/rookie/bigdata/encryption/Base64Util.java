package com.rookie.bigdata.encryption;

import cn.hutool.core.codec.Base62;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @Class Base64Util
 * @Description https://www.cnblogs.com/at0x7c00/p/7519600.html
 * https://blog.csdn.net/m0_46577050/article/details/121669697
 * @Author rookie
 * @Date 2023/12/25 14:09
 * @Version 1.0
 */
public class Base64Util {


    //将数据转换为不便于识别的数据算是一种最简单的加密了，比如Base64编码：
    public static void main(String[] args) throws Exception {


        String str = "Hello";
//        SGVsbG8=
        byte[] bytes = str.getBytes();
        String encodedStr = encode(bytes);
        System.out.println(encodedStr);

        byte[] decodedBytes = decode(encodedStr);
        System.out.println(new String(decodedBytes));


        String str1 = "HelloHelloHello";
//        SGVsbG8=
        byte[] bytes1 = str1.getBytes();
        String encodedStr1 = encode(bytes1);
        System.out.println(encodedStr1);
        System.out.println(encodedStr1.length());

    }

    public static String encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    public static byte[] decode(String encodeStr) throws IOException {
        return new BASE64Decoder().decodeBuffer(encodeStr);
    }

}

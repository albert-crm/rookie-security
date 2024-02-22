package com.rookie.bigdata.at0x7c00;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @Class Base64Util
 * @Description https://www.cnblogs.com/at0x7c00/category/1099884.html
 * <p>
 * https://www.cnblogs.com/at0x7c00/p/7519600.html
 * @Author rookie
 * @Date 2024/2/21 9:18
 * @Version 1.0
 */
public class Base64Util {

    /**
     * 这种编码是可逆的，因此加密的数据越长，则得到的结果越长，因为数据中存储了所有原始数据的细节。另外一些，比如MD5算法，是不可逆的，则属于内容摘要，
     * 多长的数据拿过来，最终得到的摘要结果长度都是一样的。因为这个特性所以经常用于校验文件是否被修改过
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {


        String str = "Hello";
        byte[] bytes = str.getBytes();
        String encodedStr = encode(bytes);
        System.out.println(encodedStr);

        byte[] decodedBytes = decode(encodedStr);
        System.out.println(new String(decodedBytes));

    }

    public static String encode(byte[] bytes) {
        return new BASE64Encoder().encode(bytes);
    }

    public static byte[] decode(String encodeStr) throws IOException {
        return new BASE64Decoder().decodeBuffer(encodeStr);
    }

}

package com.rookie.bigdata.chenwewi;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;

/**
 * @Class Base64CoderBouncyCastle
 * @Description bouncycastle示例 https://blog.csdn.net/chenwewi520feng/article/details/131302314
 * @Author rookie
 * @Date 2023/12/26 16:35
 * @Version 1.0
 */
public class Base64CoderBouncyCastle {


    public final static String ENCODING = "UTF-8";

    /**
     * Base64编码
     *
     * @param data 待编码数据
     * @return String 编码数据
     * @throws Exception
     */
    public static String encode(String data) throws Exception {
        // 执行编码
        byte[] b = Base64.encode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    /**
     * Base64解码
     *
     * @param data 待解码数据
     * @return String 解码数据
     * @throws Exception
     */
    public static String decode(String data) throws Exception {
        // 执行解码
        byte[] b = Base64.decode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    /**
     * @param data
     * @return
     * @throws Exception
     */
    public static String urlEncode(String data) throws Exception {
        // 执行编码
        byte[] b = UrlBase64.encode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

    /**
     * @param data
     * @return
     * @throws Exception
     */
    public static String urlDecode(String data) throws Exception {
        // 执行编码
        byte[] b = UrlBase64.encode(data.getBytes(ENCODING));
        return new String(b, ENCODING);
    }

}


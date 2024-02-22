package com.rookie.bigdata.chenwewi520feng.md5;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * @Class MDCoderBouncyCastle
 * @Description
 * @Author rookie
 * @Date 2023/12/26 16:58
 * @Version 1.0
 */
public class MDCoderCommons {


    public static byte[] encodeMD5(String data) throws Exception {
        // 执行消息摘要
        return DigestUtils.md5(data);
    }

    public static String encodeMD5Hex(String data) throws Exception {
        // 执行消息摘要
        return DigestUtils.md5Hex(data);
    }
}


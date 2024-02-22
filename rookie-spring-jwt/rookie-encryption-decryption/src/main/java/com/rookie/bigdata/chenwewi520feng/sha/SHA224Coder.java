package com.rookie.bigdata.chenwewi520feng.sha;


import java.security.MessageDigest;
import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

/**
 * @Class SHA224Coder
 * @Description
 * @Author rookie
 * @Date 2024/2/22 16:59
 * @Version 1.0
 */
public class SHA224Coder {
    public static byte[] encodeSHA224(byte[] data) throws Exception {
        // 加入BouncyCastleProvider支持
        Security.addProvider(new BouncyCastleProvider());

        // 初始化MessageDigest
        MessageDigest md = MessageDigest.getInstance("SHA-224");

        // 执行消息摘要
        return md.digest(data);
    }

    public static String encodeSHA224Hex(byte[] data) throws Exception {
        // 执行消息摘要
        byte[] b = encodeSHA224(data);

        // 做十六进制编码处理
        return new String(Hex.encode(b));

    }
}

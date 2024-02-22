package com.rookie.bigdata.chenwewi520feng.sha;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class SHACoderTest
 * @Description
 * @Author rookie
 * @Date 2024/2/22 16:51
 * @Version 1.0
 */
class SHACoderTest {


    @Test
    public void test001()throws Exception{
        String input = "要加密的字符串"; // 将此处替换成需要加密的内容

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] digest = md.digest(input.getBytes());

        BigInteger no = new BigInteger(1, digest);
        String hashText = no.toString(16);

        while (hashText.length() < 32) {
            hashText = "0" + hashText;
        }

        System.out.println(hashText);
    }

    @Test
    public final void testEncodeSHA() throws Exception {
        String str = "SHA1消息摘要";

        // 获得摘要信息
        byte[] data1 = SHACoder.encodeSHA(str.getBytes());
        byte[] data2 = SHACoder.encodeSHA(str.getBytes());
        System.out.println(new String(data1, Charset.forName("UTF-8")));

        new String(data1, Charset.forName("UTF-8"));

        // 校验
//        assertEquals(data1, data2);
        assertArrayEquals(data1,data2);
    }

    @Test
    public final void testEncodeSHA256() throws Exception {
        String str = "SHA256消息摘要";

        // 获得摘要信息
        byte[] data1 = SHACoder.encodeSHA256(str.getBytes());
        byte[] data2 = SHACoder.encodeSHA256(str.getBytes());

        // 校验
//        assertEquals(data1, data2);
        assertArrayEquals(data1,data2);
    }

    @Test
    public final void testEncodeSHA384() throws Exception {
        String str = "SHA384消息摘要";

        // 获得摘要信息
        byte[] data1 = SHACoder.encodeSHA384(str.getBytes());
        byte[] data2 = SHACoder.encodeSHA384(str.getBytes());

        // 校验
//        assertEquals(data1, data2);
        assertArrayEquals(data1,data2);
    }

    @Test
    public final void testEncodeSHA512() throws Exception {
        String str = "SHA512消息摘要";

        // 获得摘要信息
        byte[] data1 = SHACoder.encodeSHA512(str.getBytes());
        byte[] data2 = SHACoder.encodeSHA512(str.getBytes());

        // 校验
        //        assertEquals(data1, data2);
        assertArrayEquals(data1,data2);
    }
}


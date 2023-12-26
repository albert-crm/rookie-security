package com.rookie.bigdata.chenwewi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class SHACoderTest
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/26 17:02
 * @Version 1.0
 */
class SHACoderTest {

    @Test
    void encodeSHA() {
    }

    @Test
    void encodeSHA256() {
    }

    @Test
    void encodeSHA384() {
    }

    @Test
    void encodeSHA512() {
    }

    @Test
    void main() {
    }

    @Test
    public final void testEncodeSHA() throws Exception {
        String str = "SHA1消息摘要";

        // 获得摘要信息
        byte[] data1 = SHACoder.encodeSHA(str.getBytes());
        byte[] data2 = SHACoder.encodeSHA(str.getBytes());

        // 校验
        assertArrayEquals(data1,data2);
//        assertEquals(data1, data2);
    }

    @Test
    public final void testEncodeSHA256() throws Exception {
        String str = "SHA256消息摘要";

        // 获得摘要信息
        byte[] data1 = SHACoder.encodeSHA256(str.getBytes());
        byte[] data2 = SHACoder.encodeSHA256(str.getBytes());

        // 校验
        assertArrayEquals(data1,data2);
//        assertEquals(data1, data2);
    }

    @Test
    public final void testEncodeSHA384() throws Exception {
        String str = "SHA384消息摘要";

        // 获得摘要信息
        byte[] data1 = SHACoder.encodeSHA384(str.getBytes());
        byte[] data2 = SHACoder.encodeSHA384(str.getBytes());

        // 校验
        assertArrayEquals(data1,data2);
//        assertEquals(data1, data2);
    }

    @Test
    public final void testEncodeSHA512() throws Exception {
        String str = "SHA512消息摘要";

        // 获得摘要信息
        byte[] data1 = SHACoder.encodeSHA512(str.getBytes());
        byte[] data2 = SHACoder.encodeSHA512(str.getBytes());

        // 校验
        assertArrayEquals(data1,data2);
//        assertEquals(data1, data2);
    }
}

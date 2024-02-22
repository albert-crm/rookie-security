package com.rookie.bigdata.zhihu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class JwtUtilsTest
 * @Description
 * @Author rookie
 * @Date 2023/12/26 17:15
 * @Version 1.0
 */
class JwtUtilsTest {


    @Test
    void test01() {
        Long expireTime = 1000L * 3600 * 3;
        long expire = expireTime == null ? 7 * 24 * 3600 * 1000L : expireTime;

        String token = JwtUtils.create("abc", "127.0.0.1", "abc", "ddd", expire);

        // 验证token
        if (JwtUtils.verify(token, "abc")) {
            // 得到payload中的数据
            List<String> data = JwtUtils.getAudienceList(token);
            for (String datum : data) {
                System.out.println(datum);
            }
        }

    }


    @Test
    void create() {
    }

    @Test
    void testCreate() {
    }

    @Test
    void testCreate1() {
    }

    @Test
    void getAudienceList() {
    }

    @Test
    void verify() {
    }
}

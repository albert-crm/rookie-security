package com.rookie.bigdata.dh;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Class DHTestTest
 * @Description
 * @Author rookie
 * @Date 2023/12/26 11:48
 * @Version 1.0
 */
class DHTestTest {

    @Test
    void test01(){
        Server server = new Server();

        Client client = new Client(server);
        boolean loginSuccess = client.login("admin", "123456");

        System.out.println("login success:" + loginSuccess);
    }

}

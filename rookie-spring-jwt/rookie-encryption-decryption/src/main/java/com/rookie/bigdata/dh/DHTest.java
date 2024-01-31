package com.rookie.bigdata.dh;

/**
 * @Class DHTest
 * @Description https://blog.csdn.net/xiaoxiao_su123/article/details/133742678
 * @Author rookie
 * @Date 2023/12/26 11:30
 * @Version 1.0
 */
public class DHTest {

    public static void main(String[] args) {
        Server server = new Server();

        Client client = new Client(server);
        boolean loginSuccess = client.login("admin", "123456");

        System.out.println("login success:" + loginSuccess);
    }
}

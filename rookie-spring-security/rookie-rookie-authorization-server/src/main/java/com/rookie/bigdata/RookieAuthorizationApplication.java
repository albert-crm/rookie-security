package com.rookie.bigdata;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * rookie-security.v.1.0.0
 *
 * @Classname RookieSecurityApplication
 * @Description
 * @Author rookie
 * @Date 2021/8/6 16:20
 * @Version 1.0
 */

//@MapperScan(basePackages = "com.rookie.bigdata.mapper")
@SpringBootApplication
public class RookieAuthorizationApplication {

    public static void main(String[] args) {


        SpringApplication.run(RookieAuthorizationApplication.class);
    }
}

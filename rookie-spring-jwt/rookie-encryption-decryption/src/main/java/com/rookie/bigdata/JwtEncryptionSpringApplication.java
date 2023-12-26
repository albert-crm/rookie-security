package com.rookie.bigdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Class JwtSpringApplication
 * @Description https://www.cnblogs.com/at0x7c00/p/7688124.html
 * https://blog.csdn.net/qq_36964872/article/details/99710186
 * @Author rookie
 * @Date 2023/12/25 10:11
 * @Version 1.0
 */
@SpringBootApplication
public class JwtEncryptionSpringApplication {
    public static void main(String[] args) {

        SpringApplication.run(JwtEncryptionSpringApplication.class);
    }
}


//    1)DES(Data Encryption Standard,数据加密标准)加密之后，密文的长度的8的整数倍，秘钥长度是8字节(password)，即64bit位
//
//            2)AES(Advanced Encryption Standard,高级加密标准)加密和解密，秘钥长度是16字节，即128bit位
//
//            3)对称加密密钥长度分析：
//
//            4）对称加密的特点：加密计算量小、速度块，适合对大量数据进行加密的场景。（记住这个特点，实际使用是会用到的）

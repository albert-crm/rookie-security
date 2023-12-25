package com.rookie.bigdata;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @Class JwtTest
 * @Description https://www.cnblogs.com/wrc-blog/p/14256071.html
 * @Author rookie
 * @Date 2023/12/25 10:08
 * @Version 1.0
 */
@SpringBootTest
//@ActiveProfiles("uat")
public class JwtTest {


    @Test
    void test01(){



        //获取系统的当前时间，以便设置过期时间
        long currentTimeMillis = System.currentTimeMillis()+1000000;
        Date date = new Date(currentTimeMillis);

        //生成jwt令牌
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("66")//设置jwt编码  这是载荷
                .setSubject("黑马程序员")//设置jwt主题   这是载荷
                .setIssuedAt(new Date())//设置jwt签发日期   这是载荷
                .setExpiration(date)//设置jwt的过期时间 目前是创建就会过期
                .claim("roles","admin") //自定义的信息 这是载荷  多条自定义信息就多写几个claim
                .claim("company","itheima")//自定义的信息   这是载荷
                .signWith(SignatureAlgorithm.HS256, "itheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaaddddddds");
        //生成jwt令牌
        String jwtToken = jwtBuilder.compact();
        System.out.println(jwtToken);

        //解析jwt令牌
        Claims claims = Jwts.parser().setSigningKey("itheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaadddddddsitheimaaaaaaaaaaaddddddds")//签名必须和生成时的一样 签名是signwith方法参数
                .parseClaimsJws(jwtToken).getBody();
        System.out.println(claims);
    }

}

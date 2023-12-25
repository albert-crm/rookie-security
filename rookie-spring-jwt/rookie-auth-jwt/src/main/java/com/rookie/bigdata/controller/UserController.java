package com.rookie.bigdata.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.rookie.bigdata.entity.User;
import com.rookie.bigdata.service.UserSerivce;
import com.rookie.bigdata.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Class UserController
 * @Description https://blog.csdn.net/m0_54355172/article/details/128070287
 * @Author rookie
 * @Date 2023/12/25 15:45
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserSerivce userSerivce;


    //localhost:8081/admin/login
    //

    @RequestMapping("/login")
    public Map<String, Object> login(User user) {
        Map<String, Object> map = new HashMap<>();
        try {

            //登录成功
//            User userMsg = userSerivce.login(user);
            User userMsg=new User();
            userMsg.setId(1);
            userMsg.setName("admin");
            userMsg.setStatus("1");

            //登录成功之后生成令牌
            // payload
            Map<String, String> payload = new HashMap<>();
            payload.put("id", userMsg.getId() + "");
            payload.put("name", userMsg.getName());
            payload.put("status", userMsg.getStatus() + "");
            // 生成令牌
            String token = JWTUtils.getToken(payload);


            map.put("state", true);
            map.put("smg", "登陆成功");
            map.put("token", token);

        } catch (RuntimeException e) {
            map.put("state", false);
            map.put("smg", "登陆失败");
            e.printStackTrace();
        }
        return map;
    }


    @RequestMapping("/test2")
    public Map<String, Object> test2(String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }


    @RequestMapping("/test")
    public Map<String, Object> test(String token) {
        Map<String, Object> map = new HashMap<>();
//        log.info("token：" + token);
        try {
            JWTUtils.verify(token);
            map.put("state", true);
            map.put("smg", "登陆成功");
        } catch (SignatureVerificationException e) {
            map.put("state", false);
            map.put("smg", "签名错误");
        } catch (TokenExpiredException e) {
            map.put("state", false);
            map.put("smg", "token过期");
        } catch (AlgorithmMismatchException e) {
            map.put("state", false);
            map.put("smg", "算法不一致");
        } catch (Exception e) {
            map.put("state", false);
            map.put("smg", "无效签名");
        }
        return map;
    }

}

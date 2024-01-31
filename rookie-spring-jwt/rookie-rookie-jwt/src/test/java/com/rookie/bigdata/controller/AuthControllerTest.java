//package com.rookie.bigdata.controller;
//
//import com.alibaba.fastjson.JSON;
//import com.rookie.bigdata.domain.User;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
///**
// * @Classname AuthControllerTest
// * @Description 进行用户注册
// * @Author rookie
// * @Date 2023/3/6 14:42
// * @Version 1.0
// */
//@SpringBootTest
//@AutoConfigureMockMvc
////@ActiveProfiles("uat")
//class AuthControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//
//    @Test
//    void registerUser() throws Exception{
//
//        User user=new User();
//        user.setName("user1");
//        user.setPassword("user");
//        String toJSONString = JSON.toJSONString(user);
//
////        System.out.println(new Gson().toJson(couponQueryIn));
//        MvcResult result = mockMvc.perform(post("/auth/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                //设置请求体参数
//                .content(toJSONString))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        System.out.println(result.getResponse().getContentAsString());
//
//    }
//}
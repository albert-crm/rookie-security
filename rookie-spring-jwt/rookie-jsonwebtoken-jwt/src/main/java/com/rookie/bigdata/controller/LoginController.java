//package com.rookie.bigdata.controller;
//
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// * @Class LoginController
// * @Description
// * @Author rookie
// * @Date 2023/12/25 10:21
// * @Version 1.0
// */
//public class LoginController {
//
//
//
//    //用户登录
//    @PostMapping("/login")
//    public Result login(@RequestBody Admin admin){
//        boolean result = adminService.login(admin);
//        if(result){
//            //密码是正确的
//            //生成jwt令牌，返回到客户端
//            Map<String,String> info = new HashMap<>();
//            info.put("username",admin.getLoginName());
//            //基于工具类生成jwt令牌
//            String jwt = JwtUtil.createJWT(UUID.randomUUID().toString(), admin.getLoginName(), null);
//            info.put("token",jwt);
//
//            return new Result(true,StatusCode.OK,"登录成功",info);
//        } else
//        {
//            return new Result(false,StatusCode.ERROR,"登录失败",result);
//        }
//    }
//
//}

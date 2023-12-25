package com.rookie.bigdata.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rookie.bigdata.util.JWTUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Class JWTInterceptor
 * @Description TODO
 * @Author rookie
 * @Date 2023/12/25 15:49
 * @Version 1.0
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头数据
        String token = request.getHeader("token");

        Map<String, Object> map = new HashMap<>();
        // 验证令牌
        try {
            JWTUtils.verify(token);
            return true; // 验证通过
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

        map.put("state", false);
        // 将map转换为json
        String json = new ObjectMapper().writeValueAsString(map);
//        response.setContentType("application/json;character=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}

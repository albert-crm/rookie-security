package com.rookie.bigdata.security.web;

import com.rookie.bigdata.util.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname CustomAuthenticationEntryPoint
 * @Description
 * @Author rookie
 * @Date 2023/3/14 16:12
 * @Version 1.0
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        ResponseUtils.unauthorizedResponse(response, authException.getMessage());
    }

}

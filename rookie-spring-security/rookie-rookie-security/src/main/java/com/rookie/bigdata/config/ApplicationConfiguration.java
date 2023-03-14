package com.rookie.bigdata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Classname ApplicationConfiguration
 * @Description TODO
 * @Author rookie
 * @Date 2023/3/14 15:47
 * @Version 1.0
 */
@Component
public final class ApplicationConfiguration {


    public static String CONTEXT_PATH;

    private ApplicationConfiguration() {
    }

    @Value("${server.servlet.context-path}")
    public void setContextPath(String contextPath) {
        CONTEXT_PATH = contextPath;
    }
}

package com.rookie.bigdata.util;

import com.rookie.bigdata.config.ApplicationConfiguration;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname RequestUtils
 * @Description TODO
 * @Author rookie
 * @Date 2023/3/14 15:47
 * @Version 1.0
 */
public final class RequestUtils {

    private RequestUtils() {
    }

    /**
     * Description: 获得有效的 requestURI
     */
    public static String getQualifiedURI(HttpServletRequest request) {
        return StringUtils.replace(request.getRequestURI(), ApplicationConfiguration.CONTEXT_PATH, "");
    }
}

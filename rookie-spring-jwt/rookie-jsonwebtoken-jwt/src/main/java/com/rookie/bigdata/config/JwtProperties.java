package com.rookie.bigdata.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Class JwtProperties
 * @Description https://blog.csdn.net/qq_45193304/article/details/116465032
 * @Author rookie
 * @Date 2023/12/26 14:06
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "zero.auth.security.jwt")
public class JwtProperties {

    /**
     * Token过期时间，单位为毫秒，默认为1个小时
     */
    private Long ttl;

    /**
     * token的签发者
     */
    private String issuer;

    /**
     * token名称；accessToken
     */
    private String key;

}

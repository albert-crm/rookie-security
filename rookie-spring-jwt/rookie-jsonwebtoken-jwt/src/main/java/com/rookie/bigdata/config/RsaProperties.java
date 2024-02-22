package com.rookie.bigdata.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Class RsaProperties
 * @Description
 * @Author rookie
 * @Date 2023/12/26 14:06
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "zero.auth.security.jwt.rsa")
public class RsaProperties {

    /**
     * 加密秘钥信息
     */
    private String secret;
    /**
     * 私钥存放路径
     */
    private String privateFile;
    /**
     * 公钥存放路径
     */
    private String publicFile;

}

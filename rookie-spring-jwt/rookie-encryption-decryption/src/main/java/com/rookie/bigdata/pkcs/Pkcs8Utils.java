package com.rookie.bigdata.pkcs;

import org.apache.commons.lang3.RegExUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;


/**
 * @Class Pkcs8Utils
 * @Description https://blog.csdn.net/m0_37934074/article/details/133775108
 * https://blog.csdn.net/a7442358/article/details/127888512
 * https://blog.csdn.net/qq_31289187/article/details/84973338
 * 公钥与私钥 生成与数字签名
 * @Author rookie
 * @Date 2023/12/26 9:56
 * @Version 1.0
 */
public class Pkcs8Utils {

    private static final String PROBLEM_MSG = "证书文件【%s】有问题，请核实！";
    private static final String NOT_FOUND_MSG = "证书文件【%s】不存在，请核实！";


    public static PrivateKey loadPrivateKey(String configPath) {
        try {

            // InputStream inputStream=null;
            final String prefix = "classpath:";
            String path = RegExUtils.removeFirst(configPath, prefix);
            if (!path.startsWith("/")) {
                path = "/" + path;
            }

            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream = classPathResource.getInputStream();
//            InputStream inputStream = ResourcesUtil.getResourceAsStream(path);

            ByteArrayOutputStream array = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                array.write(buffer, 0, length);
            }

            String privateKey = array.toString("utf-8")
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(
                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
//            throw new WanDaRuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
//            throw new WanDaRuntimeException("无效的密钥格式");
        } catch (IOException e) {
//            throw new WanDaRuntimeException("无效的密钥");
        }
        return null;
    }

}

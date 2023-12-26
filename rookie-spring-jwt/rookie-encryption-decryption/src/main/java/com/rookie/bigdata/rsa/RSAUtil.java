package com.rookie.bigdata.rsa;

import com.rookie.bigdata.encryption.Base64Util;
import org.apache.commons.lang3.RegExUtils;
import org.springframework.core.io.ClassPathResource;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @Class RSAUtil
 * @Description https://blog.csdn.net/weixin_40147979/article/details/127847342
 * @Author rookie
 * @Date 2023/12/26 10:50
 * @Version 1.0
 */
public class RSAUtil {


    /**
     * 加载公钥
     *
     * @param configPath
     * @return
     */
    public static PublicKey loadPublicKey(String configPath) {

        try {

            final String prefix = "classpath:";
            String path = RegExUtils.removeFirst(configPath, prefix);
            if (!path.startsWith("/")) {
                path = "/" + path;
            }

            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream = classPathResource.getInputStream();

            ByteArrayOutputStream array = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                array.write(buffer, 0, length);
            }

            String publicKey = array.toString("utf-8")
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            X509EncodedKeySpec encodeRule = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
            PublicKey key = keyFactory.generatePublic(encodeRule);
            return key;
//            return kf.generatePrivate(
//                    new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        } catch (NoSuchAlgorithmException e) {
//            throw new WanDaRuntimeException("当前Java环境不支持RSA", e);
        } catch (InvalidKeySpecException e) {
//            throw new WanDaRuntimeException("无效的密钥格式");
        } catch (IOException e) {
//            throw new WanDaRuntimeException("无效的密钥");
        }
        return null;


    }

    /**
     * 加载私钥
     *
     * @param configPath
     * @return
     */
    public static PrivateKey loadPrivateKey(String configPath) {
        try {

            final String prefix = "classpath:";
            String path = RegExUtils.removeFirst(configPath, prefix);
            if (!path.startsWith("/")) {
                path = "/" + path;
            }

            ClassPathResource classPathResource = new ClassPathResource(path);
            InputStream inputStream = classPathResource.getInputStream();

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


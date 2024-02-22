package com.rookie.bigdata.at0x7c00.dh;

/**
 * @Class Server
 * @Description https://www.cnblogs.com/at0x7c00/p/7688124.html
 *
 * 假设客户端要发送数据到服务端，在Java中DH加密的完整步骤：
 * 1、服务端先生成自己的密钥对：
 * 2、服务端的私钥自己保持，公布公钥，客户端则需要根据服务端的公钥生成自己的密钥对：
 *  3、客户端在和服务端通信时，使用的加密算法是对称加密。对称加密的密钥是根据服务端的公钥和客户端的私钥生成的。
 *
 *
 * @Author rookie
 * @Date 2023/12/26 11:23
 * @Version 1.0
 */

import javax.crypto.SecretKey;

/**
 * 数据处理服务端
 * @author huqiao
 */
public class Server {

    private String publicKey;
    private String privateKey;
    private SecretKey key;

    public Server(){
        try {
            //产生密钥对，公钥和私钥
            //服务端的私钥自己保持，公布公钥，客户端则需要根据服务端的公钥生成自己的密钥对：
            String[] keyPair = DHUtil.getStringKeyPair();
            publicKey = keyPair[0];
            privateKey = keyPair[1];
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String service(String data,String clientPublicKey){
        System.out.println("----------------Data received at Server:----------------\r\n"+ data);
        System.out.println("----------------Client PublicKey received at Server:----------------\r\n"+clientPublicKey);
        try {
            key = DHUtil.getAgreementSecretKey(clientPublicKey, privateKey);
            String decryptedData = DHUtil.decrypt(data, key);
            System.out.println("Data decryped:" + decryptedData);
            if(verfiy(decryptedData)){
                return "OK";
            }else{
                return "Error";

            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    private boolean verfiy(String decryptedData) {
        //解析用户名和密码，进行验证
        return true;
    }

    /**
     * 明文拿到服务端公钥
     * @return
     */
    public String getPublicKey(){
        return publicKey;
    }
}

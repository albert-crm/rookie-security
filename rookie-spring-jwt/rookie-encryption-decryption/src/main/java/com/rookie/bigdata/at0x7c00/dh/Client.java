package com.rookie.bigdata.at0x7c00.dh;

/**
 * @Class Client
 * @Description
 * @Author rookie
 * @Date 2023/12/26 11:26
 * @Version 1.0
 */

import javax.crypto.SecretKey;
/**
 * 数据传输客户端
 * @author huqiao
 */
public class Client {

    private String publicKey;
    private String privateKey;
    private SecretKey key;

    private Server server;

    public Client(Server server){
        //服务端的私钥自己保持，公布公钥，客户端则需要根据服务端的公钥生成自己的密钥对：
        this.server = server;
        String serverPublicKey = server.getPublicKey();
        try{
            String[] keyPair = DHUtil.getStringKeyPair(serverPublicKey);
            publicKey = keyPair[0];
            privateKey = keyPair[1];
            key = DHUtil.getAgreementSecretKey(serverPublicKey, privateKey);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //客户端在和服务端通信时，使用的加密算法是对称加密。对称加密的密钥是根据服务端的公钥和客户端的私钥生成的。
    public boolean login(String user,String pwd){
        String data = "user=" + user + "&pwd=" + pwd;
        try {
            data = DHUtil.encrypt(data, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String response = server.service(data,publicKey);
        System.out.println("Login Response:" + response);
        return response.equals("OK");
    }
}

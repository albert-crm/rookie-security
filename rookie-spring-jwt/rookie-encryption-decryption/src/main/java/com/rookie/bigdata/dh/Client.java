package com.rookie.bigdata.dh;

/**
 * @Class Client
 * @Description TODO
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

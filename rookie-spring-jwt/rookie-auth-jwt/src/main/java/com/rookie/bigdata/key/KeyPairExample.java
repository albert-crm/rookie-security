//package com.rookie.bigdata.key;
//
//import java.security.*;
//import java.security.spec.X509EncodedKeySpec;
//
///**
// * @Class KeyPairExample
// * @Description https://blog.51cto.com/u_16175431/6665429
// * @Author rookie
// * @Date 2023/12/25 13:46
// * @Version 1.0
// */
//public class KeyPairExample {
//
//
//    public static void main(String[] args) throws Exception {
//        //生成RSA算法的秘钥对
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        //设置秘钥长度
//        keyPairGenerator.initialize(2048);
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//        //获取公钥和私钥
//        PublicKey publicKey = keyPair.getPublic();
//
//
//
//        // 公钥字节数组
//        byte[] publicKeyBytes = publicKey.getEncoded();
//
//        // 创建X509EncodedKeySpec对象
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//
//        // 获取RSA算法的KeyFactory对象
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//
//        // 通过KeyFactory对象获取私钥
//        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
//
//        // 打印私钥信息
//        System.out.println("私钥: " + privateKey);
//
//
//    }
//
//
//    public static void main1(String[] args) throws Exception {
//        //生成RSA算法的秘钥对
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        //设置秘钥长度
//        keyPairGenerator.initialize(2048);
//        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//
//        //获取公钥和私钥
//        PublicKey publicKey = keyPair.getPublic();
////        PrivateKey privateKey = keyPair.getPrivate();
//
//        //进行打印
//        System.out.println("publicKey: " + publicKey);
////        System.out.println("privateKey: " + privateKey);
//
//
//        //通过公钥获取私钥
//        //公钥字节数
//        byte[] publicKeyBytes = publicKey.getEncoded();
//        //创建X509EncodedKeySpec对象
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
//        //获取RSA算法的KeyFactory对象
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        //通过KeyFactory对象获取私钥
//        PrivateKey privateKey1 = keyFactory.generatePrivate(keySpec);
//
//        System.out.println("私钥：" + privateKey1);
//    }
//}

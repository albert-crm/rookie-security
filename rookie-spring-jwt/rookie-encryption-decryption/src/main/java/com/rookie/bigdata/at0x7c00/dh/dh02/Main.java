package com.rookie.bigdata.at0x7c00.dh.dh02;

/**
 * @Class Main
 * @Description https://blog.csdn.net/m0_64529944/article/details/131858579
 * @Author rookie
 * @Date 2024/2/22 11:36
 * @Version 1.0
 */
public class Main {
    public static void main(String[] args) {
        // Bob和Alice:
        Person bob = new Person("Bob");
        Person alice = new Person("Alice");

        // 各自生成KeyPair: 公钥+私钥
        bob.generateKeyPair();
        alice.generateKeyPair();

        // 双方交换各自的PublicKey(公钥):
        // Bob根据Alice的PublicKey生成自己的本地密钥(共享公钥):
        bob.generateSecretKey(alice.publicKey.getEncoded());

        // Alice根据Bob的PublicKey生成自己的本地密钥(共享公钥):
        alice.generateSecretKey(bob.publicKey.getEncoded());

        // 检查双方的本地密钥是否相同:
        bob.printKeys();
        alice.printKeys();

        // 双方的SecretKey相同，后续通信将使用SecretKey作为密钥进行AES加解密...
    }
}

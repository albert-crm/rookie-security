package com.rookie.bigdata.chenwewi520feng;

/**
 * @Classname PhoneUtils
 * @Description 用于按照一定的规则  对手机号进行加解密
 * CREATE   function job_dim_encryption( IN  encryption varchar(100),  KEY_A  varchar(100) DEFAULT 'jiami' )
 * returns result_end varchar(100)
 * language SQLSCRIPT as
 * begin
 *
 *
 * declare LENG1 varchar(100);
 * declare LENG2 varchar(100);
 * declare i int;
 * declare key1 varchar(100);
 * declare num_0 varchar(100);
 * declare num_1 varchar(100);
 * declare num_2 varchar(100);
 * declare num_3 varchar(100);
 * key1:='9527573942';
 * if  :KEY_A<>:key1 THEN
 *       LENG1:=LENGTH(:encryption);
 *    num_3:= '';
 *    i:=1;
 *       WHILE :i <= :LENG1  DO
 *         num_0:=substr(:key1,MOD(:i,10)+1,1);
 *      num_1:=substr(:encryption,:i,1);
 *      num_2:=ascii(:num_1)+:num_0;
 *      num_3:=:num_3||char(:num_2);
 *      i:=:i+1;
 *      end WHILE;
 *    result_end:=:num_3;
 * elseif :KEY_A=:key1  THEN
 *       LENG1:=LENGTH(:encryption);
 *    num_3:= '';
 *    i:=1;
 *       WHILE :i <= :LENG1  DO
 *         num_0:=substr(:key1,MOD(:i,10)+1,1);
 *      num_1:=substr(:encryption,:i,1);
 *      num_2:=ascii(:num_1)-:num_0;
 *      num_3:=:num_3||char(:num_2);
 *      i:=:i+1;
 *      end WHILE;
 *    result_end:=:num_3;
 *
 * END IF ;
 * end
 * @Author rookie
 * @Date 2022/7/7 10:57
 * @Version 1.0
 */
public class PhoneUtils {

    //key的长度为10
    public static String key1 = "9527573942";

    public static String decryptPhone(String phone) {
        //获取手机号长度
        int length = phone.length();
        String num_3 = "";
        int i = 1;
        //进行循环
        while (i <= length) {
            char num_0 = key1.charAt(i % 10);//[i%10];
            char num_1 = phone.charAt(i - 1);
            int num_2 = ((int) num_1) - (num_0 - '0');
//            System.out.println((char) num_2);
            String value = String.valueOf((char) num_2);
            num_3 += value;


            i++;
        }
        //16622326164
        //16622326164
        return num_3;
    }


    public static String encryptPhone(String encryption) {
        //手机号的长度
        int length = encryption.length();
        String num_3 = "";
        int i = 1;
        //对手机号进行循环进行循环
        while (i <= length) {
            //数字转换为ASCII码
            char num_0 = key1.charAt(i % 10);//[i%10];
            char num_1 = encryption.charAt(i - 1);
            int num_2 = ((int) num_1) + (num_0 - '0');
//            System.out.println((char) num_2);
            String value = String.valueOf((char) num_2);
            num_3 += value;


            i++;
        }
        //16622326164
        //16622326164
        return num_3;


    }


    public static void main(String[] args) {

//        String phone1 = decryptPhone("13003699312");

        String phone = decryptPhone("68=796;:3?9");
        System.out.println(phone);
        String encryptPhone = encryptPhone("16622326164");
        System.out.println(encryptPhone);
    }
}

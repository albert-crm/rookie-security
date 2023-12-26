package com.rookie.bigdata.encryption;

/**
 * @Class CaesarUtil
 * @Description 凯撒算法 https://blog.csdn.net/qq_39757593/article/details/125463468
 * @Author rookie
 * @Date 2023/12/25 17:21
 * @Version 1.0
 */
public class CaesarUtil {


    public static void main(String[] args) {
//        2.1、基于ASCII码实现凯撒密码
        String abc = asciiCaesarEncrypt("ABC", 125);
        System.out.println(abc);

        String dabc = asciiCaesarDecrypt(abc, 125);
        System.out.println(dabc);

        //2.2、基于字母表实现的凯撒密码
        String abc1 = lowerCaseCaesarEncrypt("zabc", 25);
        System.out.println(abc1);

        String abc2 = lowerCaseCaesarDecrypt(abc1, 25);
        System.out.println(abc2);

    }


    /**
     * 解密算法
     * @param str
     * @param offset
     * @return
     */
    public static String lowerCaseCaesarDecrypt(String str,int offset){
        char[] chars=str.toCharArray();
        char[] lowerCase=new char[26];
        for (int i = 0; i < 26; i++) {
            lowerCase[i]= (char) ('a'+i);//如果需要大写字母表这里可以填'A'
        }
        String lowerCaseStr=String.valueOf(lowerCase);
        for (int i = 0; i < chars.length; i++) {
            int i1 = lowerCaseStr.indexOf(chars[i]) - offset;
            if(i1>25){
                chars[i]=lowerCase[(i1%=26)];
            }else if (i1<0){
                int abs = Math.abs(i1)%26;
                chars[i]=lowerCase[(26-abs)%26];
            }else{
                chars[i] -= offset;
            }
        }
        return String.valueOf(chars);
    }



    /**
     * 加密算法
     * @param str
     * @param offset
     * @return
     */
    public static String lowerCaseCaesarEncrypt(String str,int offset){
        char[] chars=str.toCharArray();
        char[] lowerCase=new char[26];
        for (int i = 0; i < 26; i++) {
            lowerCase[i]= (char) ('a'+i);//如果需要大写字母表这里可以填'A'
        }
        String lowerCaseStr=String.valueOf(lowerCase);
        for (int i = 0; i < chars.length; i++) {
            int i1 = lowerCaseStr.indexOf(chars[i]) + offset;
            if(i1>25){
                chars[i]=lowerCase[(i1%=26)];
            }else if (i1<0){
                int abs = Math.abs(i1)%26;
                chars[i]=lowerCase[(26-abs)];
            }else{
                chars[i] += offset;
            }
        }
        return String.valueOf(chars);
    }










    /**
     * 加密算法
     *
     * @param str
     * @param offset
     * @return
     */
    public static String asciiCaesarEncrypt(String str, int offset) {
        char[] chars = str.toCharArray();
        //循环位移算法
        for (int i = 0; i < chars.length; i++) {
            int i1 = chars[i] + offset;
            if (i1 > 127) {
                chars[i] = (char) (i1 %= 128);
            } else if (i1 < 0) {
                int abs = Math.abs(i1) % 128;
                chars[i] = (char) (128 - abs);
            } else {
                chars[i] += offset;
            }
        }
        return String.valueOf(chars);
    }


    /**
     * 解密算法
     *
     * @param str
     * @param offset
     * @return
     */
    public static String asciiCaesarDecrypt(String str, int offset) {
        char[] chars = str.toCharArray();
        //循环位移算法
        for (int i = 0; i < chars.length; i++) {
            int i1 = chars[i] - offset;
            if (i1 > 127) {
                chars[i] = (char) (i1 %= 128);
            } else if (i1 < 0) {
                int abs = Math.abs(i1) % 128;
                chars[i] = (char) (128 - abs);
            } else {
                chars[i] -= offset;
            }
        }
        return String.valueOf(chars);
    }


}

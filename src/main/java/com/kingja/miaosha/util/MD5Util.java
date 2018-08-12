package com.kingja.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Description:TODO
 * Create Time:2018/8/11 11:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MD5Util {

    private static final String salt = "1a2b3c4d";


    public static String md5(String value) {
        return DigestUtils.md5Hex(value);
    }



//    public static String saltMd5(String value) {
//        char[] chars = (salt + md5(value)).toCharArray();
//        Arrays.sort(chars);
//        String saltMd5 = md5(String.valueOf(chars).toUpperCase());
//        return saltMd5;
//    }
//
//    public static String dbMd5(String value,String salt) {
//        char[] chars = (salt + md5(value)).toCharArray();
//        Arrays.sort(chars);
//        String saltMd5 = md5(String.valueOf(chars).toUpperCase());
//        return saltMd5;
//    }

    public static String saltMd5(String value) {
        return saltMd5( value, salt);
    }

    public static String saltMd5(String value,String salt) {
        String newValue =""+ salt.charAt(0) + salt.charAt(2) + value + salt.charAt(5) + salt.charAt(4);
        String dbMd5 = md5(newValue);
        return dbMd5;
    }
    public static void main(String[] args) {
        System.out.println("saltMd5:" + saltMd5("123456"));
        System.out.println("dbMd5:" + saltMd5(saltMd5("123456"),"1a2b3c4d"));
//        saltMd5:d3b1294a61a07da9b49b6e22b2cbd7f9
//        dbMd5:b7797cce01b4b131b433b6acf4add449
    }
}

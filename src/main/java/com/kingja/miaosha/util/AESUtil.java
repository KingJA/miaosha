package com.kingja.miaosha.util;

import org.apache.commons.codec.binary.Base64;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Description:TODO
 * Create Time:2018/8/13 20:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AESUtil {
    /**
     * 待加密字符串
     */
    private static String src = "imooc security aes";
    private static String password = "kingja";

    public static void main(String[] args) {
        jdkAES();
        String encrypt = encrypt(src);
        System.out.println("加密后："+ encrypt);
        System.out.println("解密后："+ decode(encrypt));
    }

    /**
     * 通过JDK实现AES对称加密
     */
    public static void jdkAES() {
        try {
            Key key = getKey();


            // 加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk aes encrypt:" + Base64.encodeBase64String(result));

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("jdk aes decrypt:" + new String(result));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String content) {
        // 加密
        byte[] result = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, getKey());
            result = cipher.doFinal(content.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return Base64.encodeBase64String(result);
    }

    public static String decode(String encryptContent) {
        // 加密
        byte[] result = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, getKey());
            result = cipher.doFinal(Base64.decodeBase64(encryptContent));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    private static Key getKey() throws NoSuchAlgorithmException {
        // 生成KEY
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128);//不添加秘钥
        //添加秘钥
        keyGenerator.init(128,new SecureRandom(password.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] keyBytes = secretKey.getEncoded();

        // KEY 转换
        return new SecretKeySpec(keyBytes, "AES");
    }
}

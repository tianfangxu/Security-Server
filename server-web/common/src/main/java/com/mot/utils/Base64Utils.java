package com.mot.utils;

import java.util.Base64;

public class Base64Utils {

    private static final String UTF_8 = "utf-8";

    public static String encrypt(String src){
        try {
            return src != null?Base64.getEncoder().encodeToString(src.getBytes(UTF_8)):null;
        } catch (Exception e) {
            throw new RuntimeException("加密错误！！！");
        }
    }

    public static String decrypt(String encode){
        try {
            return encode != null?new String(Base64.getDecoder().decode(encode),UTF_8):null;
        }catch (Exception e){
            throw new RuntimeException("解密错误！！！");
        }
    }

}

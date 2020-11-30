package com.mot.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;

public class DESede {
    public static final String DESede = "DESede";

    public static String decode(String secretKey, String cipherText) {
        byte[] kb = secretKey.getBytes();
        SecretKeySpec k = new SecretKeySpec(kb, DESede);
        byte[] srcByte = null;
        try {
            Cipher cp = Cipher.getInstance(DESede);
            cp.init(Cipher.DECRYPT_MODE, k);
            srcByte = cp.doFinal(cipherText.getBytes());
        }catch (Exception e){
            throw new RuntimeException("解密出错！！！");
        }
        return new String(srcByte);
    }
    public static String encode(String secretKey, String srcText) {
        byte[] kb = secretKey.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(kb, DESede);
        byte[] cipherByte = null;
        try {
            Cipher cipher = Cipher.getInstance(DESede);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            cipherByte = cipher.doFinal(srcText.getBytes());
        }catch (Exception e){
            throw new RuntimeException("加密错误！！！");
        }
        return new String(cipherByte);
    }
}

package com.mot.utils;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;

public class MD5Util {

    private static final String UTF_8 = "utf-8";

    /**
     *  盐，用于混交md5
     */
    private static final String slat = "DGYUDNHDCHINA8488ENCRYPT";

    public static String getMD5(String str) {
        String base = str +"/"+slat;
        byte[] secretBytes = null;
        try {
            secretBytes = MessageDigest.getInstance("md5").digest(base.getBytes(UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);
        for (int i = 0; i < 32 - md5code.length(); i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }

    public static String getFileMD5(InputStream fis) {
        byte[] b = createChecksum(fis);
        if(null == b){
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            result.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
    private static byte[] createChecksum(InputStream fis) {
        try {
            byte[] buffer = new byte[1024];
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead = -1;

            while ((numRead = fis.read(buffer)) != -1) {
                complete.update(buffer, 0, numRead);
            }
            return complete.digest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

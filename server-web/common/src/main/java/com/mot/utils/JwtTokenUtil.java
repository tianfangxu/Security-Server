package com.mot.utils;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class JwtTokenUtil {

    private static final String secret = "DGYUDNHDCHINA8488ENCRYPT";
    //有效时间 3天
    private static final Long expiration = 259200000L;



    /**
     * 生成token令牌
     *
     * @param username 用户
     * @return 令token牌
     */
    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("sub", username);
        claims.put("created", System.currentTimeMillis()+expiration);
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public static String getUsernameFromToken(String token) {
        Map claims = getClaimsFromToken(token);
        return claims == null?null:String.valueOf(claims.get("sub"));
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public static String refreshToken(String token) {
        String refreshedToken= null;
        Map claims = getClaimsFromToken(token);
        if (claims != null){
            claims.put("created", System.currentTimeMillis()+expiration);
            refreshedToken = generateToken(claims);
        }
        return refreshedToken;
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param username     用户
     * @return 是否有效
     */
    public static Boolean validateToken(String token, String username){
        String src = getUsernameFromToken(token);
        return (src!=null && src.equals(username));
    }


    /**
     * 从claims生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private static String generateToken(Map<String, Object> claims){
        String str = claims.toString();
        String signin = MD5Util.getMD5(str);
        claims.put("signin",signin);
        return  Base64Utils.encrypt(claims.toString());
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private static Map getClaimsFromToken(String token) {
        String decode = DESede.decode(secret, token);
        Map crs = JSON.parseObject(decode, Map.class);
        String signin = String.valueOf(crs.get("signin"));
        crs.remove("signin");
        if (!MD5Util.getMD5(crs.toString()).equals(signin)) {
            return null;
        }
        long created = (long)crs.get("created");
        if (created < System.currentTimeMillis()){
            return null;
        }
        return crs;
    }

}

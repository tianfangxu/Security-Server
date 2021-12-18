package com.mot.common.excel.utils;

import java.util.regex.Pattern;

/**
 * @author tianfx
 * @date 2021/12/16 11:52 上午
 */
public class TableUtil {

    public static int[] getPlaneSize(String start,String end){
        if (isBlank(start) && isBlank(end)){
            return new int[]{0,0};
        }
        if (isBlank(start) || isBlank(end)){
            return new int[]{0,0};
        }
        start = start.toUpperCase();
        end = end.toUpperCase();
        String startXStr = start.replaceAll("\\d*","");
        String startYStr = start.replaceAll("[A-Z]*","");
        String endXStr = end.replaceAll("\\d*","");
        String endYStr = end.replaceAll("[A-Z]*","");
        int startX = parseInt(startXStr);
        int startY = parseInt(startYStr);
        int endX = parseInt(endXStr);
        int endY = parseInt(endYStr);
        return new int[]{endY>startY?endY-startY+1:startY-endY+1,endX>startX?endX-startX+1:startX-endX+1};
    }
    
    public static boolean isBlank(Object v){
        return v == null || "".equals(v);
    }

    public static int parseInt(String v){
        int defaultVal = 1;
        return parseInt(v,defaultVal);
    }

    static Pattern compile = Pattern.compile("[A-Z]+");
    public static int parseInt(String v,int defaultVal){
         if (isBlank(v)){
             return defaultVal;
         }
        if (compile.matcher(v).matches()) {
            char[] chars = v.toCharArray();
            int redix = 1;
            int res = 0;
            int i  = chars.length-1;
            while (i > -1){
                res += (chars[i]-64)*redix;
                redix *= 26;i--;
            }
            return res;
        }else {
            try {
                return Integer.parseInt(v);
            }catch (Exception e){
                System.err.println("VALUE CAST INT ERROR:"+v);
                e.printStackTrace();
                return defaultVal;
            }
        }
    }
    
    public static double parseDouble(String v,double defaultVal){
        if (isBlank(v)){
            return defaultVal;
        }
        try {
            return Double.parseDouble(v);
        }catch (Exception e){
            System.err.println("VALUE CAST INT ERROR:"+v);
            e.printStackTrace();
            return defaultVal;
        }
    }
}

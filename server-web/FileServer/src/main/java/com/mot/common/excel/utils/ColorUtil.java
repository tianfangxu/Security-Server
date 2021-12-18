package com.mot.common.excel.utils;

/**
 * @author tianfx
 * @date 2021/12/16 11:39 上午
 */
public class ColorUtil {
    
    public static String hexadecimalToRgb(String color){
        if (color == null ){
            return null;
        }
        StringBuilder res = new StringBuilder();
        if (color.length() == 6){
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4, 6), 16);
            res.append("rgb(").append(r).append(",").append(g).append(",").append(b).append(")");
        }else if (color.length() == 8){
            int a = Integer.parseInt(color.substring(0, 2), 16);
            int r = Integer.parseInt(color.substring(2, 4), 16);
            int g = Integer.parseInt(color.substring(4, 6), 16);
            int b = Integer.parseInt(color.substring(6, 8), 16);
            res.append("rgba(").append(r).append(",").append(g).append(",").append(b).append(",").append(a).append(")");
        }else {
            return null;
        }
        return res.toString();
    }

    public static String hexadecimalToRgbWithTint(String color,double tint){
        if (color == null ){
            return null;
        }
        StringBuilder res = new StringBuilder();
        if (color.length() == 6){
            int r = Integer.parseInt(color.substring(0, 2), 16);
            int g = Integer.parseInt(color.substring(2, 4), 16);
            int b = Integer.parseInt(color.substring(4, 6), 16);
            int rr = (int) ((1 - tint) * r + (tint) * 255);
            int gg = (int) ((1 - tint) * g + (tint) * 255);
            int bb = (int) ((1 - tint) * b + (tint) * 255);
            res.append("rgb(").append(rr).append(",").append(gg).append(",").append(bb).append(")");
        }else if (color.length() == 8){
            int a = Integer.parseInt(color.substring(0, 2), 16);
            int r = Integer.parseInt(color.substring(2, 4), 16);
            int g = Integer.parseInt(color.substring(4, 6), 16);
            int b = Integer.parseInt(color.substring(6, 8), 16);
            int rr = (int) ((1 - tint) * r + (tint) * 255);
            int gg = (int) ((1 - tint) * g + (tint) * 255);
            int bb = (int) ((1 - tint) * b + (tint) * 255);
            res.append("rgba(").append(rr).append(",").append(gg).append(",").append(bb).append(",").append(a).append(")");
        }else {
            return null;
        }
        return res.toString();
    }
}

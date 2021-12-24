package com.mot.common.excel.enums;

import com.mot.common.excel.utils.ColorUtil;

/**
 * 主题颜色
 * @author tianfx
 * @date 2021/12/16 11:36 上午
 */
public enum Theme {

    /**
     * 主题
     */
    white(0,"FFFFFF"),
    black(1,"000000"),
    E7E6E6(2,"E7E6E6"),
    A4546A(3,"44546A"),
    BULE(4,"5B9BD5"),
    ED7D31(5,"ED7D31"),
    A5A5A5(6,"A5A5A5"),
    FFC000(7,"FFC000"),
    A72C4C1(8,"4472C4"),
    A0AD47(9,"70AD47"),;

    public static String getColor(int code){
        for (Theme value : values()) {
            if (value.code == code){
                return ColorUtil.hexadecimalToRgb(value.color);
            }
        }
        return null;
    }
    
    public static String getColor(int code,double tint){
        for (Theme value : values()) {
            if (value.code == code){
                if (code == 0 || code == 2){
                    
                }
                String s = ColorUtil.hexadecimalToRgbWithTint(value.color, tint);
                return s;
            }
        }
        return null;
    }
    private int code;
    private String color;

    Theme(int code, String color) {
        this.code = code;
        this.color = color;
    }

    public static void main(String[] args) {
        System.out.println(getColor(0, -0.499984740745262d));
    }
}

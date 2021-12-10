package com.mot.common.utils.excelUtils;

/**
 * @author tianfx
 * @date 2021/12/9 2:39 下午
 */
public enum Theme {
    white(0,"FFFFFF"),
    black(1,"000000"),
    E7E6E6(2,"E7E6E6"),
    A4546A(3,"44546A"),
    BULE(4,"5B9BD5"),
    ED7D31(5,"ED7D31"),
    A5A5A5(6,"A5A5A5"),
    FFC000(7,"FFC000"),
    A72C4C1(8,"4472C4"),
    A0AD47(9,"70AD47"),
    ;
    
    public static String getColor(int code){
        for (Theme value : values()) {
            if (value.code == code){
                return value.color;
            }
        }
        return "";
    }
    
    private int code;
    private String color;

    Theme(int code, String color) {
        this.code = code;
        this.color = color;
    }
}

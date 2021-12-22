package com.mot.common.excel.enums;

import com.mot.common.excel.utils.ColorUtil;

/**
 * @author tianfx
 * @date 2021/12/21 1:09 下午
 */
public enum ColorIndex {
        _00000000_1("000000"),
        _00FFFFFF_1("FFFFFF"),
        _00FF0000_1("FF0000"),
        _0000FF00_1("00FF00"),
        _000000FF_1("0000FF"),
        _00FFFF00_1("FFFF00"),
        _00FF00FF_1("FF00FF"),
        _0000FFFF_1("00FFFF"),
        _00000000_2("000000"),
        _00FFFFFF_2("FFFFFF"),
        _00FF0000_2("FF0000"),
        _0000FF00_2("00FF00"),
        _000000FF_2("0000FF"),
        _00FFFF00_2("FFFF00"),
        _00FF00FF_2("FF00FF"),
        _0000FFFF_2("00FFFF"),
        _00800000_1("800000"),
        _00008000_1("008000"),
        _00000080_1("000080"),
        _00808000_1("808000"),
        _00800080_1("800080"),
        _00008080_1("008080"),
        _00C0C0C0_1("C0C0C0"),
        _00808080_1("808080"),
        _009999FF_1("9999FF"),
        _00993366_1("993366"),
        _00FFFFCC_1("FFFFCC"),
        _00CCFFFF_1("CCFFFF"),
        _00660066_1("660066"),
        _00FF8080_1("FF8080"),
        _000066CC_1("0066CC"),
        _00CCCCFF_1("CCCCFF"),
        _00000080_2("000080"),
        _00FF00FF_3("FF00FF"),
        _00FFFF00_3("FFFF00"),
        _0000FFFF_3("00FFFF"),
        _00800080_2("800080"),
        _00800000_2("800000"),
        _00008080_2("008080"),
        _000000FF_3("0000FF"),
        _0000CCFF_2("00CCFF"),
        _00CCFFFF_2("CCFFFF"),
        _00CCFFCC_1("CCFFCC"),
        _00FFFF99_1("FFFF99"),
        _0099CCFF_1("99CCFF"),
        _00FF99CC_1("FF99CC"),
        _00CC99FF_1("CC99FF"),
        _00FFCC99_1("FFCC99"),
        _003366FF_1("3366FF"),
        _0033CCCC_1("33CCCC"),
        _0099CC00_1("99CC00"),
        _00FFCC00_1("FFCC00"),
        _00FF9900_1("FF9900"),
        _00FF6600_1("FF6600"),
        _00666699_1("666699"),
        _00969696_1("969696"),
        _00003366_1("003366"),
        _00339966_1("339966"),
        _00003300_1("003300"),
        _00333300_1("333300"),
        _00993300_1("993300"),
        _00993366_2("993366"),
        _00333399_1("333399"),
        _00333333_1("333333");
    private String color;

    ColorIndex(String color) {
        this.color = color;
    }

    public static String getColor(int index,double tint){ 
        if (index >= 0 && values().length > index){
            return ColorUtil.hexadecimalToRgbWithTint(values()[index].color,tint);
        }
        return null;
    }
}





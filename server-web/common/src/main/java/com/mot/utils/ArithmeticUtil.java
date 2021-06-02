package com.mot.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArithmeticUtil {
    /**
     * 加
     * @param arrs
     * @return
     */
    public static String add(String... arrs){
        BigDecimal init = new BigDecimal(0);
        for (String val : arrs) {
            init = init.add(new BigDecimal(val));
        }
        return init.toString();
    }

    /**
     * 减
     * @param a
     * @param b
     * @return
     */
    public static String subtract(String a,String b){
        return new BigDecimal(a).subtract(new BigDecimal(b)).toString();
    }

    /**
     * 乘
     * @param a
     * @param b
     * @return
     */
    public static String multiply(String a,String b){
        return new BigDecimal(a).multiply(new BigDecimal(b)).toString();
    }

    /**
     * 除
     * 四舍五入，保留5位小数
     * @param a
     * @param b
     * @return
     */
    public static String divide(String a,String b){
        return divide(a,b,5);
    }
    public static String divide(String a,String b,int scale){
        return new BigDecimal(a).divide(new BigDecimal(b),scale, RoundingMode.HALF_UP).toString();
    }

}

package com.mot.common.excel.entity;

import com.mot.common.excel.enums.ColorIndex;
import com.mot.common.excel.enums.Theme;
import com.mot.common.excel.utils.ColorUtil;
import com.mot.common.excel.utils.TableUtil;
import org.xml.sax.Attributes;

import java.awt.*;

/**
 * @author tianfx
 * @date 2021/12/16 11:32 上午
 */
public class Style {
    public Double size;
    public String color;
    //字体
    public String typeface;
    //居中
    public String align;
    //背景
    public String bgColor;
    //上下标？
    public String vertAlign;
    //下划线
    public Boolean u;
    //双下划线
    public Boolean du;
    //加粗
    public Boolean b;
    //斜体
    public Boolean i;
    //删除线
    public Boolean strike;
    
    public void copy(Style style){
        this.size = style.size!=null?style.size:this.size;
        this.color = style.color!=null?style.color:this.color;
        this.typeface = style.typeface!=null?style.typeface:this.typeface;
        this.align = style.align!=null?style.align:this.align;
        this.bgColor = style.bgColor!=null?style.bgColor:this.bgColor;
        this.vertAlign = style.vertAlign!=null?style.vertAlign:this.vertAlign;
        this.u = style.u!=null?style.u:this.u;
        this.du = style.du!=null?style.du:this.du;
        this.strike = style.strike!=null?style.strike:this.strike;
        this.b = style.b!=null?style.b:this.b;
        this.i = style.i!=null?style.i:this.i;
    }
    
    
    public static void setStyleCommon(String qName, Attributes attributes,Style current){
        if (qName.endsWith("b")){
            current.b = true;
        }
        if (qName.endsWith("i")){
            current.i = true;
        }
        if (qName.endsWith("u")){
            String val = attributes.getValue("val");
            if (val != null && val.equals("double")){
                current.du = true;
            }else {
                current.u = true;
            }
        }
        if (qName.endsWith("strike")){
            current.strike = true;
        }
        if (qName.endsWith("vertAlign")){
            String val = attributes.getValue("val");
            if (val != null && val.equals("subscript")){
                current.vertAlign = "sub";
            }else if (val != null && val.equals("superscript")){
                current.vertAlign = "sup";
            }
        }
        if (qName.endsWith("sz")){
            String val = attributes.getValue("val");
            if (!TableUtil.isBlank(val)){
                current.size = TableUtil.parseDouble(val,12);
            }
        }
        if (qName.endsWith("color")) {
            String theme = attributes.getValue("theme");
            String indexed = attributes.getValue("indexed");
            String tint = attributes.getValue("tint");
            String argb = attributes.getValue("rgb");
            String s;
            if (theme != null) {
                s = Theme.getColor(TableUtil.parseInt(theme, Integer.MAX_VALUE),TableUtil.parseDouble(tint,0d));
            }else{
                s = ColorIndex.getColor(TableUtil.parseInt(indexed, Integer.MAX_VALUE),TableUtil.parseDouble(tint,0d));
            }
            String color = s == null?ColorUtil.hexadecimalToRgb(argb):s;
            current.color = color;
        }
        if (qName.endsWith("name") || qName.endsWith("rFont")) {
            current.typeface= attributes.getValue("val");
        }
    }
    
    public static String getHtmlValue(Style style,Object value,String lable){
        StringBuilder builder = new StringBuilder("<").append(lable);
        builder.append(putStyle(style))
                .append(" >")
                .append(putValue(style,value));
        return builder.append("</").append(lable).append(">").toString();
    }
    
    public static String putValue(Style style,Object value){
        String s = "%s";
        if (style != null){
            if (style.getB()!=null && style.getB()) {
                s = "<b>" +s+ "</b>";
            }
            if (style.getI()!=null&&style.getI()){
                s = "<i>" +s+ "</i>";
            }
            if (style.getU()!=null&&style.getU()){
                s = "<u>" +s+ "</u>";
            }
            if (style.getStrike()!=null&&style.getStrike()){
                s = "<s>" +s+ "</s>";
            }
            if (!TableUtil.isBlank(style.getVertAlign())){
                s = "<"+style.vertAlign+">" +s+ "</"+style.vertAlign+">";
            }
        }
        return String.format(s, TableUtil.isBlank(value) ? "&nbsp;" : value);
    }
    
    public static String putStyle(Style style){
        if (style != null){
            StringBuilder stringBuilder = new StringBuilder(" style=\"min-width: 68px;max-width: 600px");
            if (style.getSize() != null && style.getSize() > 0){
                stringBuilder.append(";font-size: "+style.getSize()+"px");
            }
            if (!TableUtil.isBlank(style.getTypeface())){
                stringBuilder.append(";font-family:"+style.getTypeface());
            }
            if (!TableUtil.isBlank(style.getColor())){
                stringBuilder.append(";color: "+style.getColor());
            }
            if (!TableUtil.isBlank(style.getAlign())){
                stringBuilder.append(";text-align: "+style.getAlign());
            }
            if (!TableUtil.isBlank(style.getBgColor())){
                stringBuilder.append(";background-color: "+style.getBgColor());
            }
            if (style.du != null && style.du){
                stringBuilder.append(";border-bottom:3pt double #000000");
            }
            return stringBuilder.append("\" ").toString();
        }
        return "";
    }

    public Double getSize() {
        return size;
    }

    public Style setSize(Double size) {
        this.size = size;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Style setColor(String color) {
        this.color = color;
        return this;
    }

    public String getTypeface() {
        return typeface;
    }

    public Style setTypeface(String typeface) {
        this.typeface = typeface;
        return this;
    }

    public String getAlign() {
        return align;
    }

    public Style setAlign(String align) {
        this.align = align;
        return this;
    }

    public String getBgColor() {
        return bgColor;
    }

    public Style setBgColor(String bgColor) {
        this.bgColor = bgColor;
        return this;
    }

    public Boolean getU() {
        return u;
    }

    public Style setU(Boolean u) {
        this.u = u;
        return this;
    }

    public Boolean getB() {
        return b;
    }

    public Style setB(Boolean b) {
        this.b = b;
        return this;
    }

    public Boolean getI() {
        return i;
    }

    public Style setI(Boolean i) {
        this.i = i;
        return this;
    }

    public String getVertAlign() {
        return vertAlign;
    }

    public Style setVertAlign(String vertAlign) {
        this.vertAlign = vertAlign;
        return this;
    }

    public Boolean getDu() {
        return du;
    }

    public Style setDu(Boolean du) {
        this.du = du;
        return this;
    }

    public Boolean getStrike() {
        return strike;
    }

    public Style setStrike(Boolean strike) {
        this.strike = strike;
        return this;
    }
}

package com.mot.common.excel.entity;

/**
 * @author tianfx
 * @date 2021/12/16 11:32 上午
 */
public class Style {
    public Double size;
    public String color;
    public String typeface;
    public String align;
    public String bgColor;
    public Boolean u;
    public Boolean b;
    public Boolean i;
    
    public void copy(Style style){
        this.size = style.size!=null?style.size:this.size;
        this.color = style.color!=null?style.color:this.color;
        this.typeface = style.typeface!=null?style.typeface:this.typeface;
        this.align = style.align!=null?style.align:this.align;
        this.bgColor = style.bgColor!=null?style.bgColor:this.bgColor;
        this.u = style.u!=null?style.u:this.u;
        this.b = style.b!=null?style.b:this.b;
        this.i = style.i!=null?style.i:this.i;
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
}

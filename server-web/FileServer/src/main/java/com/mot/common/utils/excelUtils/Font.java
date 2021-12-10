package com.mot.common.utils.excelUtils;

/**
 * @author tianfx
 * @date 2021/12/9 10:59 上午
 */
public class Font {
    public double size;
    public String color;
    public String name;

    public double getSize() {
        return size;
    }

    public Font setSize(double size) {
        this.size = size;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Font setColor(String color) {
        this.color = color;
        return this;
    }

    public String getName() {
        return name;
    }

    public Font setName(String name) {
        this.name = name;
        return this;
    }
}

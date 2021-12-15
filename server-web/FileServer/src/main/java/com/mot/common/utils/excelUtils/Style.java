package com.mot.common.utils.excelUtils;

/**
 * @author tianfx
 * @date 2021/12/15 5:08 下午
 */
public class Style {

    public String horizontal;
    Font font;

    public String getHorizontal() {
        return horizontal;
    }

    public Style setHorizontal(String horizontal) {
        this.horizontal = horizontal;
        return this;
    }

    public Font getFont() {
        return font;
    }

    public Style setFont(Font font) {
        this.font = font;
        return this;
    }
}

package com.mot.common.excel.entity;

/**
 * 每一个单元数据
 * @author tianfx
 * @date 2021/12/16 11:33 上午
 */
public class Cell {
    
    private Object value;
    private Style style;
    Integer colspan;
    Integer rowspan;
    public static final Cell EMPTY = new Cell();

    public Object getValue() {
        return value;
    }

    public Cell setValue(Object value) {
        this.value = value;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Cell setStyle(Style style) {
        this.style = style;
        return this;
    }

    public Integer getColspan() {
        return colspan;
    }

    public Cell setColspan(Integer colspan) {
        this.colspan = colspan;
        return this;
    }

    public Integer getRowspan() {
        return rowspan;
    }

    public Cell setRowspan(Integer rowspan) {
        this.rowspan = rowspan;
        return this;
    }
}

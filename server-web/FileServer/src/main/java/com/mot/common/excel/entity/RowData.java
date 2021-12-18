package com.mot.common.excel.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 每一行数据集合
 * @author tianfx
 * @date 2021/12/16 11:31 上午
 */
public class RowData {
    
    private List<Cell> cells;
    private Style style;

    public List<Cell> getCells() {
        if (cells == null){
            cells = new ArrayList<>();
        }
        return cells;
    }

    public RowData setCells(List<Cell> cells) {
        this.cells = cells;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public RowData setStyle(Style style) {
        this.style = style;
        return this;
    }
}

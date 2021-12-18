package com.mot.common.excel.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 每一个sheet的数据集合
 * @author tianfx
 * @date 2021/12/16 11:29 上午
 */
public class Sheet {
    
    private String name;
    private List<RowData> rowDatas;
    private Style style;

    public String getName() {
        return name;
    }

    public Sheet setName(String name) {
        this.name = name;
        return this;
    }

    public List<RowData> getRowDatas() {
        if (rowDatas == null){
            rowDatas = new ArrayList<>();
        }
        return rowDatas;
    }

    public Sheet setRowDatas(List<RowData> rowDatas) {
        this.rowDatas = rowDatas;
        return this;
    }

    public Style getStyle() {
        return style;
    }

    public Sheet setStyle(Style style) {
        this.style = style;
        return this;
    }
}

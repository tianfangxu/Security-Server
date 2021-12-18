package com.mot.common.excel.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 整个解析后excel数据集合
 * @author tianfx
 * @date 2021/12/16 11:28 上午
 */
public class Table {
    List<Sheet> sheets;

    public List<Sheet> getSheets() {
        return sheets;
    }

    public Table setSheets(List<Sheet> sheets) {
        this.sheets = sheets;
        return this;
    }
    
    public void add(Sheet sheet){
        if (sheets == null){
            sheets = new ArrayList<>();
        }
        sheets.add(sheet);
    }
}

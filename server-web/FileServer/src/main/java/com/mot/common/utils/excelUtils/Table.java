package com.mot.common.utils.excelUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfx
 * @date 2021/12/8 2:05 下午
 */
public class Table {
    
    List<List<C>> tables;
    static C empty = new C("");
    
    public static Table getInstance(int colIndex,int rowIndex){
        Table table = new Table();
        table.tables = new ArrayList<>();
        for (int i = 0; i < rowIndex; i++) {
            ArrayList<C> list = new ArrayList<>(colIndex);
            for (int i1 = 0; i1 < colIndex; i1++) {
                list.add(empty);
            }
            table.tables.add(list);
        }
        return table;
    }

    public Table add(int rowIndex,int colIndex,String value){
        if (tables.size() <= rowIndex){
            tables.add(new ArrayList<>());
            add( rowIndex, colIndex, value);
            return this;
        }
        List<C> cList = tables.get(rowIndex);
        if (cList.size() > colIndex) {
            cList.set(colIndex,new C(value));
            return this;
        }else{
            cList.add(null);
            add( rowIndex, colIndex, value);
            return this;
        }
    }
    
    static class C{
        String v;
        int colspan;
        int rowspan;
        Font f;

        public C(String v) {
            this.v = v;
        }

        public C(String v, int colspan, int rowspan) {
            this.v = v;
            this.colspan = colspan;
            this.rowspan = rowspan;
        }

        public String getV() {
            return v;
        }

        public C setV(String v) {
            this.v = v;
            return this;
        }

        public int getColspan() {
            return colspan;
        }

        public C setColspan(int colspan) {
            this.colspan = colspan;
            return this;
        }

        public int getRowspan() {
            return rowspan;
        }

        public C setRowspan(int rowspan) {
            this.rowspan = rowspan;
            return this;
        }

        public Font getF() {
            return f;
        }

        public C setF(Font f) {
            this.f = f;
            return this;
        }
    }
}

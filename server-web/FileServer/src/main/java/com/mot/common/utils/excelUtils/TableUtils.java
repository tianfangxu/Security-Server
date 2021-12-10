package com.mot.common.utils.excelUtils;

import java.util.List;

/**
 * @author tianfx
 * @date 2021/12/8 2:31 下午
 */
public class TableUtils {

    public static void main(String[] args) {
        System.out.println(getSize("A1","A2")[1]);
    }
    
    public static int[] getSize(String start,String end){
        int numIndex = 1;
        int ensIndex = 1;
        char[] cs1 = start.toCharArray();
        int r1 = 0;
        int c1 = 0;
        for (int i = cs1.length-1; i >=0 ; i--) {
            if (cs1[i] > 47 && cs1[i] < 58){
                r1 += (cs1[i]-48)*numIndex;
                numIndex *= 10;
            }else{
                c1 += (cs1[i]-64)*ensIndex;
                ensIndex *= 26;
            }
        }
        numIndex = 1;
        ensIndex = 1;
        char[] cs2 = end.toCharArray();
        int r2 = 0;
        int c2 = 0;
        for (int i = cs2.length-1; i >=0 ; i--) {
            if (cs2[i] > 47 && cs2[i] < 58){
                r2 += (cs2[i]-48)*(numIndex);
                numIndex *= 10;
            }else{
                c2 += (cs2[i]-64)*(ensIndex);
                ensIndex *= 26;
            }
        }
        return new int[]{c2-c1+1,r2-r1+1};
    }
    
    
    public static String tableHtml(Table table){
        StringBuilder builder = new StringBuilder("<table border=\"1\"  cellspacing=\"0\" cellpadding=\"0\" style=\"text-align: center;\" >");
        List<List<Table.C>> tables = table.tables;
        for (int i = 0; i < tables.size(); i++) {
            List<Table.C> cs = tables.get(i);
            builder.append("<tr>");
            for (int i1 = 0; i1 < cs.size(); i1++) {
                Table.C c = cs.get(i1);
                if (c == null){
                    
                }else {
                    builder.append("<td");
                    int rows = 0;
                    int cols = 0;
                    if (c.rowspan > 0){
                        builder.append(" rowspan = \""+c.rowspan+"\"");
                        rows = c.rowspan;
                    }
                    if (c.colspan > 0){
                        builder.append(" colspan = \""+c.colspan+"\"");
                        cols = c.colspan;
                    }
                    if (rows != 0 && cols == 0){
                        for (int j = 1; j < rows; j++) {
                            if (tables.size() > (i+j) && tables.get(i+j) != null &&  tables.get(i+j).size() > i1) {
                                tables.get(i + j).set(i1, null);
                            }
                        }
                    }else if (rows == 0 && cols != 0){
                        for (int j = 1; j < cols; j++) {
                            if (tables.get(i).size() > i1+j) {
                                tables.get(i).set(i1 + j, null);
                            }
                        }
                    }else{
                        for (int j = 0; j < rows; j++) {
                            for (int k = 0; k < cols; k++) {
                                if (j == 0 && k == 0){
                                    continue;
                                }
                                if (tables.size() > (i+j) && tables.get(i+j) != null &&  tables.get(i+j).size() > i1+k) {
                                    tables.get(i + j).set(i1 + k, null);
                                }
                            }
                        }
                    }
                    
                    if (c.getF() != null){
                        builder.append(" style=\"font-size: "+c.getF().getSize()+"px;font-family:"+c.getF().getName()+";color: "+c.getF().getColor()+" \" ");
                    }
                    builder.append(">");
                    builder.append(c.v);
                    builder.append("</td>");
                }
            }
            builder.append("</tr>");
        }
        builder.append("</table>");
        String s = builder.toString();
        System.out.println(s);
        return s;
    }
}

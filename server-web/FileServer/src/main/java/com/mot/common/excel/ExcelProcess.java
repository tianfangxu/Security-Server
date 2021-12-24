package com.mot.common.excel;

import com.mot.common.excel.entity.Cell;
import com.mot.common.excel.entity.RowData;
import com.mot.common.excel.entity.Sheet;
import com.mot.common.excel.entity.Style;
import com.mot.common.excel.entity.Table;
import com.mot.common.excel.handler.SharedStringHandle;
import com.mot.common.excel.handler.SheetHandle;
import com.mot.common.excel.handler.StylesHandle;
import com.mot.common.excel.utils.TableUtil;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author tianfx
 * @date 2021/12/16 2:34 下午
 */
public class ExcelProcess {
    static ThreadLocal<Register> registerThreadLocal = new ThreadLocal<>();
    public static Register getRegister(){
        return registerThreadLocal.get();
    }
    public static Table process(File file) throws IOException{
        if (file == null || !file.exists()){
            return null;
        }
        Table table =null;
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        registerThreadLocal.set(Register.getInstance());
        try {
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();
                getRegister().handlingEvents(zipEntry.getName(),zipFile.getInputStream(zipEntry));
            }
            table = dataAssembly();
        }finally {
            registerThreadLocal.remove();
        }
        return table;
    }

    private static Table dataAssembly() {
        Map<String, DefaultHandler> processMap = registerThreadLocal.get().processMap;
        SharedStringHandle sharedStringHandle = null;
        StylesHandle stylesHandle = null;
        List<String> sheets = new ArrayList<>();
        for (Map.Entry<String,DefaultHandler> entry :processMap.entrySet()){
            if (entry.getValue() instanceof SharedStringHandle) {
                sharedStringHandle = (SharedStringHandle) entry.getValue();
            }
            if (entry.getValue() instanceof StylesHandle){
                stylesHandle = (StylesHandle) entry.getValue();
            }
            if (entry.getValue() instanceof SheetHandle){
                sheets.add(entry.getKey());
            }
        }
        Collections.sort(sheets);
        Table table = new Table();
        SharedStringHandle finalSharedStringHandle = sharedStringHandle;
        StylesHandle finalStylesHandle = stylesHandle;
        sheets.forEach(s -> table.add(sheetAssembly(finalSharedStringHandle, finalStylesHandle, (SheetHandle) processMap.get(s))));
        return table;
    }

    private static Sheet sheetAssembly(SharedStringHandle sharedStringHandle, StylesHandle stylesHandle, SheetHandle sheetHandle) {
        List<List<SheetHandle.Node>> list = sheetHandle.getSheets();
        Sheet sheet = new Sheet();
        List<int[]> mergeCell = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<SheetHandle.Node> row = list.get(i);
            RowData rowData = new RowData();
            for (int j = 0; j < row.size(); j++) {
                if (isMerge(i,j,mergeCell)){
                    continue;
                }
                SheetHandle.Node node = row.get(j);
                Cell cell = new Cell();
                if (node == SheetHandle.placeholder){
                    cell = Cell.EMPTY;
                }else{
                    cell.setValue(node.isRef()&&!TableUtil.isBlank(node.getV())?sharedStringHandle.getDateString(Integer.parseInt(node.getV().toString())):node.getV());
                    cell.setColspan(node.getColspan());
                    cell.setRowspan(node.getRowspan());
                    int cl = 0;
                    if (cell.getColspan() != null && cell.getColspan() > 0){
                        cl = cell.getColspan();
                    }
                    int rl = 0;
                    if (cell.getRowspan() != null && cell.getRowspan() > 0){
                        rl = cell.getRowspan();
                    }
                    if (cl != 0 || rl != 0){
                        mergeCell.add(new int[]{i,j,(rl >0?rl -1:rl)+i,(cl >0?cl -1:cl)+j});
                    }
                    Integer sid = node.getSid();
                    if (sid != null){
                        cell.setStyle(stylesHandle.getStyle(sid));
                    } 
                }
                rowData.getCells().add(cell);
            }
            sheet.getRowDatas().add(rowData);
        }
        return sheet;
    }

    private static boolean isMerge(int i, int j, List<int[]> mergeCell) {
        if (mergeCell.isEmpty()){
            return false;
        }
        for (int[] ints : mergeCell) {
             if (i>= ints[0] && i <= ints[2] && j>= ints[1] && j <= ints[3]){
                 return true;
             }
        }
        return false;
    }
    
    public static String toHtml(Table table){
        StringBuilder builder = new StringBuilder(pre);
        List<Sheet> sheets = table.getSheets();
        for (int i = 0; i < sheets.size(); i++) {
            Sheet sheet = sheets.get(i);
            builder.append("<li><input id=\"tab"+(i+1)+"\" type=\"radio\" name=\"tab\" "+(i==0?"checked":"")+"><label for=\"tab"+(i+1)+"\">sheet"+(i+1)+"</label><div class=\"content\">");
            builder.append("<table cellspacing=\"0\" cellpadding=\"0\" "+Style.putStyle(sheet.getStyle())+">");
            List<RowData> rowDatas = sheet.getRowDatas();
            for (int j = 0; j < rowDatas.size(); j++) {
                RowData rowData = rowDatas.get(j);
                builder.append("<tr "+Style.putStyle(rowData.getStyle())+">");
                List<Cell> cells = rowData.getCells();
                for (int k = 0; k < cells.size(); k++) {
                    Cell cell = cells.get(k);
                    builder.append("<td "+Style.putStyle(cell==null?null:cell.getStyle()));
                    builder.append(parseValue(cell));
                }
                builder.append("</tr>");
            }
            builder.append("</table>");
            builder.append("</div></li>");
        }
        String s = builder.append(pos).toString();
        return s;
    }
    private static String parseValue(Cell cell){
        StringBuilder builder = new StringBuilder();
        if (cell != null){
            if (cell.getRowspan() != null && cell.getRowspan() > 0){
                builder.append(" rowspan = \""+cell.getRowspan()+"\"");
            }
            if (cell.getColspan() != null && cell.getColspan() > 0){
                builder.append(" colspan = \""+cell.getColspan()+"\"");
            }
        }
        builder.append(">");
        builder.append(Style.putValue(cell.getStyle(),cell.getValue()));
        return builder.append("</td>").toString();
    }
    private static final String pre = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Title</title></head><style>*{margin:0;padding:0}ul{position:relative;margin:10px}ul li{list-style:none}ul li input{display:none}ul li label{float:left;width:100px;text-align:center;line-height:30px;border:1px solid #000;border-right:0;box-sizing:border-box;cursor:pointer;transition:all .3s}ul li input:checked+label{color:#fff;background-color:#302e2e}ul li:last-child label{border-right:1px solid #000}ul li .content{opacity:0;position:absolute;left:0;top:31px;width:100%;border-top:1px solid #000;box-sizing:border-box;font-size:24px;text-align:center;transition:all .3s;padding: 5px;}ul li input:checked~.content{opacity:1}table {border-top: 1px solid #e8eaec;border-left: 1px solid #e8eaec;}td { border-right: 1px solid #e8eaec;border-bottom: 1px solid #e8eaec;padding: 5px;}</style><body><div class=\"tabs\"><ul>";
    private static final String pos = "</ul></div></body></html>";


}

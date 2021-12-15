package com.mot.common.utils.excelUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author tianfx
 * @date 2021/12/8 1:12 下午
 */
public class XmlTableHandler extends DefaultHandler implements Handler {

    XmlSharedStringHandler xmlSharedStringHandler;
    XmlStyleHandler xmlStyleHandler;
    String dimension = "dimension";
    String sheetData = "sheetData";
    String mergeCell = "mergeCell";
    String row = "row";
    String start = null;
    boolean dataFlag = false;
    int rowIndex = 1;
    int colIndex = 1;
    boolean hasRef = true;
    boolean inlineStr = false;
    String obj = "";
    Style cellXfs;
    Table table;

    @Override
    public void startDocument() throws SAXException {
        System.out.println("====start");
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith(dimension)){
            String ref = attributes.getValue("ref");
            String[] arr = ref.split(":");
            start = arr[0];
            if (arr.length == 1){
                table = Table.getInstance(1,1);
            }else {
                int[] sizes = TableUtils.getSize(arr[0], arr[1]);
                table = Table.getInstance(sizes[0],sizes[1]);
            }
        }else if (qName.endsWith(sheetData)){
            dataFlag = true;
        }else if (qName.endsWith(row)){
            String r = attributes.getValue("r");
            rowIndex = Integer.parseInt(r);
        }else if (qName.endsWith("c")){
            String r = attributes.getValue("r");
            int[] sizes = TableUtils.getSize(start, r);
            colIndex = sizes[0];
            String t = attributes.getValue("t");
            hasRef = t != null && t.equals("s");
            inlineStr = t != null && t.equals("inlineStr");
            String s = attributes.getValue("s");
            try {
                int i = Integer.parseInt(s);
                cellXfs = xmlStyleHandler.getCellXfs(i);
            }catch (Exception e){}
        }else if (qName.endsWith(mergeCell)){
            String ref = attributes.getValue("ref");
            String[] arr = ref.split(":");
            int[] sizes1 = TableUtils.getSize(start, arr[0]);
            int[] sizes2 = TableUtils.getSize(arr[0], arr[1]);
            Table.C c = table.tables.get(sizes1[1] - 1).get(sizes1[0] - 1);
            if (sizes2[0] > 1){
                c.colspan = sizes2[0];
            }
            if (sizes2[1] > 1){
                c.rowspan = sizes2[1];
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.endsWith(sheetData)){
            dataFlag = false;
        }else if (qName.endsWith("c")){
            if (inlineStr){
                table.add(rowIndex-1,colIndex-1, obj);
                table.tables.get(rowIndex-1).get(colIndex-1).setS(cellXfs);
                obj = "";
            }
            inlineStr = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (dataFlag){
            String value = new String(ch, start, length);
            if (value == null || value.trim().equals("")){
                return;
            }
            if (hasRef && value!=null && !value.trim().equals("")){
                try {
                    int i = Integer.parseInt(value);
                    table.add(rowIndex-1,colIndex-1, xmlSharedStringHandler.getData(i));
                } catch (NumberFormatException e) {
                    table.add(rowIndex-1,colIndex-1, value);
                }
                table.tables.get(rowIndex-1).get(colIndex-1).setS(cellXfs);
            }else if (inlineStr){
                obj += value;
            }else{
                table.add(rowIndex-1,colIndex-1, value);
                table.tables.get(rowIndex-1).get(colIndex-1).setS(cellXfs);
            }
            
        }
    }
    
    public Table getTable(){
        return table;
    }

    public XmlTableHandler(XmlSharedStringHandler xmlSharedStringHandler, XmlStyleHandler xmlStyleHandler) {
        this.xmlSharedStringHandler = xmlSharedStringHandler;
        this.xmlStyleHandler = xmlStyleHandler;
    }
}

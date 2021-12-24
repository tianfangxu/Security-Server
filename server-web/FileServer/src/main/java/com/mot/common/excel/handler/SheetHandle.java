package com.mot.common.excel.handler;

import com.mot.common.excel.utils.TableUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfx
 * @date 2021/12/16 3:04 下午
 */
public class SheetHandle extends DefaultHandler {

    List<List<Node>> sheets;
    String start = null;
    String current = null;
    String obj = "";
    public static final Node placeholder = new Node();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith("dimension")){
            String ref = attributes.getValue("ref");
            String[] arr = ref.split(":");
            start = arr[0];
            if (arr.length <= 1){
                List<Node> rows = new ArrayList<>();
                rows.add(placeholder);
                sheets = new ArrayList<>();
                sheets.add(rows);
            }else {
                int[] planes = TableUtil.getPlaneSize(arr[0],arr[1]);
                sheets = new ArrayList<>(planes[0]);
                for (int i = 0; i < planes[0]; i++) {
                    ArrayList<Node> e = new ArrayList<>();
                    for (int j = 0; j < planes[1]; j++) {
                        e.add(placeholder);
                    }
                    sheets.add(e);
                }
            }
        }else if (qName.endsWith("c")){
            current = attributes.getValue("r");
            Node node = new Node();
            String s = attributes.getValue("s");
            String t = attributes.getValue("t");
            if (!TableUtil.isBlank(s)){
                node.sid = Integer.parseInt(s);
            }
            node.ref = "s".equals(t);
            setNode(current,node);
        }else if (qName.endsWith("mergeCell")){
            String ref = attributes.getValue("ref");
            String[] arr = ref.split(":");
            Node node = getNode(arr[0]);
            int[] sizes = TableUtil.getPlaneSize(arr[0], arr[1]);
            if (sizes[0] > 1){
                node.rowspan = sizes[0];
            }
            if (sizes[1] > 1){
                node.colspan = sizes[1];
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.endsWith("c")){
            Node node = getNode(current);
            node.v = obj;
            obj = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String obj = new String(ch, start, length);
        this.obj += obj;
    }
    
    public void setNode(String index,Node node){
        int[] planeSize = TableUtil.getPlaneSize(start, index);
        int x = planeSize[0]-1;
        int y = planeSize[1]-1;
        if (sheets.size() <= x){
            for (int i = sheets.size()-1; i < x+1; i++) {
                ArrayList<Node> e = new ArrayList<>();
                for (int j = 0; j < y+1; j++) {
                    e.add(placeholder);
                }
                sheets.add(e);
            }
        }
        List<Node> row = sheets.get(x);
        if (row.size() <= y){
            for (int i = row.size()-1; i < y-1; i++) {
                row.add(placeholder);
            }
            row.add(node);
        }else{
            row.set(y,node);
        }
    }
    public Node getNode(String index){
        int[] planeSize = TableUtil.getPlaneSize(start, index);
        int x = planeSize[0]-1;
        int y = planeSize[1]-1;
        if (sheets.size() <= x){
            for (int i = sheets.size()-1; i < x+1; i++) {
                ArrayList<Node> e = new ArrayList<>();
                for (int j = 0; j < y+1; j++) {
                    e.add(placeholder);
                }
                sheets.add(e);
            }
        }
        List<Node> row = sheets.get(x);
        if (row.size() <= y){
            for (int i = row.size()-1; i < y+1; i++) {
                row.add(placeholder);
            }
        }
        return row.get(y);
    }

    public List<List<Node>> getSheets() {
        return sheets;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    public static class Node{
        /**
         * 数据
         */
        Object v;
        /**
         * 是否引用
         */
        boolean ref;
        /**
         * 样式ID
         */
        Integer sid;
        
        Integer colspan;
        Integer rowspan;

        public Object getV() {
            return v;
        }

        public Node setV(Object v) {
            this.v = v;
            return this;
        }

        public boolean isRef() {
            return ref;
        }

        public Node setRef(boolean ref) {
            this.ref = ref;
            return this;
        }

        public Integer getSid() {
            return sid;
        }

        public Node setSid(Integer sid) {
            this.sid = sid;
            return this;
        }

        public Integer getColspan() {
            return colspan;
        }

        public Node setColspan(Integer colspan) {
            this.colspan = colspan;
            return this;
        }

        public Integer getRowspan() {
            return rowspan;
        }

        public Node setRowspan(Integer rowspan) {
            this.rowspan = rowspan;
            return this;
        }
    }
}

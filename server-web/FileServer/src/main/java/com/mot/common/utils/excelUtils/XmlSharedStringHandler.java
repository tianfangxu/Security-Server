package com.mot.common.utils.excelUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfx
 * @date 2021/12/8 11:40 上午
 */
public class XmlSharedStringHandler extends DefaultHandler implements Handler {
    
    private static final int max = 1024;
    List<List<String>> data;
    int currentIndex = 0;
    List<String> current;
    String obj = "";
    
    @Override
    public void startDocument() throws SAXException {
        data = new ArrayList<>();
        data.add(new ArrayList<>(max));
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith("si")){
            List<String> list = data.get(currentIndex);
            if (list.size() > max){
                data.add(new ArrayList<>(max));
                currentIndex++;
                list = data.get(currentIndex);
            }
            current = list;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.endsWith("si")){
            current.add(obj.replaceAll("\n","</br>"));
            obj = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        obj += new String(ch,start,length);
    }

    @Override
    public String toString() {
        return "XmlSharedStringHandler{" +
                "data=" + data +
                '}';
    }
    
    public String getData(int index){
        int i = index / max;
        int j = index % max;
        if (data.get(i) != null){
            return data.get(i).get(j);
        }
        return null;
    }
}

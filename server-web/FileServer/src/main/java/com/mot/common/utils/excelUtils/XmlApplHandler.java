package com.mot.common.utils.excelUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfx
 * @date 2021/12/8 11:08 上午
 */
public class XmlApplHandler extends DefaultHandler implements Handler {

    String resource;
    List<String> sheetNames;
    int level = 0;

    @Override
    public void startDocument() throws SAXException {
        sheetNames = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        level++;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        level--;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (level == 2){
            resource = new String(ch,start,length);
        }else if (level == 4){
            sheetNames.add( new String(ch,start,length));
        }
    }

    @Override
    public String toString() {
        return "XmlApplHandler{" +
                "resource='" + resource + '\'' +
                ", sheetNames=" + sheetNames +
                '}';
    }
}

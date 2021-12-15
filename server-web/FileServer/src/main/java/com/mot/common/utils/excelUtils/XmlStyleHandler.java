package com.mot.common.utils.excelUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfx
 * @date 2021/12/9 10:53 上午
 */
public class XmlStyleHandler extends DefaultHandler implements Handler {
    
    List<Font> fonts;
    List<Style> cellXfs;
    Font current;
    Style cstyle;
    boolean isFonts = false;
    boolean isXf = false;

    @Override
    public void startDocument() throws SAXException {
        fonts = new ArrayList<>();
        cellXfs = new ArrayList<>();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith("fonts")){
            isFonts = true;
        }else if (qName.endsWith("cellXfs")){
            isXf = true;
        }
        
        if (isXf){
            if (qName.endsWith("xf")) {
                cstyle = new Style();
                cstyle.font = fonts.get(Integer.parseInt(attributes.getValue("fontId")));
                cellXfs.add(cstyle);
            }else if (qName.endsWith("alignment")){
                String horizontal = attributes.getValue("horizontal");
                cstyle.setHorizontal(horizontal);
            }
        }
        
        if (isFonts){
            if (qName.endsWith("sz")) {
                current = new Font();
                current.setSize(Double.parseDouble(attributes.getValue("val")));
            }else if (qName.endsWith("color")) {
                String theme = attributes.getValue("theme");
                String color = null;
                if (theme == null){
                    String rgb = attributes.getValue("rgb");
                    if (rgb != null){
                        int a = Integer.parseInt(rgb.substring(0, 2), 16);
                        int r = Integer.parseInt(rgb.substring(2, 4), 16);
                        int g = Integer.parseInt(rgb.substring(4, 6), 16);
                        int b = Integer.parseInt(rgb.substring(6, 8), 16);
                        color = "rgba("+r+","+g+","+b+","+a+")";
                    }else {
                        color = "#000000";
                    }
                    
                }else{
                    int i = Integer.parseInt(theme);
                    color = "#"+Theme.getColor(i);
                }
                current.setColor(color);
            }else if (qName.endsWith("name")) {
                current.setName(attributes.getValue("val")) ;
                fonts.add(current);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.endsWith("fonts")){
            isFonts = false;
        }else if (qName.endsWith("cellXfs")){
            isXf = false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }
    
    public Style getCellXfs(int i){
        return cellXfs.get(i);
    }
}

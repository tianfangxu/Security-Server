package com.mot.common.excel.handler;

import com.mot.common.excel.entity.Style;
import com.mot.common.excel.enums.Theme;
import com.mot.common.excel.utils.ColorUtil;
import com.mot.common.excel.utils.TableUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfx
 * @date 2021/12/16 3:05 下午
 */
public class StylesHandle extends DefaultHandler {
    List<Style> fonts;
    List<Style> fills;
    List<Style> cells;
    Style current;
    int type;
    boolean iscellXfs = false;
    boolean isfonts = false;
    boolean isfills = false;

    @Override
    public void startDocument() throws SAXException {
        fonts = new ArrayList<>();
        cells = new ArrayList<>();
        fills = new ArrayList<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith("cellXfs")){
            iscellXfs = true;
        }
        if (qName.endsWith("fonts")){
            isfonts = true;
        }
        if (qName.endsWith("fills")){
            isfills = true;
        }
        if (isfonts && qName.endsWith("font")){
            type = 1;
            current = new Style();
        }if (isfills && qName.endsWith("fill")){
            type = 2;
            current = new Style();
        }if (iscellXfs && qName.endsWith("xf")){
            type = 3;
            current = new Style();
        }
        
        if (isfonts && type == 1){
            if (qName.endsWith("b")){
                current.b = "b".equals(qName);
            }
            if (qName.endsWith("i")){
                current.i = "i".equals(qName);
            }
            if (qName.endsWith("u")){
                current.u = "u".equals(qName);
            }
            if (qName.endsWith("sz")){
                String val = attributes.getValue("val");
                if (!TableUtil.isBlank(val)){
                    current.size = TableUtil.parseDouble(val,12);
                }
            }
            if (qName.endsWith("color")) {
                String color = "rgb(0,0,0)";
                String theme = attributes.getValue("theme");
                String tint = attributes.getValue("tint");
                String argb = attributes.getValue("rgb");
                String s = Theme.getColor(TableUtil.parseInt(theme, Integer.MAX_VALUE),TableUtil.parseDouble(tint,0d));
                if (s == null){
                    String s1 = ColorUtil.hexadecimalToRgb(argb);
                    color = s1!=null?s1:color;
                }else{
                    color = s;
                }
                current.color = color;
            }
            if (qName.endsWith("name")) {
                current.typeface= attributes.getValue("val");
            }
        }else if ( isfills && type == 2){
            if(qName.endsWith("fgColor")){
                String color = "rgb(255,255,255)";
                String theme = attributes.getValue("theme");
                String tint = attributes.getValue("tint");
                String argb = attributes.getValue("rgb");
                String s = Theme.getColor(TableUtil.parseInt(theme, Integer.MAX_VALUE),TableUtil.parseDouble(tint,0d));
                if (s == null){
                    String s1 = ColorUtil.hexadecimalToRgb(argb);
                    color = s1!=null?s1:color;
                }else {
                    color = s;
                }
                current.bgColor = color;
            }
        }else if (iscellXfs && type == 3){
            if (qName.endsWith("xf")) {
                int fontId = Integer.parseInt(attributes.getValue("fontId"));
                int fillId = Integer.parseInt(attributes.getValue("fillId"));
                current = merge(fontId,fillId);
            }else if (qName.endsWith("alignment")){
                String horizontal = attributes.getValue("horizontal");
                current.align = horizontal;
            }
        }
    }

    private Style merge(int fontId, int fillId) {
        Style style = new Style();
        if (fonts.size() > fontId){
            Style font = fonts.get(fontId);
            style.copy(font);
        }
        if (fills.size() > fillId){
            Style fill = fills.get(fillId);
            style.copy(fill);
        }
        return style;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (type == 1 && qName.endsWith("font")){
            fonts.add(current);
        }else if (type == 2 && qName.endsWith("fill")){
            fills.add(current);
        }else if (type == 3 && qName.endsWith("xf")){
            cells.add(current);
        }
        if (qName.endsWith("cellXfs")){
            iscellXfs = false;
        }
        if (qName.endsWith("fonts")){
            isfonts = false;
        }
        if (qName.endsWith("fills")){
            isfills = false;
        }
        
    }
    
    public Style getStyle(Integer index){
        if (index != null && index < cells.size()){
            return cells.get(index);
        }
        return null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}

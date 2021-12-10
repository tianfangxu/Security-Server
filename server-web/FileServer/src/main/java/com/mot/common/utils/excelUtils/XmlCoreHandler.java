package com.mot.common.utils.excelUtils;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author tianfx
 * @date 2021/12/8 11:17 上午
 */
public class XmlCoreHandler extends DefaultHandler implements Handler {
    
    String creator;
    String creatorTime;
    String updater;
    String updateTime;
    
    int count = 0;
    

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith("creator")){
            count = 1;
        }else if (qName.endsWith("lastModifiedBy")){
            count = 3;
        }else if (qName.endsWith("created")){
            count = 2;
        }else if (qName.endsWith("modified")){
            count = 4;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (count == 1){
            creator = new String(ch,start,length);
        } else if (count == 2){
            creatorTime = new String(ch,start,length);
        } else if (count == 3){
            updater = new String(ch,start,length);
        } else if (count == 4){
            updateTime = new String(ch,start,length);
        }
    }

    @Override
    public String toString() {
        return "XmlCoreHandler{" +
                "creator='" + creator + '\'' +
                ", creatorTime='" + creatorTime + '\'' +
                ", updater='" + updater + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }
}

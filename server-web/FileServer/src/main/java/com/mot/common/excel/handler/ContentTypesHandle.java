package com.mot.common.excel.handler;

import com.mot.common.excel.ExcelProcess;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 获取所有的文件目录
 * @author tianfx
 * @date 2021/12/16 2:23 下午
 */
public class ContentTypesHandle extends DefaultHandler {
    
    public static ContentTypesHandle getInstance(){
        return new ContentTypesHandle();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith("Override")) {
            ExcelProcess.getRegister().register(attributes.getValue("PartName"));
        }
    }
}

package com.mot.common.utils.excelUtils;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author tianfx
 * @date 2021/12/8 10:10 上午
 */
public class XmlUtils {
    static SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
    
    public static void parse(InputStream inputStream,DefaultHandler handler) throws ParserConfigurationException, SAXException, IOException {
        SAXParser saxParser = saxParserFactory.newSAXParser();
        saxParser.parse(inputStream,handler);
    }
}

package com.mot.common.utils.excelUtils;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author tianfx
 * @date 2021/12/7 5:54 下午
 */
public class ZipUtils {
    
    public static String unzip(File file) throws IOException, ParserConfigurationException, SAXException {
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        XmlSharedStringHandler xmlSharedStringHandler = null;
        XmlTableHandler xmlTableHandler = null;
        XmlStyleHandler xmlStyleHandler = null;
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            if (!zipEntry.isDirectory()){
                InputStream inputStream = zipFile.getInputStream(zipEntry);
                DefaultHandler handler = null;
                if (zipEntry.getName().endsWith("app.xml")){
                    handler = new XmlApplHandler();
                }else if (zipEntry.getName().endsWith("core.xml")){
                    handler = new XmlCoreHandler();
                }else if (zipEntry.getName().endsWith("sharedStrings.xml")){
                    xmlSharedStringHandler = new XmlSharedStringHandler();
                    XmlUtils.parse(inputStream, xmlSharedStringHandler);
                }else if (zipEntry.getName().endsWith("sheet1.xml")){
                    xmlTableHandler = new XmlTableHandler(xmlSharedStringHandler,xmlStyleHandler);
                    XmlUtils.parse(inputStream, xmlTableHandler);
                }else if (zipEntry.getName().endsWith("styles.xml")){
                    xmlStyleHandler = new XmlStyleHandler();
                    XmlUtils.parse(inputStream, xmlStyleHandler);
                }
                if (handler != null){
                    XmlUtils.parse(inputStream, handler);
                }
            }
        }
        return TableUtils.tableHtml(xmlTableHandler.getTable());
    }
}

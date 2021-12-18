package com.mot.common.excel;

import com.mot.common.excel.handler.AppHandle;
import com.mot.common.excel.handler.ContentTypesHandle;
import org.xml.sax.helpers.DefaultHandler;
import com.mot.common.excel.handler.SharedStringHandle;
import com.mot.common.excel.handler.SheetHandle;
import com.mot.common.excel.handler.StylesHandle;

import javax.xml.parsers.SAXParserFactory;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author tianfx
 * @date 2021/12/16 1:56 下午
 */
public class Register {

    static SAXParserFactory saxParserFactory;
    Map<String, DefaultHandler> processMap;
    private static final String FIRSTFILE = "[Content_Types].xml";
    static Map<Pattern, Class<? extends DefaultHandler>> compiles;
    static {
        saxParserFactory = SAXParserFactory.newInstance();
        compiles = new HashMap<>();
        compiles.put(Pattern.compile("/xl/worksheets/sheet[1-9]+.xml"), SheetHandle.class);
        compiles.put(Pattern.compile("/xl/styles.xml"), StylesHandle.class);
        compiles.put(Pattern.compile("/xl/sharedStrings.xml"), SharedStringHandle.class);
        compiles.put(Pattern.compile("/docProps/app.xml"), AppHandle.class);
    }
    public static Register getInstance(){
        Register register = new Register();
        register.processMap = new HashMap<>();
        register.processMap.put(FIRSTFILE, ContentTypesHandle.getInstance());
        return register;
    }

    public void register(String name) {
        compiles.forEach((p,c)->{
            if (p.matcher(name).matches()) {
                try {
                    String key = name.startsWith("/")?name.substring(1):name;
                    processMap.put(key,c.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public void handlingEvents(String name, InputStream in){
        try {
            DefaultHandler defaultHandler = processMap.get(name);
            if (defaultHandler!=null){
                saxParserFactory.newSAXParser().parse(in, defaultHandler);
            }
        }catch (Exception e){
            throw new RuntimeException("HANDLE ERROR",e);
        }
        
    }
}

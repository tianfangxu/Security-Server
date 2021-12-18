package com.mot.common.excel.handler;

import com.mot.common.excel.utils.TableUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tianfx
 * @date 2021/12/16 3:02 下午
 */
public class SharedStringHandle extends DefaultHandler {

    private static final int MAX = 4096;
    List<String> smallData;
    List<List<String>> bigData;
    boolean smallOrBig;
    int currntI = 0;
    String obj = "";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.endsWith("sst")){
            String uniqueCount = attributes.getValue("uniqueCount");
            if (TableUtil.isBlank(uniqueCount)){
                uniqueCount = attributes.getValue("count");
            }
            if (!TableUtil.isBlank(uniqueCount)){
                int length = Integer.parseInt(uniqueCount);
                if (MAX >= length){
                    smallData = new ArrayList<>();
                }else{
                    smallOrBig = true;
                    int size = (length % MAX) > 0 ? (length / MAX) + 1 : length / MAX;
                    bigData = new ArrayList<>(size);
                    while (size> -1){
                        bigData.add(new ArrayList<>(MAX));
                        size--;
                    }
                    
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.endsWith("si")){
            List<String> list;
            if (smallOrBig){
                list = bigData.get(currntI);
                if (list.size() == MAX){
                    list = bigData.get(++currntI);
                }
            }else{
                list = smallData;
            }
            list.add(obj.replaceAll("\n","</br>"));
            obj = "";
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        obj += new String(ch,start,length);
    }
    

    public String getDateString(int index){
        if (smallOrBig){
            int i = index / MAX;
            int j = index % MAX;
            if (bigData.size() > i && bigData.get(i) != null && bigData.get(i).size() > j){
                return bigData.get(i).get(j);
            }
        }else if (smallData.size() > index){
            return smallData.get(index);
        }
        return null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}

package jiyun.com.openresausechina.view;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import jiyun.com.openresausechina.model.Model;

/**
 * Created by think on 2017/4/5.
 */

public class MyDefaultHander extends DefaultHandler {

    private String mNameStr;
    public Model model;
    private ArrayList<Model> list = new ArrayList<>();

    public ArrayList<Model> getList() {
        return list;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        mNameStr = qName;
        if (mNameStr.equals("news")) {
            if (model == null) {
                model = new Model();
            }
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equals("news")) {
            list.add(model);
            model = null;
        }
        mNameStr = "";
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if(mNameStr.equals("title")){
            String title = new String(ch,start,length);
            model.setTitle(title);
        }else if(mNameStr.equals("id")){
            String id = new String(ch, start, length);
            model.setId(id);
        }

    }

}

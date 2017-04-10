package jiyun.com.openresausechina.view;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Created by think on 2017/4/6.
 */

public class MyDefaultHanderTwo extends DefaultHandler{
    private String mNameStr;
    public String url;

    public String url() {
        return url;
    }

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        mNameStr = qName;

    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        mNameStr = "";
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        if(mNameStr.equals("url")){
            String url = new String(ch,start,length);
            this.url = url;
        }

    }
}

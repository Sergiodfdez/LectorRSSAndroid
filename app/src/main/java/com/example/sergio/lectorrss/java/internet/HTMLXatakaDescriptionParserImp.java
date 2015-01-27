package com.example.sergio.lectorrss.java.internet;
import com.example.sergio.lectorrss.java.internet.interfaces.HTMLXatakaDescriptionParser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Element;

import java.util.jar.Attributes;

/**
 * Created by sergio on 26/01/15.
 */
public class HTMLXatakaDescriptionParserImp implements HTMLXatakaDescriptionParser{
    public HTMLXatakaDescriptionParserImp() {
    }

    @Override
    public String parserImg(String html){
        Document doc=Jsoup.parse(html);
        Elements elements=doc.select("img");
        org.jsoup.nodes.Element el=elements.get(0);
        org.jsoup.nodes.Attributes attr=el.attributes();

        return attr.get("src");

    }
}

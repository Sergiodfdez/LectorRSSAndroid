package com.example.sergio.lectorrss.java.internet;

/**
 * Created by sergio on 22/01/15.
 */

import com.example.sergio.lectorrss.java.internet.interfaces.HTMLXatakaDescriptionParser;
import com.example.sergio.lectorrss.java.object.NoticiaDB;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



import java.net.*;
import java.io.*;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ParseadorRSS {
    URL feedXML;
    HTMLXatakaDescriptionParser parserHTML= new HTMLXatakaDescriptionParserImp();
    ArrayList<NoticiaDB> noticiaDBs = new ArrayList<NoticiaDB>();
    NoticiaDB noticiaDB;
    public ParseadorRSS(String feedXML) {
        //TODO Don't work corretly
        try {
            this.feedXML = new URL(feedXML);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(this.feedXML.openStream());
            NodeList nodeList = doc.getElementsByTagName("item");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                NodeList elements=  node.getChildNodes();
                noticiaDB =new NoticiaDB();
                for (int j=0; j<elements.getLength();j++){
                    Node aux= elements.item(j);
                    switch(aux.getNodeName()){
                        case "title":
                            noticiaDB.setTitulo(aux.getTextContent());
                            break;
                        case "link":
                            noticiaDB.setEnlace(aux.getTextContent());
                            break;
                        case "pubDate":
                            noticiaDB.setFecha(aux.getTextContent());
                            break;
                        case "description":
                            String img_src = parserHTML.parserImg(aux.getTextContent());
                            noticiaDB.setImagen(img_src);
                            noticiaDB.setContenido("Resumen pequeño de la noticia");
                            //noticiaDB.setContenido(aux.getTextContent());
                            break;
                    }
                }
            noticiaDBs.add(noticiaDB);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<NoticiaDB> getNoticiaDBs(){
        return this.noticiaDBs;
    }

}

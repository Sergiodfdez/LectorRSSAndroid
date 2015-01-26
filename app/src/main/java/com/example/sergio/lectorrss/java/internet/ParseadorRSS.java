package com.example.sergio.lectorrss.java.internet;

/**
 * Created by sergio on 22/01/15.
 */

import android.util.Log;

import com.example.sergio.lectorrss.java.internet.interfaces.HTMLXatakaDescriptionParser;
import com.example.sergio.lectorrss.java.object.Noticia;

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
    ArrayList<Noticia> noticias = new ArrayList<Noticia>();
    Noticia noticia;
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
                noticia=new Noticia();
                for (int j=0; j<elements.getLength();j++){
                    Node aux= elements.item(j);
                    switch(aux.getNodeName()){
                        case "title":
                            //Log.i("RESULTADO title",aux.getTextContent());
                            noticia.setTitulo(aux.getTextContent());
                            break;
                        case "link":
                            noticia.setEnlace(aux.getTextContent());
                           // Log.i("RESULTADO link",aux.getTextContent());
                            break;
                        case "pubDate":
                            noticia.setFecha(aux.getTextContent());
                            //Log.i("RESULTADO pubDate",aux.getTextContent());
                            break;
                        case "description":
                            //Log.i("RESULTADO description",aux.getTextContent());
                            String img_src = parserHTML.parserImg(aux.getTextContent());
                            noticia.setImagen(img_src);
                            noticia.setContenido("blalalalalalala");
                            break;
                    }
                }
            noticias.add(noticia);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public ArrayList<Noticia> getNoticias(){
        return this.noticias;
    }

}

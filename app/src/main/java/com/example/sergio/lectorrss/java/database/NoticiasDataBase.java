package com.example.sergio.lectorrss.java.database;

import com.example.sergio.lectorrss.java.object.Noticia;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sergio on 21/01/15.
 */
public class NoticiasDataBase{
    public ArrayList<Noticia> Array_Noticias1= new ArrayList<Noticia>();
    public ArrayList<Noticia> Array_Noticias2= new ArrayList<Noticia>();
    public NoticiasDataBase() {
        Noticia noticia_1=new Noticia();
        noticia_1.setTitulo("Titulo noticia 1");
        noticia_1.setContenido("Contenido de la  noticia 1");
        noticia_1.setFecha(new Date());

        Noticia noticia_2=new Noticia();
        noticia_2.setTitulo("Titulo noticia 2");
        noticia_2.setContenido("Contenido de la  noticia 2");
        noticia_2.setFecha(new Date());

        Noticia noticia_3=new Noticia();
        noticia_3.setTitulo("Titulo noticia 3");
        noticia_3.setContenido("Contenido de la  noticia 3");
        noticia_3.setFecha(new Date());


        Array_Noticias1.add(noticia_1);
        Array_Noticias1.add(noticia_2);

        Array_Noticias2.add(noticia_3);
    }

    public ArrayList<Noticia> getArray_Noticias1() {
        return Array_Noticias1;
    }

    public ArrayList<Noticia> getArray_Noticias2() {
        return Array_Noticias2;
    }
}

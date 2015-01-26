package com.example.sergio.lectorrss.java.database.interfaces;

import com.example.sergio.lectorrss.java.object.Noticia;

import java.util.ArrayList;

/**
 * Created by sergio on 22/01/15.
 */
public interface NoticiasDataBase {
    public ArrayList<Noticia> getArray_Noticias();
    public void crearNoticias(ArrayList<Noticia> noticia);
    public ArrayList<Noticia> getNoticias();
    public Noticia getNoticia(int position);
}

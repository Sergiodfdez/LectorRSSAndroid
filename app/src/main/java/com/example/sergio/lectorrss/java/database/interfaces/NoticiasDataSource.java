package com.example.sergio.lectorrss.java.database.interfaces;

import com.example.sergio.lectorrss.java.object.NoticiaDB;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sergio on 22/01/15.
 */
public interface NoticiasDataSource {
    public long createNoticia(NoticiaDB noticia);
    public long createNoticia(String titulo,String contenido, String enlace, String imagen, String fecha);
    public ArrayList<NoticiaDB> getAllNoticias();
    public NoticiaDB getNoticia(int position);
    public void open() throws SQLException;
    public void close();
}

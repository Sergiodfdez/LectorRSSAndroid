package com.example.sergio.lectorrss.java.database.interfaces;

import com.example.sergio.lectorrss.java.object.Noticia;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by sergio on 22/01/15.
 */
public interface NoticiasDataSource {
    public Noticia createNoticia(String titulo,String contenido, String enlace, String imagen, String fecha);
    public List<Noticia> getAllNoticias();
    public void open() throws SQLException;
    public void close();
}

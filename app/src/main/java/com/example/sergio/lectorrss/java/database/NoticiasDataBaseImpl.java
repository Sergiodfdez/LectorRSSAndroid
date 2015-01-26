package com.example.sergio.lectorrss.java.database;

import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataBase;
import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataSource;
import com.example.sergio.lectorrss.java.object.Noticia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sergio on 21/01/15.
 */
public class NoticiasDataBaseImpl implements NoticiasDataBase {
    private static NoticiasDataBaseImpl instance = null;
    private ArrayList<Noticia> Array_Noticias= new ArrayList<Noticia>();
    private NoticiasDataSource noticiasDataSource;

    protected NoticiasDataBaseImpl() {
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
        Noticia noticia_1=new Noticia();
        noticia_1.setTitulo("Titulo noticia 1");
        noticia_1.setContenido("Contenido de la  noticia 1");
        noticia_1.setFecha(dt.format(new Date()).toString());
        noticia_1.setEnlace("http://www.elmundotoday.com/2015/01/los-conductores-que-circulen-por-la-derecha-tendran-preferencia-a-la-hora-de-insultar/");


        Noticia noticia_2=new Noticia();
        noticia_2.setTitulo("Titulo noticia 2");
        noticia_2.setContenido("Contenido de la  noticia 2");
        noticia_2.setFecha(dt.format(new Date()).toString());
        noticia_2.setEnlace("http://www.elmundotoday.com/2015/01/una-rueda-de-carrito-de-super-nerviosa-y-sin-ideas-sobre-hacia-que-lado-desviarse-hoy/");


        Noticia noticia_3=new Noticia();
        noticia_3.setTitulo("Titulo noticia 3");
        noticia_3.setContenido("Contenido de la  noticia 3");
        noticia_3.setFecha(dt.format(new Date()).toString());
        noticia_3.setEnlace("http://www.elmundotoday.com/2015/01/el-tiempo-que-tardan-los-espanoles-en-estar-de-mala-hostia-al-volante-baja-de-los-7-a-los-23-segundos/");


        Array_Noticias.add(noticia_1);
        Array_Noticias.add(noticia_2);
        Array_Noticias.add(noticia_3);
    }
    public static NoticiasDataBaseImpl getInstance() {
        if(instance == null) {
            instance = new NoticiasDataBaseImpl();
        }
        return instance;
    }
    @Override
    public void crearNoticias(ArrayList<Noticia> noticias){
        for(int i=0; i< noticias.size();i++){
            this.Array_Noticias.add(noticias.get(i));
        }
    }

    @Override
    public ArrayList<Noticia> getNoticias() {
        return this.Array_Noticias;
    }

    @Override
    public ArrayList<Noticia> getArray_Noticias() {
        return Array_Noticias;
    }

    @Override
    public Noticia getNoticia(int position) {
        return Array_Noticias.get(position);
    }
}

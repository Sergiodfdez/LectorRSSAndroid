package com.example.sergio.lectorrss.java.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataSource;
import com.example.sergio.lectorrss.java.object.NoticiaDB;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sergio on 22/01/15.
 */
public class NoticiasDataSourceImpl implements NoticiasDataSource {
    // Database fields
    private static NoticiasDataSourceImpl instance = null;
    private ArrayList<NoticiaDB> noticiasDBs;
    private Boolean has_update=true;
    private SQLiteDatabase database;
    private NoticiasSQLHelper dbHelper;
    private String[] allColumns = {
            NoticiasSQLHelper.COLUMN_ID,
            NoticiasSQLHelper.COLUMN_TITULO,
            NoticiasSQLHelper.COLUMN_CONTENIDO,
            NoticiasSQLHelper.COLUMN_ENLACE,
            NoticiasSQLHelper.COLUMN_IMAGEN,
            NoticiasSQLHelper.COLUMN_FECHA
    };

    public static NoticiasDataSourceImpl getInstance(Context context) {
        if(instance == null) {
            instance = new NoticiasDataSourceImpl(context);
        }
        return instance;
    }

    private NoticiasDataSourceImpl(Context context) {
        dbHelper = new NoticiasSQLHelper(context);
    }


    @Override
    public long createNoticia(String titulo,String contenido, String enlace, String imagen, String fecha) {
        ContentValues values = new ContentValues();
        values.put(NoticiasSQLHelper.COLUMN_TITULO, titulo);
        values.put(NoticiasSQLHelper.COLUMN_CONTENIDO, contenido);
        values.put(NoticiasSQLHelper.COLUMN_ENLACE, enlace);
        values.put(NoticiasSQLHelper.COLUMN_IMAGEN, imagen);
        values.put(NoticiasSQLHelper.COLUMN_FECHA, fecha);

        database=dbHelper.getWritableDatabase();
        long insertId = database.insert(NoticiasSQLHelper.TABLE_NOTICIAS, null,values);
        return insertId;
    }


    @Override
    public long createNoticia(NoticiaDB noticia){
        ContentValues values = new ContentValues();
        values.put(NoticiasSQLHelper.COLUMN_TITULO, noticia.getTitulo());
        values.put(NoticiasSQLHelper.COLUMN_CONTENIDO, noticia.getContenido());
        values.put(NoticiasSQLHelper.COLUMN_ENLACE, noticia.getEnlace());
        values.put(NoticiasSQLHelper.COLUMN_IMAGEN, noticia.getImagen());
        values.put(NoticiasSQLHelper.COLUMN_FECHA, noticia.getFecha());

        database=dbHelper.getWritableDatabase();
        long insertId = database.insert(NoticiasSQLHelper.TABLE_NOTICIAS, null,values);
        if(insertId < 0){
            throw new Error("Error en la base e datos");
        }else{
            this.has_update=true;
        }
        return insertId;
    }

    public void deleteNoticia(NoticiaDB noticiaDB) {
        //TODO: Id Necesario
//        long id = noticia.getId();
//        System.out.println("Noticia deleted with id: " + id);
//        database.delete(NoticiasSQLHelper.TABLE_NOTICIAS, NoticiasSQLHelper.COLUMN_ID
//                + " = " + id, null);
    }

    @Override
    public ArrayList<NoticiaDB> getAllNoticias() {
        if(this.has_update){
            noticiasDBs = new ArrayList<NoticiaDB>();
            database=dbHelper.getWritableDatabase();
            Cursor cursor = database.query(NoticiasSQLHelper.TABLE_NOTICIAS,
                    allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            int position=0;
            while (!cursor.isAfterLast()) {
                NoticiaDB noticiaDB = cursorToNoticia(cursor,position);
                noticiasDBs.add(noticiaDB);
                cursor.moveToNext();
                position++;
            }
            // make sure to close the cursor
            cursor.close();
            this.has_update=false;
        }
        return noticiasDBs;
    }

    @Override
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    private NoticiaDB cursorToNoticia(Cursor cursor,int position) {
        NoticiaDB noticiaDB = new NoticiaDB();
        noticiaDB.setPosition(position);
        noticiaDB.setId(cursor.getLong(0));
        noticiaDB.setTitulo(cursor.getString(1));
        noticiaDB.setContenido(cursor.getString(2));
        noticiaDB.setEnlace(cursor.getString(3));
        noticiaDB.setImagen(cursor.getString(4));
        noticiaDB.setFecha(cursor.getString(5));
        return noticiaDB;
    }

    @Override
    public NoticiaDB getNoticia(int position){
        if(has_update){
            this.noticiasDBs=this.getAllNoticias();
        }
        NoticiaDB noticiaFound=null;
        for(NoticiaDB noticia : this.noticiasDBs){
            if(noticia.getPosition()==position){
                noticiaFound=noticia;
                break;
            }
        }
        if(noticiaFound==null){
            throw new Error("Problema al seleccionar la noticia");
        }
        return noticiaFound;
    };
}

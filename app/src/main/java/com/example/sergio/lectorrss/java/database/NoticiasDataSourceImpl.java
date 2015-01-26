package com.example.sergio.lectorrss.java.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataSource;
import com.example.sergio.lectorrss.java.object.Noticia;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sergio on 22/01/15.
 */
public class NoticiasDataSourceImpl implements NoticiasDataSource {
    // Database fields
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

    public NoticiasDataSourceImpl(Context context) {
        dbHelper = new NoticiasSQLHelper(context);
    }


    @Override
    public Noticia createNoticia(String titulo,String contenido, String enlace, String imagen, String fecha) {
        ContentValues values = new ContentValues();
        values.put(NoticiasSQLHelper.COLUMN_TITULO, titulo);
        values.put(NoticiasSQLHelper.COLUMN_CONTENIDO, contenido);
        values.put(NoticiasSQLHelper.COLUMN_ENLACE, enlace);
        values.put(NoticiasSQLHelper.COLUMN_IMAGEN, imagen);
        values.put(NoticiasSQLHelper.COLUMN_FECHA, fecha);
        //TODO: Problemas
        long insertId = database.insert(NoticiasSQLHelper.TABLE_NOTICIAS, null,values);

        Cursor cursor = database.query(NoticiasSQLHelper.TABLE_NOTICIAS,
                allColumns, NoticiasSQLHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Noticia newNoticia = cursorToNoticia(cursor);
        cursor.close();
        return newNoticia;
    }

    public void deleteNoticia(Noticia noticia) {
        //TODO: Id Necesario
//        long id = noticia.getId();
//        System.out.println("Noticia deleted with id: " + id);
//        database.delete(NoticiasSQLHelper.TABLE_NOTICIAS, NoticiasSQLHelper.COLUMN_ID
//                + " = " + id, null);
    }

    @Override
    public List<Noticia> getAllNoticias() {
        List<Noticia> noticias = new ArrayList<Noticia>();

        Cursor cursor = database.query(NoticiasSQLHelper.TABLE_NOTICIAS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Noticia noticia = cursorToNoticia(cursor);
            noticias.add(noticia);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return noticias;
    }

    @Override
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    @Override
    public void close() {
        dbHelper.close();
    }

    private Noticia cursorToNoticia(Cursor cursor) {
        Noticia noticia = new Noticia();
//        noticia.setId(cursor.getLong(0));
        noticia.setTitulo(cursor.getString(1));
        noticia.setContenido(cursor.getString(2));
        noticia.setEnlace(cursor.getString(3));
        noticia.setImagen(cursor.getString(4));
        noticia.setFecha(cursor.getString(5));
        return noticia;
    }
}

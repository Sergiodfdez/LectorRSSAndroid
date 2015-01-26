package com.example.sergio.lectorrss.java.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;

/**
 * Created by sergio on 22/01/15.
 */
public class NoticiasSQLHelper extends SQLiteOpenHelper{

    public static final String TABLE_NOTICIAS = "noticias";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITULO = "titulo";
    public static final String COLUMN_CONTENIDO = "contenido";
    public static final String COLUMN_ENLACE = "enlace";
    public static final String COLUMN_IMAGEN = "imagen";
    public static final String COLUMN_FECHA = "fecha";

    private static final String DATABASE_NAME = "noticias.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NOTICIAS + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_TITULO+ " text not null,"
            + COLUMN_CONTENIDO+ " text not null,"
            + COLUMN_ENLACE+ " text not null,"
            + COLUMN_IMAGEN+ " text not null,"
            + COLUMN_FECHA+ " text not null,"
            +");";

    public NoticiasSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(NoticiasSQLHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICIAS);
        onCreate(db);
    }
}

package com.example.sergio.lectorrss.java.database;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.Date;
import java.sql.SQLException;

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
    private static final int DATABASE_VERSION = 2;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NOTICIAS + "(" + COLUMN_ID
            + " integer primary key autoincrement, "
            + COLUMN_TITULO+ " varchar(255),"
            + COLUMN_CONTENIDO+ " varchar(255),"
            + COLUMN_ENLACE+ " varchar(255),"
            + COLUMN_IMAGEN+ " varchar(255),"
            + COLUMN_FECHA+ " varchar(255)"
            +");";
    private Context context;

    public NoticiasSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
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

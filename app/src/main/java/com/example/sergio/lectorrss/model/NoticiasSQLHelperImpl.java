package com.example.sergio.lectorrss.model;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by sergio on 22/01/15.
 */
public class NoticiasSQLHelperImpl extends SQLiteOpenHelper implements NoticiasSQLHelper{

    public static final String TABLE_NOTICIAS = "noticias";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOTICIAS = "noticias";

    private static final String DATABASE_NAME = "noticias.db";
    private static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table "
            + TABLE_NOTICIAS + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NOTICIAS
            + " text not null);";

    public NoticiasSQLHelperImpl(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(NoticiasSQLHelperImpl.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTICIAS);
        onCreate(db);
    }
}

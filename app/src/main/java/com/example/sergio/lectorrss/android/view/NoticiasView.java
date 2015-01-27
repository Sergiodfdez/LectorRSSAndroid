package com.example.sergio.lectorrss.android.view;

import android.content.Context;

import com.example.sergio.lectorrss.java.object.NoticiaDB;

import java.util.ArrayList;

/**
 * Created by sergio on 21/01/15.
 */
public interface NoticiasView {
    public void refreshView(ArrayList<NoticiaDB> noticiaDBs);
    public void showMessage(String message);
    public Context getContextHelper();
}

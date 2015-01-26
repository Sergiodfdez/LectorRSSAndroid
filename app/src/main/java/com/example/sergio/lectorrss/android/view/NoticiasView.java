package com.example.sergio.lectorrss.android.view;

import android.content.Context;

/**
 * Created by sergio on 21/01/15.
 */
public interface NoticiasView {
    public void refreshView();
    public void showMessage(String message);
    public Context getContextHelper();
}

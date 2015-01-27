package com.example.sergio.lectorrss.android.view;

import android.content.Context;
import android.media.Image;

/**
 * Created by sergio on 22/01/15.
 */
public interface DetalleNoticiaView {
    public void setImagen(String imagen);
    public void setTitulo(String titulo);
    public void setContenido(String contenido);
    public void setFecha(String fecha);
    public void openBrowser(String url);
    public Context getContextHelper();
}

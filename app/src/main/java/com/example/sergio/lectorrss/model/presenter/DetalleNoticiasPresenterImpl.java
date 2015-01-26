package com.example.sergio.lectorrss.model.presenter;

import android.app.Activity;

import com.example.sergio.lectorrss.android.view.DetalleNoticiaView;
import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataBase;
import com.example.sergio.lectorrss.java.database.NoticiasDataBaseImpl;
import com.example.sergio.lectorrss.java.object.Noticia;
import com.example.sergio.lectorrss.model.presenter.interfaces.DetalleNoticiasPresenter;

/**
 * Created by sergio on 22/01/15.
 */
public class DetalleNoticiasPresenterImpl implements DetalleNoticiasPresenter {
    DetalleNoticiaView detalleNoticiaView;
    Noticia noticia;
    NoticiasDataBase noticiasDataBase= NoticiasDataBaseImpl.getInstance();

    public DetalleNoticiasPresenterImpl(DetalleNoticiaView detalleNoticiaView,int position) {
        this.detalleNoticiaView=detalleNoticiaView;
        noticia=noticiasDataBase.getNoticia(position);
        setImagen(noticia.getImagen());
        setTitulo(noticia.getTitulo());
        setContenido(noticia.getContenido());
        setFecha(noticia.getFecha().toString());
    }

    @Override
    public void setImagen(String imagen) {
      detalleNoticiaView.setImagen(imagen);
    }

    @Override
    public void setTitulo(String titulo) {
        this.detalleNoticiaView.setTitulo(titulo);
    }

    @Override
    public void setContenido(String contenido) {
        detalleNoticiaView.setContenido(contenido);
    }

    @Override
    public void setFecha(String fecha) {
        detalleNoticiaView.setFecha(fecha);
    }

    @Override
    public void onClickNavigator() {
        detalleNoticiaView.openBrowser(noticia.getEnlace());
    }
}

package com.example.sergio.lectorrss.model.presenter;

import com.example.sergio.lectorrss.android.view.DetalleNoticiaView;
import com.example.sergio.lectorrss.java.database.NoticiasDataSourceImpl;
import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataSource;
import com.example.sergio.lectorrss.java.object.NoticiaDB;
import com.example.sergio.lectorrss.model.presenter.interfaces.DetalleNoticiasPresenter;

/**
 * Created by sergio on 22/01/15.
 */
public class DetalleNoticiasPresenterImpl implements DetalleNoticiasPresenter {
    DetalleNoticiaView detalleNoticiaView;
    NoticiasDataSource noticiasDataSource;
    NoticiaDB noticiaDB;

    public DetalleNoticiasPresenterImpl(DetalleNoticiaView detalleNoticiaView,int position) {
        this.detalleNoticiaView=detalleNoticiaView;
        noticiasDataSource= NoticiasDataSourceImpl.getInstance(this.detalleNoticiaView.getContextHelper());
        noticiaDB =noticiasDataSource.getNoticia(position);
        setImagen(noticiaDB.getImagen());
        setTitulo(noticiaDB.getTitulo());
        setContenido(noticiaDB.getContenido());
        setFecha(noticiaDB.getFecha().toString());
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
        detalleNoticiaView.openBrowser(noticiaDB.getEnlace());
    }
}

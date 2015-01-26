package com.example.sergio.lectorrss.model.presenter.interfaces;

/**
 * Created by sergio on 22/01/15.
 */
public interface DetalleNoticiasPresenter {
    public void setImagen(String imagen);
    public void setTitulo(String titulo);
    public void setContenido(String contenido);
    public void setFecha(String fecha);
    public void onClickNavigator();
}

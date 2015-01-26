package com.example.sergio.lectorrss.model.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sergio.lectorrss.android.view.NoticiasView;
import com.example.sergio.lectorrss.java.database.NoticiasDataBaseImpl;
import com.example.sergio.lectorrss.java.database.NoticiasDataSourceImpl;
import com.example.sergio.lectorrss.java.database.NoticiasSQLHelper;
import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataBase;
import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataSource;
import com.example.sergio.lectorrss.java.internet.ParseadorRSS;
import com.example.sergio.lectorrss.java.object.Noticia;
import com.example.sergio.lectorrss.model.presenter.interfaces.NoticiasPresenter;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sergio on 21/01/15.
 */
public class NoticiasPresenterImpl implements NoticiasPresenter {
    private NoticiasView noticiasView;
    private NoticiasDataSource noticiasDataSource;
    private NoticiasDataBase noticiasDataBase=NoticiasDataBaseImpl.getInstance();
    private NoticiasSQLHelper noticiasSQLHelper;

    public NoticiasPresenterImpl(NoticiasView noticiasView) {

        this.noticiasView = noticiasView;
        this.noticiasDataSource = new NoticiasDataSourceImpl(this.noticiasView.getContextHelper());
        new AsynSQL().execute();
        new AsyncXMLParser().execute();
        List<Noticia> noticias=noticiasDataSource.getAllSQLNoticias();

    }
    @Override
    public void refreshView(){
        noticiasView.refreshView();
    };
    @Override
    public void clickRefreshButton() {
        this.noticiasView.refreshView();
    }

    @Override
    public void onItemClicked(int position) {
        noticiasView.showMessage(String.format("Position %d clicked", position + 1));
    }


    class AsynSQL extends AsyncTask<String,Void,Void> {

        @Override
        protected void onPreExecute(){
            noticiasDataSource= new NoticiasDataSourceImpl((Context)noticiasView);
            try {
                noticiasDataSource.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected Void doInBackground(String... params) {
            SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
            noticiasDataSource.createNoticia("Titulo noticia 1",
                    "Contenido de la  noticia 1",
                    "http://www.elmundotoday.com/2015/01/los-conductores-que-circulen-por-la-derecha-tendran-preferencia-a-la-hora-de-insultar/",
                    dt.format(new Date()).toString(),
                    "");
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            noticiasDataSource.close();
        }
    }

    class AsyncXMLParser extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            ParseadorRSS parseadorRSS= new ParseadorRSS("http://www.xatakandroid.com/tag/feeds/rss2.xml");
            ArrayList<Noticia> noticias= parseadorRSS.getNoticias();
            noticiasDataBase.crearNoticias(noticias);
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            noticiasView.refreshView();
        }
    }

}

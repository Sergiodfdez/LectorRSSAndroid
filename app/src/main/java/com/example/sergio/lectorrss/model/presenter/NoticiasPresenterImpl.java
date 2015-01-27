package com.example.sergio.lectorrss.model.presenter;

import android.content.Context;
import android.os.AsyncTask;

import com.example.sergio.lectorrss.android.view.NoticiasView;
import com.example.sergio.lectorrss.java.database.NoticiasDataSourceImpl;
import com.example.sergio.lectorrss.java.database.interfaces.NoticiasDataSource;
import com.example.sergio.lectorrss.java.internet.ParseadorRSS;
import com.example.sergio.lectorrss.java.object.NoticiaDB;
import com.example.sergio.lectorrss.model.presenter.interfaces.NoticiasPresenter;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by sergio on 21/01/15.
 */
public class NoticiasPresenterImpl implements NoticiasPresenter {
    private NoticiasView noticiasView;
    private NoticiasDataSource noticiasDataSource;

    public NoticiasPresenterImpl(NoticiasView noticiasView) {

        this.noticiasView = noticiasView;
        this.noticiasDataSource = NoticiasDataSourceImpl.getInstance(this.noticiasView.getContextHelper());
        new AsyncXMLParser().execute();

    }
    @Override
    public void refreshView(){
        noticiasView.refreshView(noticiasDataSource.getAllNoticias());
    };
    @Override
    public void clickRefreshButton() {
        this.noticiasView.refreshView(noticiasDataSource.getAllNoticias());
    }

    @Override
    public void onItemClicked(int position) {
        noticiasView.showMessage(String.format("Position %d clicked", position + 1));
    }


    class AsyncXMLParser extends AsyncTask<String,Void,Void>{

        @Override
        protected void onPreExecute(){
            noticiasDataSource= NoticiasDataSourceImpl.getInstance((Context)noticiasView);
            try {
                noticiasDataSource.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(String... params) {
            ParseadorRSS parseadorRSS= new ParseadorRSS("http://www.xatakandroid.com/tag/feeds/rss2.xml");
            ArrayList<NoticiaDB> noticiasDBs = parseadorRSS.getNoticiaDBs();
            for(NoticiaDB noticia: noticiasDBs){
                noticiasDataSource.createNoticia(noticia);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void args) {
            noticiasView.refreshView(noticiasDataSource.getAllNoticias());
            noticiasDataSource.close();
        }
    }

}

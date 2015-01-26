package com.example.sergio.lectorrss.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sergio.lectorrss.android.view.NoticiasView;
import com.example.sergio.lectorrss.java.database.NoticiasDataBaseImpl;
import com.example.sergio.lectorrss.java.object.Noticia;
import com.example.sergio.lectorrss.model.adapter.Noticias_Adapter;
import com.example.sergio.lectorrss.R;
import com.example.sergio.lectorrss.model.presenter.interfaces.NoticiasPresenter;
import com.example.sergio.lectorrss.model.presenter.NoticiasPresenterImpl;

import java.util.ArrayList;

/**
 * Created by sergio on 21/01/15.
 */
public class Activity_Noticias extends Activity implements NoticiasView, View.OnClickListener, ListView.OnItemClickListener{
    private ListView listView;
    private NoticiasDataBaseImpl noticiasDataBase= NoticiasDataBaseImpl.getInstance();
    private Noticias_Adapter adapter;
    private NoticiasPresenter noticiasPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        listView=(ListView) findViewById(R.id.noticias_listview);
        listView.setOnItemClickListener(this);
        findViewById(R.id.button).setOnClickListener(this);
        noticiasPresenter = new NoticiasPresenterImpl(this);
    }

    @Override
    public void onClick(View v){
        this.noticiasPresenter.clickRefreshButton();
    }

    @Override
    public void refreshView() {
        ArrayList<Noticia> noticas=noticiasDataBase.getArray_Noticias();
        adapter = new Noticias_Adapter(this, noticiasDataBase.getArray_Noticias());
        listView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        noticiasPresenter.onItemClicked(position);
        Intent intent = new Intent(this, Activity_DetalleNoticia.class);
        intent.putExtra("id", position);
        startActivity(intent);
    }
}

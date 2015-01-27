package com.example.sergio.lectorrss.android.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sergio.lectorrss.android.view.NoticiasView;
import com.example.sergio.lectorrss.java.object.NoticiaDB;
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
    private Noticias_Adapter adapter;
    private NoticiasPresenter noticiasPresenter;
    private SQLiteDatabase db;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v){
        this.noticiasPresenter.clickRefreshButton();
    }

    @Override
    public void refreshView(ArrayList<NoticiaDB> noticiaDBs) {
        adapter = new Noticias_Adapter(this, noticiaDBs);
        listView.setAdapter(adapter);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public Context getContextHelper() {
        return this.getApplicationContext();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        noticiasPresenter.onItemClicked(position);
        Intent intent = new Intent(this, Activity_DetalleNoticia.class);
        intent.putExtra("id", position);
        startActivity(intent);
    }
}

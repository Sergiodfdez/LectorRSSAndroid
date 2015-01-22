package com.example.sergio.lectorrss.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.sergio.lectorrss.view.NoticiasView;
import com.example.sergio.lectorrss.Noticias_Adapter;
import com.example.sergio.lectorrss.R;
import com.example.sergio.lectorrss.model.NoticiasDataBase;
import com.example.sergio.lectorrss.presenter.NoticiasPresenter;
import com.example.sergio.lectorrss.presenter.NoticiasPresenterImpl;

/**
 * Created by sergio on 21/01/15.
 */
public class Activity_Noticias extends Activity implements NoticiasView, View.OnClickListener{
    private ListView listView;
    private NoticiasDataBase noticiasDataBase= new NoticiasDataBase();
    private Noticias_Adapter adapter;
    private NoticiasPresenter noticiasPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);
        listView=(ListView) findViewById(R.id.noticias_listview);
        findViewById(R.id.button).setOnClickListener(this);
        noticiasPresenter = new NoticiasPresenterImpl(this);

        setItems();
    }

    public void setItems() {
        adapter = new Noticias_Adapter(this, noticiasDataBase.getArray_Noticias1());
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v){
        this.noticiasPresenter.clickRefreshButton();
    }

    @Override
    public void refreshView() {
        adapter = new Noticias_Adapter(this, noticiasDataBase.getArray_Noticias2());
        listView.setAdapter(adapter);
    }
}

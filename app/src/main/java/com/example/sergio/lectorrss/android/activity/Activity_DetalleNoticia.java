package com.example.sergio.lectorrss.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sergio.lectorrss.R;
import com.example.sergio.lectorrss.android.view.DetalleNoticiaView;
import com.example.sergio.lectorrss.model.presenter.DetalleNoticiasPresenterImpl;
import com.example.sergio.lectorrss.model.presenter.interfaces.DetalleNoticiasPresenter;

import java.io.InputStream;

/**
 * Created by sergio on 22/01/15.
 */
public class Activity_DetalleNoticia extends Activity implements DetalleNoticiaView, View.OnClickListener {
    DetalleNoticiasPresenter detalleNoticiasPresenter;
    TextView titulo;
    TextView contenido;
    TextView fecha;
    ImageView imagen;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_noticia);
        Bundle extras = getIntent().getExtras();
        int position = extras.getInt("id");
        titulo=(TextView) findViewById(R.id.detalle_titulo);
        contenido= (TextView) findViewById(R.id.detalle_contenido);
        fecha= (TextView) findViewById(R.id.detalle_fecha);
        imagen=(ImageView) findViewById(R.id.detalle_imagen);
        findViewById(R.id.detalle_button_navigator).setOnClickListener(this);
        detalleNoticiasPresenter= new DetalleNoticiasPresenterImpl(this,position);
        Log.i("LectorRSS", "Position: "+position);


    }

    @Override
    public void setImagen(String imagen) {
        //TODO: Fail
//        Bitmap imagenInternet = null;
//        try {
//            InputStream in = new java.net.URL("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png").openStream();
//            imagenInternet = BitmapFactory.decodeStream(in);
//        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
//        }
//        this.imagen.setImageBitmap(imagenInternet);
    }

    @Override
    public void setTitulo(String titulo) {
        this.titulo.setText(titulo);
    }

    @Override
    public void setContenido(String contenido) {
        this.contenido.setText(contenido);

    }

    @Override
    public void setFecha(String fecha) {
        this.fecha.setText(fecha);
    }

    @Override
    public void openBrowser(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        detalleNoticiasPresenter.onClickNavigator();

    }

}

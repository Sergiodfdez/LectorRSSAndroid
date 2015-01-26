package com.example.sergio.lectorrss.model.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sergio.lectorrss.R;
import com.example.sergio.lectorrss.java.object.Noticia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by sergio on 21/01/15.
 */
public class Noticias_Adapter extends ArrayAdapter<Object> {
    Context context;
    private ArrayList<Noticia> noticias;

    public Noticias_Adapter(Context context, ArrayList<Noticia> noticias) {
        super(context, R.layout.item_noticias);
        this.context=context;
        this.noticias = noticias;
    }

    @Override
    public int getCount(){
        return noticias.size();
    }

    private static class PlaceHolder {
        TextView title;
        TextView time;
        TextView content;

        public static PlaceHolder generate(View convertView){
            PlaceHolder placeHolder = new PlaceHolder();
            placeHolder.title=(TextView) convertView.findViewById(R.id.noticia_textview_title);
            placeHolder.time=(TextView) convertView.findViewById(R.id.noticia_textview_time);
            placeHolder.content=(TextView) convertView.findViewById(R.id.noticia_textview_content);
            return placeHolder;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent){
        PlaceHolder placeHolder;
        if(convertView == null){
            convertView = View.inflate(context,R.layout.item_noticias,null);
            placeHolder = PlaceHolder.generate(convertView);
            convertView.setTag(placeHolder);
        }else{
            placeHolder = (PlaceHolder) convertView.getTag();
        }
        placeHolder.title.setText(noticias.get(position).getTitulo());
        placeHolder.time.setText(noticias.get(position).getFecha());
        placeHolder.content.setText("" + noticias.get(position).getContenido());
        return convertView;

    }
}

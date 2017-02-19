package com.example.quietus.flicks;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quietus on 2017/2/19.
 */

public class MovieAdapter extends ArrayAdapter<MovieJSon.Results> {
    Context context;
    int layoutResourceId;
    ArrayList<MovieJSon.Results> data = null;

    public MovieAdapter(Context context, int resource, ArrayList<MovieJSon.Results> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MovieHolder holder = null;

        if(holder == null)
        {
            // inflate layout
            row = LayoutInflater.from(getContext()).inflate(layoutResourceId, parent, false);
            // init holder
            holder = new MovieHolder();

            holder.image_poster = (ImageView) row.findViewById(R.id.image_poster);
            holder.text_title = (TextView) row.findViewById(R.id.text_title) ;
            holder.text_overview = (TextView) row.findViewById(R.id.text_overview) ;

            // setTag to holder
            row.setTag(holder);
        }
        else
        {
            // getTag to holder
            holder = (MovieHolder)row.getTag();
        }

        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            holder.text_title.setText(data.get(position).title);
            holder.text_overview.setText(data.get(position).overview);
            Picasso.with(context).load("https://image.tmdb.org/t/p/w780" + data.get(position).poster_path).placeholder(R.mipmap.ic_launcher).into(holder.image_poster);
        }
        else if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            holder.text_title.setText(data.get(position).title);
            holder.text_overview.setText(data.get(position).overview);
            Picasso.with(context).load("https://image.tmdb.org/t/p/w780" + data.get(position).backdrop_path).placeholder(R.mipmap.ic_launcher).into(holder.image_poster);
        }

        return row;
    }

    static  class MovieHolder{
        ImageView image_poster;
        TextView text_title;
        TextView text_overview;
    }
}

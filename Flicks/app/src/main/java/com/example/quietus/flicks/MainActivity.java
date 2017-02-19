package com.example.quietus.flicks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {
    String movie_ApiUrl = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    MovieJSon movie_Data = new MovieJSon();
    ListView lv_Movie;
    MovieAdapter ma_Movie;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getMovieData(movie_ApiUrl);
    }

    public void getMovieData(String Url){
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        client.get(Url, params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("Network Failed!\n", throwable.toString());
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Gson gson = new Gson();
                movie_Data = gson.fromJson(responseString, MovieJSon.class);
                setListView();
            }
        });
    }

    public void setListView()
    {
        lv_Movie = (ListView)findViewById(R.id.lv_movies);
        ma_Movie = new MovieAdapter(this, R.layout.list_item, movie_Data.results);
        lv_Movie.setAdapter(ma_Movie);

    }

}

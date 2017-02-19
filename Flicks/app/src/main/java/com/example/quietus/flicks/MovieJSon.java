package com.example.quietus.flicks;

import java.util.ArrayList;

/**
 * Created by Quietus on 2017/2/19.
 */

public class MovieJSon {

    public ArrayList<Results> results = new ArrayList<>();

    public class Results{
        public String poster_path;
        public String overview;
        public String title;
        public String backdrop_path;
    }
}

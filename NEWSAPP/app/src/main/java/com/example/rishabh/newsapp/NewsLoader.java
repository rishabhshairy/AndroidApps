package com.example.rishabh.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Created by Rishabh on 1/21/2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {


    public NewsLoader(Context context) {
        super(context);

    }

    @Override
    public List<News> loadInBackground() {
        List<News> newsList=null;
        try {
            URL url=QueryUtils.createUrl();
            String jsonResponse=QueryUtils.makeHttpRequest(url);
            newsList=QueryUtils.extractNewsFromJson(jsonResponse);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsList;

    }


    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}

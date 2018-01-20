package com.example.rishabh.newsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {
    ArrayList<News> newsArrayList;
    NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ListView newsList=(ListView) findViewById(R.id.newsList);
        newsArrayList=QueryUtils.extractNews();
        newsAdapter=new NewsAdapter(this,newsArrayList);
        newsList.setAdapter(newsAdapter);

    }
}

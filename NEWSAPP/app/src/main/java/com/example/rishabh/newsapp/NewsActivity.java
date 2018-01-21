package com.example.rishabh.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>>{
    public static final String LOG_tag=NewsActivity.class.getName();
    final int BundleID=1;
    private NewsAdapter newsAdapter;
    private TextView emptyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        ListView listView=(ListView)findViewById(R.id.newsList);
        emptyView=(TextView)findViewById(R.id.empty_view);

        //check for network connection
        ConnectivityManager connectivityManager= (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=connectivityManager.getActiveNetworkInfo();
        if (info!=null && info.isConnected())
        {
           getLoaderManager().initLoader(BundleID,null,this);
        }

        else {
            View loadiingView=(View)findViewById(R.id.indeterminantBar);
            loadiingView.setVisibility(View.GONE);
            emptyView.setText("No Internet Connection");

        }

        newsAdapter=new NewsAdapter(this,new ArrayList<News>());
        listView.setAdapter(newsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News news=newsAdapter.getItem(i);
                Uri uri= Uri.parse(news.getmUrl());
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });


    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        newsAdapter.clear();
        if (news!=null&&!news.isEmpty())
        {
            newsAdapter.addAll(news);
        }

       else{ emptyView.setText("No Recent news");}
        View loadingView=(View)findViewById(R.id.indeterminantBar);
        loadingView.setVisibility(View.GONE);

    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clear();
    }
}

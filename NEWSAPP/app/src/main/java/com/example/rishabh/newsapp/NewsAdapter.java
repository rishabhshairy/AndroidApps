package com.example.rishabh.newsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rishabh on 1/21/2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(@NonNull Context context, @NonNull List<News> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView=convertView;
        if (listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.news_item,parent,false);
        }

        News currentNews=getItem(position);
        TextView title=(TextView)listItemView.findViewById(R.id.newsTitle);
        title.setText(currentNews.getmTitle());
        TextView section=(TextView) listItemView.findViewById(R.id.newsSection);
        section.setText(currentNews.getmSection());
        TextView date=(TextView)listItemView.findViewById(R.id.newsDate);
        date.setText(currentNews.getmDate());

        return listItemView;
    }
}

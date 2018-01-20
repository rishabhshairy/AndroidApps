package com.example.rishabh.newsapp;

/**
 * Created by Rishabh on 1/20/2018.
 */

public class News {
    private String mTitle;
    private String mSection;
    private String mDate;

    public News(String mTitle, String mSection, String mDate) {
        this.mTitle = mTitle;
        this.mSection = mSection;
        this.mDate = mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSection() {
        return mSection;
    }

    public String getmDate() {
        return mDate;
    }
}

package com.example.rishabh.newsapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rishabh on 1/21/2018.
 */

public final class QueryUtils {
    public static final String LOG_Tag=QueryUtils.class.getName();

    private static final String sampleJsonResponse="{\r\n \"response\":" +
            " {\r\n\"status\": \"ok\",\r\n\"userTier\": " +
            "\"free\",\r\n\"total\": 1,\r\n\"content\":" +
            " {\r\n\"id\": " +
            "\"business\"/2014\"/feb\"/18\"/uk-inflation-falls-below-bank-england-target\",\r\n\"sectionId\":" +
            " \"business\",\r\n \"sectionName\":" +
            " \"Business\",\r\n\"webPublicationDate\": " +
            "\"2014-02-18T11:02:45Z\",\r\n\"webTitle\": " +
            "\"UK inflation falls below Bank of England's 2% target\",\r\n\"webUrl\":" +
            " \"https:" +
            "\"/\"/www.theguardian.com\"/business\"/2014\"/feb\"/18\"/uk-inflation-falls-below-bank-england-target\",\r\n\"apiUrl\":" +
            " \"https:" +
            "\"/\"/content.guardianapis.com\"/business\"/2014\"/feb\"/18\"/uk-inflation-falls-below-bank-england-target\"\r\n }\r\n }\r\n}";




    private QueryUtils()  {
    }

    public static ArrayList<News> extractNews()
    {
        ArrayList<News> news=new ArrayList<>();
        try {
            JSONObject newsObject=new JSONObject(sampleJsonResponse);
            JSONObject jsonObject=newsObject.getJSONObject("content");
            String section=jsonObject.getString("sectionId");
            String title=jsonObject.getString("webTitle");
            String date=jsonObject.getString("webPublicationDate");

            News news1=new News(title,section,date);
            news.add(news1);
            Log.i("section",section);
            Log.i("title",title);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_Tag,"Error in parsing");
        }
        return news;
    }
}

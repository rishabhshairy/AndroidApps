package com.example.rishabh.newsapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rishabh on 1/21/2018.
 */

public final class QueryUtils {
    public static final String LOG_Tag = QueryUtils.class.getName();



    static URL createUrl() {
        String url = createStringUrl();
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResult = "";
        HttpURLConnection urlConnection = null;
        InputStream newsIn=null;
        if (url == null) {
            return jsonResult;
        }


        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(15000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                newsIn = urlConnection.getInputStream();
                jsonResult = readFromStream(newsIn);
            } else {
                Log.e(LOG_Tag, "Error in connecting");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (url!=null)
            {
                urlConnection.disconnect();
            }
            if (newsIn!=null)
            {
                newsIn.close();
            }
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return jsonResult;
    }

    public static String readFromStream(InputStream newsIn) {
        StringBuilder jsonResponse = new StringBuilder();

        if (newsIn != null) {
            InputStreamReader streamReader = new InputStreamReader(newsIn, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(streamReader);
            try {
                String line = reader.readLine();
                while (line != null) {
                    jsonResponse.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return jsonResponse.toString();
    }



    public static List<News> extractNewsFromJson(String jsonResponse) {
        ArrayList<News> newsList = new ArrayList<News>();
        try {
            JSONObject baseResponse = new JSONObject(jsonResponse);
            JSONObject response = baseResponse.getJSONObject("response");
            JSONArray result = response.getJSONArray("results");
            for (int i = 0; i < result.length(); i++) {
                JSONObject singleNews = result.getJSONObject(i);
                String title = singleNews.getString("webTitle");
                String date = formatDate(singleNews.getString("webPublicationDate"));
                String section = singleNews.getString("sectionId");
                String url = singleNews.getString("webUrl");
                JSONArray tagArray = singleNews.getJSONArray("tags");
                String author = "";
                if (tagArray.length() == 0) {
                    author = null;
                } else {
                    for (int j = 0; j < tagArray.length(); j++) {
                        JSONObject authorObject = tagArray.getJSONObject(j);
                        author += authorObject.getString("webTitle");
                    }
                }
                newsList.add(new News(title, section, date, url, author));
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(LOG_Tag,"Error in parsing");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    private static String formatDate(String webPublicationDate) throws ParseException {
        String jsonDate = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat dateFormat = new SimpleDateFormat(jsonDate);

        Date date = dateFormat.parse(webPublicationDate);
        String newDate = "dd-MM-yyyy";
        SimpleDateFormat format = new SimpleDateFormat(newDate, Locale.getDefault());
        return format.format(date);

    }

    static String createStringUrl() {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .encodedAuthority("content.guardianapis.com")
                .appendPath("search")
                .appendQueryParameter("order-by", "relevance")
                .appendQueryParameter("show-references", "author")
                .appendQueryParameter("show-tags", "contributor")
                .appendQueryParameter("q", "Android")
                .appendQueryParameter("api-key", "13cab25b-9efb-4a84-9d18-cec58c049388");
        String url = builder.build().toString();
        return url;

    }
}

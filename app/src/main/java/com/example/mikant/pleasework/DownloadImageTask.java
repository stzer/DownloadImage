package com.example.mikant.pleasework;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by MikAnt on 08.07.2015.
 */
public class DownloadImageTask extends AsyncTask<String, Void, String> {
    private ImageView bmImage;
    private Context context;
    private static final String CLIENT_ID = "2036621798.6fb0206.58b92ab998744eb9a8a7c2d70f6dddbd";
    private String API_REQUEST;
    private String url;
    private String emptyRequest = "http://i1.sndcdn.com/artworks-000030854892-y53ha7-t500x500.jpg";
    private static final String API_URL = "https://api.instagram.com/v1";

    private InputStream downloadJSON() throws IOException {
        String downloadJsonUrl = API_URL + "/tags/" + API_REQUEST + "/media/recent?access_token=" + CLIENT_ID;
//        if (MainActivity.mmap.get(API_REQUEST) == null) {
//            MainActivity.mmap.put(API_REQUEST, 0);
//        } else {
//            for (Map.Entry<String, Integer> entry : MainActivity.mmap.entrySet()) {
//                if (entry.getKey().equals(API_REQUEST)) {
//                    entry.setValue(entry.getValue() + 1);
//                    break;
//                }
//            }
//        }
        URL url = new URL(downloadJsonUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();
        return connection.getInputStream();
    }

    private String streamToString(InputStream inputStream) throws IOException {
        String output = "";
        if (inputStream != null) {
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } finally {
                inputStream.close();
            }
            output = stringBuilder.toString();
        }
        return output;
    }

    public DownloadImageTask(ImageView bmImage, String API_REQUEST, Context context) {
        this.bmImage = bmImage;
        this.API_REQUEST = API_REQUEST;
        this.context = context;
    }

    protected String doInBackground(String... urls) {
        try {
            InputStream inputStream = downloadJSON();
            String responseJSON = streamToString(inputStream);
            JSONObject jsonObject = (JSONObject) new JSONTokener(responseJSON).nextValue();
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            JSONObject imageJsonObject;
            imageJsonObject = jsonArray.getJSONObject(new Random().nextInt(jsonArray.length())).getJSONObject("images").getJSONObject("standard_resolution");
            url = imageJsonObject.getString("url");
//            for (int i = 0; i < MainActivity.mylist.size(); i++) {
//                if (url.equals(MainActivity.mylist.size())) {
//                    new DownloadImageTask(bmImage, "empty", context)
//                            .execute();
//                }
//                else MainActivity.mylist.add(url);
//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String urldisplay;
        if (!(API_REQUEST.equals("empty"))) {
            urldisplay = url;
        } else
            urldisplay = emptyRequest;
        return urldisplay;
    }

    protected void onPostExecute(String result) {
        boolean flag = false;
        for (int i = 0; i < MainActivity.mylist.size(); i++) {
            if (url.equals(MainActivity.mylist.get(i))) {
                flag = true;
                break;
            }
        }
        if (flag) {
            new DownloadImageTask(bmImage, API_REQUEST, context)
                    .execute();
        } else {
            MainActivity.mylist.add(url);
            Glide.with(context).load(emptyRequest).into(bmImage);
            Glide.with(context).load(result).into(bmImage);
        }
    }
}
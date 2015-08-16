//package com.example.mikant.pleasework;
//
//import android.text.Editable;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.json.JSONTokener;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by MikAnt on 05.07.2015.
// */
//public class MyThread implements Runnable {
//    private static final String CLIENT_ID = "2036621798.6fb0206.58b92ab998744eb9a8a7c2d70f6dddbd";
//    public static String API_REQUEST;
//    private static final String API_URL = "https://api.instagram.com/v1";
//    private static Map<String, Integer> mmap = new HashMap<>();
//
//    //    private static String debug1 = "not_change1";
////    private static String debug2 = "not_change2";
////    private static String debug3 = "not_change3";
//    MyThread(String query) {
//        API_REQUEST = query;
//    }
//
//
//    private InputStream downloadJSON() throws IOException {
//        String downloadJsonUrl = API_URL + "/tags/" + API_REQUEST + "/media/recent?access_token=" + CLIENT_ID;
//        if(mmap.get(API_REQUEST)==null) {
//            mmap.put(API_REQUEST,0);
//        }
//        else {
//            for (Map.Entry<String, Integer> entry : mmap.entrySet()) {
//                //System.out.println( entry.getKey() + " " + entry.getValue() );
//                if (entry.getKey().equals(API_REQUEST)) {
//                    entry.setValue(entry.getValue() + 1);
//                    break;
//                }
//            }
//        }
//        URL url = new URL(downloadJsonUrl);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setReadTimeout(100000);
//        connection.setConnectTimeout(100000);
//        connection.setRequestMethod("GET");
//        connection.setDoInput(true);
//        connection.connect();
//        MainActivity.debug3 = String.valueOf(connection.getURL());
//        return connection.getInputStream();
//    }
//
////    String myfunc() throws IOException, JSONException {
////        InputStream inputStream = downloadJSON();
////        String responseJSON = streamToString(inputStream);
////        MainActivity.debug1 = responseJSON;
////        JSONObject jsonObject = (JSONObject) new JSONTokener(responseJSON).nextValue();
////        JSONArray jsonArray = jsonObject.getJSONArray("data");
////        JSONObject imageJsonObject = jsonArray.getJSONObject(0).getJSONObject("image").getJSONObject("standard_resolution");
////        //System.out.println("-------------------------------------------------------------------------------------------------");
////        //System.out.println(imageJsonObject.getString("url"));
////        //Log.d("11111111111111111",imageJsonObject.getString("url"));
////        return imageJsonObject.getString("url");
////    }
//
//    public boolean getInternetConnectionState() {
//        return true;
//    }
//
//    private String streamToString(InputStream inputStream) throws IOException {
//        String output = "";
//        if (inputStream != null) {
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            try {
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                while ((line = bufferedReader.readLine()) != null) {
//                    stringBuilder.append(line);
//                }
//                bufferedReader.close();
//            } finally {
//                inputStream.close();
//            }
//            output = stringBuilder.toString();
//        }
//        MainActivity.debug2 = output;
//        return output;
//    }
//
//    public void run() {
//        //while (!Thread.currentThread().isInterrupted()) {
//
//        try {
//            InputStream inputStream = downloadJSON();
//            String responseJSON = streamToString(inputStream);
//            MainActivity.debug1 = responseJSON;
//            JSONObject jsonObject = (JSONObject) new JSONTokener(responseJSON).nextValue();
//            JSONArray jsonArray = jsonObject.getJSONArray("data");
//            JSONObject imageJsonObject;
//            do {
//                imageJsonObject = jsonArray.getJSONObject(mmap.get(API_REQUEST)).getJSONObject("images").getJSONObject("standard_resolution");
//            } while(imageJsonObject.getString("url").equals(MainActivity.url));
//            MainActivity.url = imageJsonObject.getString("url");
//            //MainActivity.url = imageJsonObject.getString("url");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return;
//    }
////        try {
////            Thread.sleep(500);
////        } catch (InterruptedException e) {
////            Thread.currentThread().interrupt();
////        }
////    }
//}

package com.example.mikant.pleasework;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.MemoryCache;
import com.bumptech.glide.load.engine.cache.MemoryCacheAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends ActionBarActivity {
    int index=0;
    public static Map<String, Integer> mmap = new HashMap<>();
    public static ArrayList<String> mylist = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist.add("pull");
        if (!Glide.isSetup()) {
            GlideBuilder gb = new GlideBuilder(this);
            DiskCache dlw = DiskLruCacheWrapper.get(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/PLEASEWORK/"), 250 * 1024 * 1024);
            gb.setDiskCache(dlw);
            Glide.setup(gb);
        }
        final Button buttonHello = (Button) findViewById(R.id.button);
        buttonHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tview = (TextView) findViewById(R.id.textView);
//                for (int i = 0; i < mylist.size(); i++) {
//                    tview.append("\n"+mylist.get(i));
//                }
                EditText eview = (EditText) findViewById(R.id.editText);
                //buttonHello.setText(String.valueOf(index));
                String query = String.valueOf(eview.getText());
                if (!(query.equals(""))) {
                    //tview.setText(query);
                    new DownloadImageTask((ImageView) findViewById(R.id.imageView), query, getApplicationContext())
                            .execute();
                    //index++;
                } else {
                    new DownloadImageTask((ImageView) findViewById(R.id.imageView), "empty", getApplicationContext())
                            .execute();
                    //index++;
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

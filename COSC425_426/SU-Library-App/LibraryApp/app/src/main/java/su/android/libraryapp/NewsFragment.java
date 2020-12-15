package su.android.libraryapp;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by njraf_000 on 11/28/2016.
 * revamped by Sam Disharoon in 2020
 *
 * News Fragment Implementation
 * - pulls articles from a Json file
 * - opens up a webview fragment when clicked on
 */

// how the fragment is implemented
@SuppressWarnings("ALL")
public class NewsFragment extends Fragment implements AdapterView.OnItemClickListener {

    Bitmap[] icons;
    Boolean isConnected;
    String base_url, json_string;
    HttpURLConnection conn; // Connection object
    ListviewX lix;
    ListView listView;
    ArrayList<JSONObject> objs; //list of JSONObjects
    String[] strURL;
    String baseImgURL = "https://libapps.salisbury.edu/news/images/";
    ActionBar toolbar;

    // creates the fragment and initializes json file
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base_url = "https://libapps.salisbury.edu/news/json.php";
    }

    // creates the view for Library News Fragment
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        // Modify toolbar
        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle("Library News");

        // If there is no internet connection, inflate the fragment connection error fragment.
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            return inflater.inflate(R.layout.fragment_connection_error, container, false);
        } else {
            lix = new ListviewX(Objects.requireNonNull(getActivity()));
            listView = view.findViewById(R.id.news_list);
            listView.setVisibility(View.INVISIBLE);

            Thread th1 = new Thread(new NewsFragment.JSONRetriever()); // Fixed the AsycTask error for Json Retreiver
            Thread th2 = new Thread(new NewsFragment.PostRetreiver());

            try{ // Added to help run the new JSONretreiver code

                th1.start();
                th1.join();
                th2.start();
                th2.join();

            } catch (InterruptedException e){
                e.printStackTrace();
            }
            listView.setOnItemClickListener(this);
            return view;
        }
    }

    // provides insight on when it is clicked
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url;
        webViewFragment web;

        try {
            url = objs.get(position).getString("url");
            web = new webViewFragment(url, getResources().getString(R.string.news));
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().replace(R.id.content_container, web).addToBackStack(null).commit();
            MainActivity.pageStack.push(MainActivity.newsPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // gets bitmap values
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, (float) pixels, (float) pixels, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    private class JSONRetriever implements Runnable { //Updated to get rid of depreciated Asynctask function

        @Override
        public void run() {

            try {
                URL url; // URL object
                StringBuilder response = new StringBuilder(); // Allows string appending
                String inputLine; // Buffer for inputStream
                try {
                    url = new URL(base_url); // url passed in
                    try {
                        conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) { }
                    } catch (IOException e) { }
                } catch (MalformedURLException e) { }
                json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) { }
        }
    }

        private class PostRetreiver implements Runnable {
            @Override
            public void run() {
                //parseJSON(json_string);
                objs = new ArrayList<>();
                JSONArray jArray = null;

                try {
                    jArray = new JSONArray(json_string);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                assert jArray != null;
                for (int x = 0; x < jArray.length(); x++) {
                    try {
                        if (jArray.getJSONObject(x).getString("display_in_app").equals("1")) {
                            objs.add(jArray.getJSONObject(x));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                icons = new Bitmap[objs.size()];

                //populate array of urls
                strURL = new String[objs.size()];
                for (int x = 0; x < objs.size(); x++) {
                    try {
                        strURL[x] = baseImgURL + getLargeImage(objs.get(x).getString("image"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                final AsyncTask<String, Void, Bitmap[]> execute = new DownloadImageTask().execute(strURL);
            }
        }

    // retreives the image corresponding with the News article
    private String getLargeImage(String img) {
        String[] parts = img.split("\\.");
        return parts[0]+"@2x." + parts[1];
    }

    // gets the image and populates the list items
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap[]> {
        public DownloadImageTask() {}
        protected Bitmap[] doInBackground(String[] urls) {

            for(int x = 0; x < objs.size(); x++) {
                String urldisplay = urls[x];
                try {
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    icons[x] = getRoundedCornerBitmap(BitmapFactory.decodeStream(in), 12); //get image and round corners
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
            return icons;
        }

        protected void onPostExecute(Bitmap[] results) {

            ArrayList<ListItem> listItems = new ArrayList<ListItem>();
            ///////////populate icons, titles, and subtitles array////////////
            ListItem6 li6;
            for(int x = 0; x < objs.size(); x++) {
                try {
                    li6 = new ListItem6(Objects.requireNonNull(getActivity()), icons[x],"", objs.get(x).getString("title"), objs.get(x).getString("details"));
                    li6.getTextView3().setTextColor(Color.parseColor("#8a000000"));
                    listItems.add(li6);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            /////////////////////////////////////////////////////////////////
            lix.populate(listItems);
            listView.setAdapter(lix);
            listView.setVisibility(View.VISIBLE);
        }
    }
}


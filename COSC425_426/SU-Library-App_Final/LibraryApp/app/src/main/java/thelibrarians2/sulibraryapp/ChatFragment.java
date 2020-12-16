package thelibrarians2.sulibraryapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class ChatFragment extends Fragment {

    TextView noInternet, chatIs, chatMeUp;
    static boolean connected = false;
    HttpURLConnection conn;
    String full_string;
    ActionBar toolbar;
    ImageView bubble;
    MainActivity ma;
    View view;

    // Create an empty constructor.
    public ChatFragment(){}

    @Override // On activity creation function.
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override // On view create function.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Set the toolbar title.
        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.chat));

        // Declare variables and different views for further manipulation:
        view = inflater.inflate(R.layout.fragment_chat, container, false);
        noInternet = view.findViewById((R.id.noInternet));
        chatMeUp = view.findViewById(R.id.chatMeUp);
        chatIs = view.findViewById(R.id.chat_is);
        bubble = view.findViewById(R.id.bubble);
        ma = (MainActivity)getActivity();

        // Setup the loading screen until the json is fully parsed.
        chatMeUp = view.findViewById(R.id.chatMeUp);
        chatMeUp.setText(R.string.loading);
        chatMeUp.setTextColor(0x999999);

        // Start two threads for retrieving and parsing json information regarding chat availability.
        Thread thread1 = new Thread(new JSONRetriever());
        Thread thread2 = new Thread(new postRetriever());

        try {
            thread1.start();
            thread1.join();
            thread2.start();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Add social media buttons to the bottom of the fragment.
        setupSocialMedia();
        return view; // Return the view for inflating.
    }

    // Grabs the chat URL, and gets the text from the site.
    // Results are: "unavailable"m "available", or "away".
    private class JSONRetriever implements Runnable {
        @Override
        public void run() {
            try {
                URL url; // URL object
                StringBuilder response = new StringBuilder(); // Allows string appending
                String inputLine; // Buffer for inputStream
                try {
                    url = new URL(getResources().getString(R.string.chat_avail_url)); // url passed in
                    try {
                        conn = (HttpURLConnection)url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException ignored) { }
                    } catch (IOException ignored) { }
                } catch (MalformedURLException ignored) { }
                full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception ignored) { }
        }
    }

    private class postRetriever implements Runnable {
        @Override
        public void run () {
            chatChange();
        }
    }

    public void chatChange(){
        if (isNetworkAvailable()) {
            // Create a listener for opening the chat.
            View.OnClickListener openChat = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!(MainActivity.chat_webs.containsKey("library_chat")))
                        MainActivity.chat_webs.put("library_chat", new ChatWebViewFragment(getResources().getString(R.string.chat_url)));
                    connected = true;
                    FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_container, Objects.requireNonNull(MainActivity.chat_webs.get("library_chat")));
                    ft.addToBackStack(null).commit();
                }
            };

            // If chat is unavailable:
            if (full_string.equalsIgnoreCase("unavailable") || full_string.equalsIgnoreCase("away") && !connected) {
                // Set text colors to red, remove button listener, and display unavailable texts and images.
                chatMeUp.setTextColor(Color.parseColor("#ffcc0000"));
                chatIs.setTextColor(Color.parseColor("#ffcc0000"));
                bubble.setImageResource(R.drawable.chat_unavailable);
                chatMeUp.setText(R.string.chat_later);
                chatMeUp.setOnClickListener(null);
                chatIs.setText(R.string.unavail);

            // If chat is available:
            } else if (full_string.equalsIgnoreCase("available") && !connected) {
                // Set text colors to green, set button listener, and display available texts and images.
                chatMeUp.setTextColor(Color.parseColor("#ff669909"));
                chatIs.setTextColor(Color.parseColor("#ff669909"));
                bubble.setImageResource(R.drawable.chat_available);
                chatMeUp.setOnClickListener(openChat);
                chatMeUp.setVisibility(View.VISIBLE);
                chatIs.setText(R.string.Available);
                chatMeUp.setText(R.string.tap);

            // If the user has already started a chat:
            } else if (connected) {
                chatMeUp.setTextColor(Color.parseColor("#ff669909"));
                chatIs.setTextColor(Color.parseColor("#ff669909"));
                bubble.setImageResource(R.drawable.chat_available);
                chatMeUp.setVisibility(View.VISIBLE);
                chatMeUp.setOnClickListener(openChat);
                chatMeUp.setText(R.string.cont);
                chatIs.setText(R.string.avail);
            }
        // Else (Not internet connection):
        } else {
            // Set text color to gray, resize text, and display unavailable information.
            chatIs.setTextColor(Color.parseColor("#777777"));
            bubble.setImageResource(R.drawable.chat_unreachable);
            noInternet.setVisibility(View.VISIBLE);
            chatMeUp.setText(R.string.retry);
            chatIs.setText(R.string.unreachable);
            chatMeUp.setTextSize(16);
            chatIs.setTextSize(23);
        }
    }

    private void setupSocialMedia() {
        ImageView[] social;
        social = new ImageView[3];
        social[0] = view.findViewById(R.id.facebook);
        social[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("http://fb.com/sulibraries");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        social[1] = view.findViewById(R.id.twitter);
        social[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("http://twitter.com/sulibraries");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
        social[2] = view.findViewById(R.id.instagram);
        social[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uriUrl = Uri.parse("http://instagram.com/sulibraries");
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
                startActivity(launchBrowser);
            }
        });
    }

    // Returns true if network connection is available; vice versa.
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

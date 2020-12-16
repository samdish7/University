package thelibrarians2.sulibraryapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;


public class StudyRoomListFragment extends Fragment implements AdapterView.OnItemClickListener {
    View view;
    ListviewX lix;
    String rooms_url;
    int[] header_pos;
    ActionBar toolbar;
    JSONArray roomList;
    RoomDetail[] rooms;
    TextView loading_msg;
    ListView roomListView;
    ArrayList<ListItem> listItems;
    SwipeRefreshLayout swipeRefresher;
    ArrayList<String> availability_urls, availability_grab;
    public final int[] first_floor_room_ids = {16883,16884};
    public static final String[] sections = {"First Floor", "Second Floor"};

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        header_pos = new int[2];
        availability_urls = new ArrayList<>();
        availability_grab = new ArrayList<>();

        view = inflater.inflate(R.layout.fragment_study_room_list, container, false); // Assigns view
        roomListView = view.findViewById(R.id.roomsListView); // Assigns listview
        roomListView.setVisibility(View.INVISIBLE);

        loading_msg = view.findViewById(R.id.study_list_loading);
        loading_msg.setVisibility(View.VISIBLE);

        lix = new ListviewX(Objects.requireNonNull(getActivity()));
        listItems = new ArrayList<>();

        rooms_url = getResources().getString(R.string.rooms_url);

        //*************************

        Thread th1 = new Thread(new StudyRoomListFragment.JSONRetriever());
        Thread th2 = new Thread(new StudyRoomListFragment.PostRetriever());

        try{
            th1.start();
            th1.join();
            th2.start();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //*************************

        swipeRefresher = view.findViewById(R.id.swiperefreshstudylist); // Assigns SwipeRefreshLayout object
        swipeRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                roomListView.setVisibility(View.INVISIBLE);
                loading_msg.setVisibility(View.VISIBLE);
                refresh(); // Refreshes
                swipeRefresher.setRefreshing(false);
            }
        });

        swipeRefresher.setEnabled(false);

        roomListView.setOnItemClickListener(this);

        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.study_room));

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (header_pos[0] != position && header_pos[1] != position) {
            Fragment p1;
            FragmentManager fragmentManager;
            FragmentTransaction fragmentTransaction;
            int new_pos;
            boolean btn;
            if (position <= first_floor_room_ids.length){
                new_pos = position - 1;
                btn = false;
            }
            else {
                new_pos = position - 2;
                btn = true;
            }
            if (isNetworkAvailable()) {
                p1 = new StudyRoomDetailFragment(rooms[new_pos], btn); // Creates new Fragment
            } else {
                p1 = new ConnectionErrorFragment(new StudyRoomDetailFragment(rooms[new_pos], btn));
            }
            fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager(); // Gets Fragment Manager
            fragmentTransaction = fragmentManager.beginTransaction(); // Begins transaction
            fragmentTransaction.replace(R.id.content_container, p1); // Replaces fragment
            fragmentTransaction.addToBackStack(null).commit(); // Adds this fragment to backstack
            MainActivity.pageStack.push(MainActivity.studyroomPage);
        }
    }

    //*********************************************************
    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onResume(){
        super.onResume();
        refresh();
    }

    public void refresh(){
        swipeRefresher.setEnabled(false);
        lix = new ListviewX(Objects.requireNonNull(getActivity()));
        listItems = new ArrayList<>();
        availability_urls.clear();
        availability_grab.clear();
    }
    //*********************************************************
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    // Runnable to for retrieving study room json file
    private class JSONRetriever implements Runnable {
        @Override
        public void run() {
            try {
                roomList = getRooms(rooms_url);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Function to get the list of study rooms
    public JSONArray getRooms(String base_url) throws JSONException {
        System.out.println("GETTING Rooms");
        String json_string = "";
        HttpURLConnection conn;

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
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    try {
                        while ((inputLine = br.readLine()) != null) // While there are more contents to read
                            response.append(inputLine); // Append the new data to all grabbed data
                        br.close(); // Close connection
                    }
                    catch (IOException ignored) {}
                }
                catch (IOException ignored) {}
            }
            catch (MalformedURLException ignored) {}
            json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
        }
        catch (Exception ignored) {}
        System.out.println("GOT Rooms");

        JSONObject room = null;
        try {
            room = new JSONObject(json_string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        assert room != null;
        return room.getJSONArray("rooms");
    }

    // Runnable to parse JSON and fill list
    private class PostRetriever implements Runnable{
        @Override
        public void run(){
            try {
                parseJSON();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            fillList();
        }
    }

    // Parses JSON room names
    public void parseJSON() throws JSONException {
        int len = roomList.length();
        rooms = new RoomDetail[len];
        for (int i = 0; i < len; i++) {
            rooms[i] =  new RoomDetail(roomList.getJSONObject(i));
        }
    }

    // Fills the list for viewing
    public void fillList(){
        int room = 0;

        //section 1
        int h = 0;
        header_pos[0] = h;

        ListItem0 li = new ListItem0(Objects.requireNonNull(getActivity()), sections[0].toUpperCase());
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
        li.getTextView().setTextAppearance(R.style.listHeader);
        listItems.add(li);

        for (h = 0; h < first_floor_room_ids.length; h++) {
            rooms[room].setSection(sections[0]);
            ListItem1 roomItem = new ListItem1(getActivity(), rooms[room].icon, rooms[room].name);
            listItems.add(roomItem);
            room++;
        }
        header_pos[1] = h+1;

        //section 2
        li = new ListItem0(getActivity(), sections[1].toUpperCase());
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
        li.getTextView().setTextAppearance(R.style.listHeader);
        listItems.add(li);

        for (int x = 0; x < rooms.length-first_floor_room_ids.length; x++) {
            rooms[room].setSection(sections[0]);
            listItems.add(new ListItem1(getActivity(), rooms[room].icon, rooms[room].name));
            room++;
        }

        lix.populate(listItems);
        roomListView.setAdapter(lix);
        loading_msg.setVisibility(View.INVISIBLE);
        roomListView.setVisibility(View.VISIBLE);
//        swipeRefresher.setEnabled(true);
    }
    //*********************************************************
}



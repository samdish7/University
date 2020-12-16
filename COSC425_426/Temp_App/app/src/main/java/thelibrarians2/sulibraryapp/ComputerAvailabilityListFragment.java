package thelibrarians2.sulibraryapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.res.ResourcesCompat;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * ComputerAvailabilityListFragment:
 * <br>
 * This class is the fragment which displays a list of all computer rooms. These listitems are
 * clickable and will take the user to a ComputerAvailabilityDisplayFragment fragment. Also shown
 * are the current numbers of available and total computers, with a color-coded index.
 */

public class ComputerAvailabilityListFragment
        extends Fragment
        implements AdapterView.OnItemClickListener{

    /*
    * View objects
    * */
    ActionBar toolbar; /* The Applications ActionBar */
    View view; /* The entire View */
    ListView list_of_groups; /* The ListView object */
    ArrayList<ListItem> listItems; /* ArrayList of ListItems */
    ListviewX lix; /* The ListViewAdapter object for our ListView */
    SwipeRefreshLayout swipeRefresher; /* The SwipeRefreshLayout object */
    DrawerToggleListener toggleListener;
    JSONRetriever jretr; // JSONRetreiver object
    String[] room_names, // Names of rooms
            group_names, // Names of computer groups
            room_descriptions, // The descriptions for each room
            mapID, // The mapIDs for all the rooms for JSONRetreiver
            json_strings; // The JSON strings that are retrieved
    int[] num_comps, // Number of computers in each room
            imgs = {R.drawable.ac102_icon, R.drawable.ac1c20_icon, R.drawable.ac1c5_icon,
                    R.drawable.ac117_icon, R.drawable.ac162_icon, R.drawable.ac2c1_icon,
                    R.drawable.ac261_icon, R.drawable.ac262_icon, R.drawable.ac300_icon}; // Resource values of the images which are used in each listitem
    Integer num_available, // Number of available in a given room
            num_total; // Number total in a given room
    boolean loaded; // Determines if the page has been loaded

    /**
     * Default Constructor:
     * <br>
     * Sets loaded to false, which allows connection to begin when jretr.execute() is called
     */
    public ComputerAvailabilityListFragment() {
        loaded = false; // The page has not been loaded yet
    }

    /**
     * JSONRetreiver:
     * <br>
     * Inner class used to start an asynchronous class that reads a JSON stream from a URL and
     * uses this string in the parent classes methods
     */
    private class JSONRetriever extends AsyncTask<Void, Void, Void> {
        String url_to_stream; // URL and result of the URL
        HttpURLConnection conn; // Connection object

        /**
         * doInBackground
         * @return - null variable of type Void used as an object, not a primitive
         *<br>
        * THIS STARTS WHEN JSONRetriever.execute() IS CALLED
        *<br>
        * THIS IS STRICTLY FOR GRABBING THE STRING. DO NOT ATTEMPT TO
        * CALL ANY PARENT CLASS METHODS OR CHANGE ANY UI ELEMENTS IN
        * THIS METHOD. IT WILL FAIL AND YOU WILL BE SAD. I'M SORRY.
        */

        @Override
        protected Void doInBackground(Void... params) {
            if(!loaded) { // If the page has not been loaded
                json_strings = new String[room_names.length]; // Creates an array of the length of room name array
                for (int i = 0; i < room_names.length; i++) { // For the number of rooms there are
                    try {
                        url_to_stream = getActivity().getResources().getString(R.string.json_url); // First part of all URLs
                        String[] mapIDs = getResources().getStringArray(R.array.computer_map_ids); // Loads array of possible room IDs
                        url_to_stream = url_to_stream.concat(mapIDs[i]); // Adds room IDs to
                        URL url; // URL object
                        StringBuilder response = new StringBuilder(); // Creates an object for string appending
                        String inputLine; // Buffer for inputStream
                        try {
                            url = new URL(url_to_stream); // url passed in
                            try {
                                conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                                conn.setConnectTimeout(3000); // Aborts connection if connection takes too long
                                conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                                try {
                                    while ((inputLine = br.readLine()) != null) { // While there are more contents to read
                                        response.append(inputLine); // Append the new data to all grabbed data
                                    }
                                    br.close(); // Close connection
                                } catch (IOException e) {}
                            } catch (IOException e) {}
                        } catch (MalformedURLException e) {}
                        json_strings[i] = response.toString(); // Sets string in parent class to be the string taken from the URL
                    } catch (Exception e) {}
                }
            }
            else{
                cancel(true); // Cancels the task if the app tries to reload the page
            }
            return null;
        }

        /**
        * This starts once doInBackground(...) completes.
        * <br>
        * This continues  on the main thread (parent methods that alter the UI can be called at this point).
        */

        protected void onPostExecute(Void v){
            fillList();
        }

        /**
         * This is called once the connection fails.
         *<br>
         * This allows nothing to happen.
         */

        protected void onCancelled(){}
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        view = inflater.inflate(R.layout.fragment_computer_availability_list, container, false); // MAKE LAYOUT EDITABLE
        swipeRefresher = (SwipeRefreshLayout) view.findViewById(R.id.swiperefreshcomplist); // Assigns SwipeRefreshLayout object
        swipeRefresher.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            /**
             * On a Refresh, the page recognizes as not being loaded and restarts the process in
             * the refresh() procedure
             */
            @Override
            public void onRefresh(){ // OnClickListener
                jretr.cancel(true);
                loaded = false; // Sets loaded to false
                //swipeRefresher.setEnabled(false); // Turns off until list has loaded
                refresh(); // Refreshes
                swipeRefresher.setRefreshing(false);
            }
        });
        swipeRefresher.setEnabled(false);
        view.findViewById(R.id.list_of_groups).setVisibility(View.INVISIBLE); //
        view.findViewById(R.id.comp_list_loading).setVisibility(View.VISIBLE);
        list_of_groups = (ListView)view.findViewById(R.id.list_of_groups); // FIND LISTVIEW IN LAYOUT
        // Required empty public constructor

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.computer));

        return view; // FINALIZE VIEW AND MAKE IT VISIBLE
    }

    /**
     * Standard onDestroyView, cancels the streaming if still in process
     */
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        jretr.cancel(true);
    }

    /**
     * Standard onStart, remakes listview and reloads data
     */

    @Override
    public void onResume(){
        super.onResume();
        lix = new ListviewX(getActivity());
        loaded = false;
        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(true);
        refresh();
    }

    /**
     * Resets data if connection is available
     */

    private void refresh(){
        jretr = new JSONRetriever();
        swipeRefresher.setEnabled(false); // Turns off until list has loaded
        if(!loaded && isNetworkAvailable()) {
            room_names = getResources().getStringArray(R.array.computer_room_names);
            group_names = getResources().getStringArray(R.array.computer_group_names);
            room_descriptions = getResources().getStringArray(R.array.computer_room_descriptions);
            num_comps = getResources().getIntArray(R.array.num_computers);
            mapID = getResources().getStringArray(R.array.computer_map_ids);
            view.findViewById(R.id.comp_list_loading).setVisibility(View.VISIBLE);
            view.findViewById(R.id.list_of_groups).setVisibility(View.INVISIBLE);
            jretr.execute();
        }
        else if(!isNetworkAvailable()){
            Fragment fragment = new ConnectionErrorFragment(this);
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.content_container, fragment);
            fragmentTransaction.addToBackStack(null).commit();
        }
        else if(loaded && isNetworkAvailable()){
            fillList();
        }
    }

    /**
     * Parses the json string for a specific room, called many times
     *
     * @param i - index of json string to parse
     */

    private void parseJSON(int i){
        JSONObject j; // Declares JSONObject
        try {
            // READ DOCUMENTATION PLEASE FOR THE LOVE OF GOD
            j = new JSONObject(json_strings[i]);
            JSONObject all = j.getJSONObject("all");
            num_total = new Integer((Integer) all.get("total"));
            num_available = new Integer((Integer) all.get("available"));
        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * Fills the list with info gathered by json pull, puts list in fragment
     */

    private void fillList(){
        loaded = true;
        listItems = new ArrayList<ListItem>();
        ListItem0 li = new ListItem0(getActivity(), "COMPUTER GROUPS");
        listItems.add(styleLikeHeader(li));
        for(int i = 0; i < room_names.length; i++)
            addToList(i);
        lix.populate(listItems);
        list_of_groups.setAdapter(lix); // ASSIGN THE ADAPTER TO THE LISTVIEW
        list_of_groups.setOnItemClickListener(this); // MAKE LISTVIEW CLICKABLE
        swipeRefresher.setEnabled(true);
        view.findViewById(R.id.comp_list_loading).setVisibility(View.INVISIBLE);
        view.findViewById(R.id.list_of_groups).setVisibility(View.VISIBLE);
    }

    /**
     * Adds the listitem generated by  to the list
     * @param i - The position of the
     */

    private void addToList(int i){
        parseJSON(i);
        ListItem2 li2 = new ListItem2(getActivity(), imgs[i], group_names[i], getSubtitle(i));
        if((double)num_available/(double)num_total > .66){
            li2.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_green, null));
        }
        else if((double)num_available/(double)num_total > .33){
            li2.getTextView2().setTextColor(Color.parseColor("#555555"));
        }
        else
            li2.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(),R.color.color_red, null));
        listItems.add(li2);
    }

    /**
     * Creates a subtitle for the listitem
     *
     * @param i - the roomname at index i
     * @return the newly created string
     */

    private String getSubtitle(int i){

        String for_sub = new String(room_names[i]); // Creates the room name string
        for_sub = for_sub.concat(" / "); // Concats to make string
        Integer nc = new Integer(num_comps[i]); // Number of computers
        for_sub = for_sub.concat(nc.toString()); // ||
        for_sub = for_sub.concat(" Computers / "); // Concat
        for_sub = for_sub.concat(num_available.toString());
        for_sub = for_sub.concat(" Available");
        return for_sub; // Returns string

    }

    /**
     * Sends the user to the ComputerAvailabilityDisplayFragment
     *
     * @param parent - The view that is clicked view inhabits
     * @param view - The view that is clicked
     * @param position - The position of a list that is clicked
     * @param id - The id of the position that is clicked, not used
     */

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position != 0) {
            Fragment fragment = null;
            if (isNetworkAvailable()) {
                fragment = new ComputerAvailabilityDisplayFragment(position - 1);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, fragment);
                fragmentTransaction.addToBackStack(null).commit();
            } else {
                fragment = new ConnectionErrorFragment(new ComputerAvailabilityDisplayFragment(position - 1));
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, fragment);
                fragmentTransaction.addToBackStack(null).commit();
            }
            MainActivity.pageStack.push(MainActivity.computerPage);
        }
    }

    /**
     * Determines if the network connection is available
     *
     * @return if a network connection is available
     */

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private ListItem0 styleLikeHeader(ListItem0 li){
        li.getTextView().setTextAppearance(getActivity(), R.style.listHeader); // Looks like a header
        li.getTextView().setPaintFlags(li.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // Underlines
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null)); // Sets background color to the standard
        return li;
    }
}

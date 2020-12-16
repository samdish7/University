package thelibrarians2.sulibraryapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;


/**
 * HOME FRAGMENT
 * Revamped by Sam Disharoon, Jordan Welch, Delcan Sheehan, & Jack Stoetzel
 *
 * Home Fragment displays a grid to lead to other fragments,
 * a search bar to search the library's resources,
 * today's library hours,
 * the libraries 3 social media icons,
 * & any potential alerts that the library staff may send out
 */
public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    // attributes of the Home Fragment
    View view;
    FragmentManager fm;
    FragmentTransaction ft;
    String base_url, full_string;
    HttpURLConnection conn; // Connection object
    JSONObject week1;   //week(sun - sat) for homepage seven day calendar
    JSONObject week2;   //week(sun - sat) for homepage seven day calendar
    ArrayList<JSONObject> myweek;   //custom 7 day week
    boolean hasInternet = false;
    GridView gridView;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    ListView calendar;
    ArrayList<ListItem> calendarList;
    ListviewX lix2;
    ListView alertLV;
    ArrayList<ListItem> alertList;
    ListviewX lix3;
    public static Stack<Integer> pageStack;
    boolean alert = false;
    String alertText;
    String alertURL;
    String worldcat_url;
    SearchView search;
    String redirectURL; // URL for redirected search query
    String a = "AND"; // used for search ****DO NOT EDIT****
    String query, label, newURL, label2, newURL2;// used for search query
    int jSize;// used for search query

    Fragment currentFragment;
    BarCodeFragment myCard = new BarCodeFragment();
    LibraryHoursFragment libHours = new LibraryHoursFragment();
    ResearchHelpFragment researchHelp = new ResearchHelpFragment();
    DeviceAvailabilityFragment deviceAvailable = new DeviceAvailabilityFragment();
    AboutFragment about = new AboutFragment();
    HelpfulLinksFragment help = new HelpfulLinksFragment();
    ContactInfoFragment contact = new ContactInfoFragment();
    ChatFragment chat = new ChatFragment();
    MakerlabFragment makerlab = new MakerlabFragment();
    NewsFragment news = new NewsFragment();
    StudyRoomListFragment studyRoom = new StudyRoomListFragment();

    // default CTOR
    public HomeFragment() {}

    // class to create the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        alertURL = getResources().getString(R.string.alert_url);
        worldcat_url = getResources().getString(R.string.worldcat_url);
        base_url = getResources().getString(R.string.home_hours_url);
        redirectURL = getResources().getString(R.string.redirect_url);

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();

        lix = new ListviewX(Objects.requireNonNull(getActivity()));
        listItems = new ArrayList<>();
        gridView = view.findViewById(R.id.gridView);
        lix2 = new ListviewX(Objects.requireNonNull(getActivity()));
        calendarList = new ArrayList<>();
        calendar = view.findViewById(R.id.cal);
        lix3 = new ListviewX(Objects.requireNonNull(getActivity()));
        alertList = new ArrayList<>();
        alertLV = view.findViewById(R.id.alert);
        search = view.findViewById(R.id.search);
        search.setQueryHint("Search for Resources");

        pageStack = new Stack<>();
        pageStack.push(-1);



        // function to set up search function
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                ft = fm .beginTransaction();
                query = s;
                if(hasInternet){
                    jSize = 0;
                    //thread used to search the database
                    Thread searchTH = new Thread(new HomeFragment.SearchJSONRetriever());
                    try{
                        searchTH.start();
                        searchTH.join();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }

                    if (jSize > 0)
                        searchDialogBuild(); //builds search dialog after JSONArray is parsed
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment(worldcat_url + s, "Search Results"));
                    pageStack.push(MainActivity.homePage);
                    ft.replace(R.id.content_container, currentFragment).addToBackStack(null).commit();
                }

                search.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Clear fragment back stack when home page is visited
        if (fm.getBackStackEntryCount() > 0)
            fm.popBackStack(fm.getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

        setupMenu();

        lix.populate(listItems);
        gridView.setAdapter(lix);
        gridView.setOnItemClickListener(this);

        setupSocialMedia();

        // hide toolbar
        Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).hide();

        // threads for retrieving JSON information; Used to be Async task functions, replaced to stop memory leaks
        Thread th1 = new Thread(new HomeFragment.JSONRetriever());
        Thread th2 = new Thread(new PostRetriever());

        try{
            th1.start();
            th1.join();
            th2.start();
            th2.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        if (!hasInternet) {
            dialogBuild();
        }

        return view;
    }

    // sets up menu using the list items array
    private void setupMenu() {
        listItems.add(new HomeListItem(getActivity(), R.drawable.clock_1, "Library Hours"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.menu_news, "Library News"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.books, "Research Help"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.links, "Helpful Links"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.door, "Study Room Reservations"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.technology, "Device Availability"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.menu_chat, "Chat"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.printer_3d, "SU Libraries MakerLab"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.menu_card, "My Card"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.menu_maps, "Building Maps"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.contact_color, "Contact Information"));
        listItems.add(new HomeListItem(getActivity(), R.drawable.menu_about, "About"));
    }

    // sets up social media icons on bottom of page
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

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //drawer item clicked listener
        //section header positions: 0, 5, 11

        if(pageStack.peek() != position) {

            ft = fm.beginTransaction();
            Objects.requireNonNull(((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar()).show();

            switch (position)/*position in the array*/ {
                case 0:
                    //Library Hours
                    currentFragment = libHours;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 1:
                    //Library News
                    currentFragment = news;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 2:
                    //Research Help
                    currentFragment = researchHelp;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 3:
                    //Helpful Links
                    currentFragment = help;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 4:
                    //Study Room Reservations
                    currentFragment = studyRoom;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 5:
                    //Device Availability
                    currentFragment = deviceAvailable;
                    ft.replace(R.id.content_container, currentFragment); //replace current fragment with study room reservations fragment
                    break;
                case 6:
                    //Chat
                    currentFragment = chat;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 7:
                    //SU Libraries MakerLab
                    currentFragment = makerlab;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 8:
                    //My Card
                    currentFragment = myCard;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 9:
                    //Building Maps
                    if (hasInternet)
                        currentFragment = new webViewFragment("https://libapps.salisbury.edu/maps/", "Building Maps");
                    else
                        currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libapps.salisbury.edu/maps/", "Building Maps"));
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 10:
                    //Contact Information
                    currentFragment = contact;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case 11:
                    //About
                    currentFragment = about;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
            }

            pageStack.push(position);

            ft.addToBackStack(null).commit();
        }
    }

    // Seven day homepage calendar
    private void parseJSON() {

        try {
            JSONObject j = new JSONObject(full_string);
            //get info for this week and next week
            //each week object contains objects for each day of the week
            week1 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(0);
            week2 = new JSONObject(j.getJSONArray("locations").getString(0)).getJSONArray("weeks").getJSONObject(1);

            //get today's date
            // 1=SUNDAY, 7=SATURDAY
            int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

            //build array of 7 day period
            myweek = new ArrayList<>();
            for (int x = 0; x < 7; x++) {
                if (day <= 7) {
                    switch (day) {
                        case Calendar.SUNDAY:
                            myweek.add(week1.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week1.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week1.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week1.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week1.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week1.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week1.getJSONObject("Saturday"));
                            break;
                    }
                } else {
                    switch (day - 7) {
                        case Calendar.SUNDAY:
                            myweek.add(week2.getJSONObject("Sunday"));
                            break;
                        case Calendar.MONDAY:
                            myweek.add(week2.getJSONObject("Monday"));
                            break;
                        case Calendar.TUESDAY:
                            myweek.add(week2.getJSONObject("Tuesday"));
                            break;
                        case Calendar.WEDNESDAY:
                            myweek.add(week2.getJSONObject("Wednesday"));
                            break;
                        case Calendar.THURSDAY:
                            myweek.add(week2.getJSONObject("Thursday"));
                            break;
                        case Calendar.FRIDAY:
                            myweek.add(week2.getJSONObject("Friday"));
                            break;
                        case Calendar.SATURDAY:
                            myweek.add(week2.getJSONObject("Saturday"));
                            break;
                    }
                }
                day++;
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //checks for an alert
    private void parseAlertJSON() {

        try {
            JSONObject j = new JSONObject(full_string);
            if (!j.isNull("message")) {
                alertText = j.getString("message");
                if (alertText.length() > 1) {
                    alert = true;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (alert) {
            alertList.add(new AlertListItem(Objects.requireNonNull(getActivity()), alertText));
            lix3.populate(alertList);
            alertLV.setAdapter(lix3);
        }
    }

    // function to get calenders
    private class JSONRetriever implements Runnable {

        @Override
        public void run(){
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
                        hasInternet = true;
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) {
                            hasInternet = false;
                        }
                    } catch (IOException e) {
                        hasInternet = false;
                    }
                } catch (MalformedURLException e) {
                    hasInternet = false;
                }
                full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {
                hasInternet = false;
            }
            if (hasInternet) {
                parseJSON();
            }

            try {
                URL url; //URL object
                StringBuilder response = new StringBuilder(); // Allows string appending
                String inputLine; // Buffer for inputStream
                try {
                    url = new URL(alertURL); //Checks for alerts
                    try {
                        conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        hasInternet = true;
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) {
                            hasInternet = false;
                        }
                    } catch (IOException e) {
                        hasInternet = false;
                    }
                } catch (MalformedURLException e) {
                    hasInternet = false;
                }
                full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {
                hasInternet = false;
            }
            if (hasInternet) {
                parseAlertJSON();
            }
        }
    }

    // builds the alert dialog
    private void dialogBuild() {
        CustomAlertDialogBuilder dialog = new CustomAlertDialogBuilder(getActivity());
        dialog.setTitle("No Internet");
        dialog.setMessage("You are not connected to the Internet. Most of this app's features do not function without the Internet. Please reconnect to use the app properly.");
        String ok = getResources().getString(R.string.ok);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface d, int which) {
                d.dismiss();
            }
        });
        dialog.show();
        Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE); //button only referenced after show()
        if (button != null) {
            button.setBackgroundColor(Color.parseColor("#FFFFFF"));
            button.setTextColor(Color.parseColor("#000000"));
        }
    }

    private String getTime(String hours) {  //takes the JSON information and return the specified hour range

        switch (hours) { //hours from JSON are as follows
            case "7:30am - 8pm":
                return "7:30am - 8:00pm";
            case "10am - 8pm":
                return "10:00am - 8:00pm";
            case "11am - 2am":
                return "11:00am - 2:00am";
            case "7:30am - 2am":
                return "7:30am - 2:00am";
            case "7:30am - 5pm":
                return "7:30am - 5:00pm";
            case "7:30am - 4pm":
                return "7:30am - 4:00pm";
            case "Closing at 7 PM":
                return "Closing at 7 PM";
            case "24 Hours":
                return "Open 24 Hours";
            case "Library Closed":
                return "Library Closed";
            case "Open at 7:30 AM":
                return "Open at 7:30 AM";
            case "10am - 2am":
                return "10:00am - 2:00am";
            case "10am - 10pm":
                return "10:00am - 10:00pm";
        }
        return hours; //return unavailable if none of the above options are pulled
    }

    private class PostRetriever implements Runnable {
        // Pages are zero-indexed
        // Update current page and adjacent pages
        @Override
        public void run() {
            if (hasInternet) {
                try {
                    calendarList.add(new CalendarListItem(Objects.requireNonNull(getActivity()), "Hours Today:", getTime(myweek.get(0).getString("rendered"))));
                    lix2.populate(calendarList);
                    calendar.setAdapter(lix2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // grabs the JSON object sent by search query
    private class SearchJSONRetriever implements Runnable {

        @Override
        public void run() {
            redirectQuery(redirectURL);
        }
    }

    /**
     * see API info for POST Request params
     */
    // function for searchJsonRetriever
    public void redirectQuery(String... parms){
        StringBuilder sb = new StringBuilder(); // formulate POST request
        StringBuilder result = new StringBuilder(); // retrieve json
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("query", query); // for POST request
        parameters.put("s", a); // for POST request

        // for sending the POST
        int i = 0;
        for (String key : parameters.keySet()) {
            try {
                if (i != 0)
                    sb.append("&");
                sb.append(key).append("=").append(URLEncoder.encode(parameters.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            i++;
        }

        try {

            String urlStr = parms[0]; // url redirect string

            URL urlObj = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();

            // setup for sending POST request
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.connect();

            String paramStr = sb.toString();
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(paramStr);
            wr.flush();
            wr.close();

            // reads returned info and turns into a string
            InputStream in = new BufferedInputStream(conn.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            String json = result.toString(); // string that holds the retrieved JSON object

            // if no suggestions, send query off
            if (json.equals("[]")) {
                currentFragment = new webViewFragment(worldcat_url + query, "Search Results");
                pageStack.push(MainActivity.homePage);
                ft.replace(R.id.content_container, currentFragment).addToBackStack(null).commit();
            // if suggestions, then create a dialog box to show suggestions, and then display them to user
            } else {

                JSONArray jArray = new JSONArray(json); // JSON array to store the parsed string
                jSize = jArray.length();
                JSONObject obj1 = jArray.getJSONObject(0);

                try{
                    label = obj1.getString("label");
                    newURL = obj1.getString("url");
                } catch(JSONException e) {
                    e.printStackTrace();
                }
                // only called if there is more than 1 suggestion
                if(jSize > 1){
                    JSONObject obj2 = jArray.getJSONObject(1);
                    try{
                        label2 = obj2.getString("label");
                        newURL2 = obj2.getString("url");
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        } finally {
            if (conn != null)
                conn.disconnect();
        }

    }

    // function to build searching the JSON array returned from search suggestions after search query is redirected
    private void searchDialogBuild(){
        // uses out custom dialog alert builder
        CustomAlertDialogBuilder dialog = new CustomAlertDialogBuilder(getActivity());
        if(jSize == 1) {
            dialog.setTitle("Are You Looking for This?");
        } else {
            dialog.setTitle("Are You Looking for These?");
        }
        dialog.setMessage("This search box searches the library's resources, but it looks like you may be searching for something else.");

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"No, Search the Catalog ", new DialogInterface.OnClickListener(){
           @Override
           public void onClick(DialogInterface a, int which){
               a.dismiss();
               currentFragment = new webViewFragment(worldcat_url + query,"Search Results");
               pageStack.push(MainActivity.homePage);
               ft.replace(R.id.content_container, currentFragment).addToBackStack(null).commit();
           }
        });
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, label, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface b, int which){
                b.dismiss();
                currentFragment = new webViewFragment(newURL,"Search Results");
                pageStack.push(MainActivity.homePage);
                ft.replace(R.id.content_container, currentFragment).addToBackStack(null).commit();

            }
        });
        // only make 3rd button if needed
        if(label2 != null && jSize == 2) {
            dialog.setButton(DialogInterface.BUTTON_NEUTRAL, label2, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface c, int i) {
                    c.dismiss();
                    currentFragment = new webViewFragment(newURL2, "Search Results");
                    pageStack.push(MainActivity.homePage);
                    ft.replace(R.id.content_container, currentFragment).addToBackStack(null).commit();
                }
            });
        }
        // display the dialog and create buttons
        dialog.show();
        Button button1 = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button button2 = dialog.getButton(DialogInterface.BUTTON_NEUTRAL);
        Button button3 = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        if(button1 != null){
            button1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            button1.setTextColor(Color.parseColor("#800000"));
        }
        if(button2 != null){
            button2.setBackgroundColor(Color.parseColor("#FFFFFF"));
            button2.setTextColor(Color.parseColor("#800000"));
        }
        if(button3 != null){
            button3.setBackgroundColor(Color.parseColor("#FFFFFF"));
            button3.setTextColor(Color.parseColor("#800000"));
        }

    }
}


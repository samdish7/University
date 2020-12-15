package su.android.libraryapp;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

/**
 * Revamped by Sam Disharoon & Jack Stoetzel in 2020
 * Used in Contact information Fragment
 * Displays the various staff at the library and shows helpful links they possess
 * and ability to chat with them
 * Uses Subject List item
 */

public class SubjectDetailedFragment extends Fragment implements AdapterView.OnItemClickListener{

    View view;
    ListviewX lix;
    static int tab;
    TextView title;
    ActionBar toolbar;
    JSONObject subject;
    Integer[] databases;
    JSONArray databases1;
    ListView listViewsrr;
    ListItem3 chat_status;
    HttpURLConnection conn;
    String[] sectionTitles;
    int num_research_guides;
    ArrayList<ListItem> listItems;
    boolean connected, chattable;
    Thread retrieverThread, postThread;
    String[] sectionHeader, subjectInfo;
    JSONObject databaseObject, contactsObject;
    String availability, full_string, base_url;
    ImageView icon, rectangle, staff_pic, frame;

    // default CTOR
    public SubjectDetailedFragment() {
        chattable =false;
    }

    // main CTOR
    public SubjectDetailedFragment(int pos, JSONObject sub, JSONObject cntctObject, JSONObject dbObject){
        tab = pos;
        subject = sub;
        chattable = false;
        databaseObject = dbObject;
        contactsObject = cntctObject;
    }


    // creates view
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_subject_detailed, container, false); // Grabs whole view
        icon = view.findViewById(R.id.researchImage);  //icon for the subject
        rectangle = view.findViewById(R.id.acc_header);  //rectangle portion
        frame = view.findViewById(R.id.frame1);
        title = view.findViewById(R.id.acc_title);  //text displaying name of subject
        staff_pic = view.findViewById(R.id.staff_icon); // image for staff picture
        databases = new Integer[]{}; // Initializes empty Integer array
        sectionHeader = getResources().getStringArray(R.array.subject_headers); // Grabs array of subject headers
        sectionTitles = getResources().getStringArray(R.array.subject_detailed_constant); // Grabs the item text shared by all pages


        postThread = new Thread(new SubjectDetailedFragment.postRetriever());
        retrieverThread = new Thread(new SubjectDetailedFragment.JSONRetriever());

        num_research_guides = 0;

        try {
            FillPage();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            retrieverThread.start();
            retrieverThread.join();
            postThread.start();
            postThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        if(toolbar != null)
            toolbar.setTitle(R.string.research);

        return view;
    }

    public void FillPage() throws JSONException {
        String subjectName = null, image, liaison = null, school = null;
        subjectInfo = new String[10];
        int drawableLiaison = getResources().getIdentifier("su_seal", "drawable", "su.android.libraryapp");
        int drawableImage = 0;
        try {
            liaison = (String) subject.get("liaison");
            image = (String) subject.get("image");
            drawableLiaison = getResources().getIdentifier(liaison, "drawable", "su.android.libraryapp");
            if (drawableLiaison == 0) {
                drawableLiaison = getResources().getIdentifier("su_seal", "drawable", "su.android.libraryapp");
            }
            drawableImage= getResources().getIdentifier(image + "_large", "drawable", "su.android.libraryapp");
            if(drawableImage == 0)
            {
                drawableImage= getResources().getIdentifier("general", "drawable", "su.android.libraryapp");
            }
            subjectName = (String) subject.get("name");
            school = (String) subject.get("school");
            databases1 = (JSONArray) subject.get("databases");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Titles   titles = getResources().getStringArray(R.array.resp);
        try {
            assert liaison != null;
            JSONObject contact = (JSONObject) contactsObject.get(liaison);
            subjectInfo[0] = (String) contact.get("name");
            subjectInfo[1] = "";
            subjectInfo[2] = (String) contact.get("phone");
            subjectInfo[3] = (String) contact.get("email");
            subjectInfo[4] = (String) contact.get("office");
            subjectInfo[5] = subjectName + " Subject Guide";
            subjectInfo[6] = "All " + subjectName + " Research Guides";

            if (!subject.getString("url").contains("http")) {
                subjectInfo[7] = "";
            }
            else {
                subjectInfo[7] = (String) subject.get("url");
                num_research_guides++;
            }
            if (!subject.getString("subjectURL").contains("http")) {
                subjectInfo[8] = "";
            }
            else {
                subjectInfo[8] = (String) subject.get("subjectURL");
                num_research_guides++;
            }
            int space = 0;
            for(int i = 0; i < subjectInfo[0].length(); i++) {
                if(subjectInfo[0].charAt(i) == ' ') {
                    space = i;
                }
            }
            subjectInfo[9] = subjectInfo[0].substring(0, space) + subjectInfo[0].charAt(space+1);
            subjectInfo[9] = subjectInfo[9].toLowerCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Icon
        icon.setImageResource(drawableImage);
        int color = getResources().getIdentifier("school_"+school, "color", "su.android.libraryapp");
        icon.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.custom_circle));
        icon.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), color));
        // Rectangle
        rectangle.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.custom_rectangle));
        rectangle.setBackgroundTintList(ContextCompat.getColorStateList(getContext(), color));
        // Title
        title.setText(subjectName);
        // Databases
        databases = new Integer[]{0,1,2,3};

        //Staff Pic
        staff_pic.setImageResource(drawableLiaison);

        populateListView(sectionHeader, subjectInfo);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    public void onPause(){
        super.onPause();
    }

    //*********************************************************************************
    // Runnable function
    private class JSONRetriever implements Runnable {

        @Override
        public void run() {
            getChatStatus();
        }
    }

    // Runnable function
    private class postRetriever implements Runnable{

        @Override
        public void run() {
            checkAvailability();
            assignConnected();
        }
    }

    // Opens chat
    public void getChatStatus(){
        try {
            System.out.println("GETTING CHAT STATUS");
            full_string = "";
            String[] url_parts = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.url_for_subject_chat);
            base_url = url_parts[0].concat(subjectInfo[9]).concat(url_parts[1]);
            System.out.println("HERE" + base_url);
            Log.d("ERROR", base_url);
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
                        while ((inputLine = br.readLine()) != null) { // While there are more contents to read
                            connected = true;
                            response.append(inputLine); // Append the new data to all grabbed data
                        }
                        br.close(); // Close connection
                    } catch (IOException ignored) {
                        System.out.println("CATCH1");
                    }
                } catch (IOException ignored) {
                    System.out.println("CATCH2");
                }
            } catch (MalformedURLException ignored) {
                System.out.println("CATCH3");
            }
            full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
        } catch (Exception ignored) {
            System.out.println("CATCH4");
        }
        System.out.println("GOT CHAT STATUS");
    }
    //*********************************************************************************

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // Fills the listview with subject information
    public void populateListView(String[] sectionHeader, String[] subjectInfo) throws JSONException {
        int position = 0;  //current position in each item array
        lix = new ListviewX(Objects.requireNonNull(getActivity())); // Creates a new listview
        listItems = new ArrayList<>(); // Creates a new ArrayList to hold the list items
        listViewsrr = view.findViewById(R.id.subject_list); // Assigns listview
        listViewsrr.setOnItemClickListener(this); // Sets the list to react on this fragment in onItemClick method

        ListItem0 li = new ListItem0(getActivity(), subjectInfo[0].toUpperCase()); // Sets name of the librarian
        listItems.add(styleLikeHeader(li)); // Adds to list
        position++; // Ups current position in list
        for(int j = 0; j < 4; j++) {
            listItems.add(new ListItem3(getActivity(),sectionTitles[j],subjectInfo[j+1])); // Adds item to the list with a subtitle dependant on the staff
            position++; // Ups current position in list
        }

        ListItem0 li2 = new ListItem0(getActivity(), sectionHeader[1].toUpperCase()); // Sets resources header
        listItems.add(styleLikeHeader(li2)); // Adds to list
        for(int j = 0; j < num_research_guides; j++) {
            if(subjectInfo[position].compareTo("") != 0) { // If the item isn't empty
                ListItem0 resItem = new ListItem0(getActivity(), subjectInfo[position]);
                resItem.seeArrow();
                listItems.add(resItem); // Add to list
            }
            position++; // Ups current position in list
        }

        if(databases.length != 0) {
            ListItem0 li3 = new ListItem0(getActivity(), sectionHeader[2].toUpperCase()); // Sets database header
            listItems.add(styleLikeHeader(li3)); // Adds to the list
            position++; // Ups current position in list

            for(int i = 0; i < databases1.length(); i++)
            {
                JSONObject database = (JSONObject) databaseObject.get((String) databases1.get(i));
                ListItem0 dataItem = new ListItem0(getActivity(), (String) database.get("name"));
                dataItem.seeArrow();
                listItems.add(dataItem); // Adds databases
                position++; // Ups current position in list

            }
        }
        System.out.println(listItems);
        lix.populate(listItems); // Links ArrayList to resource
        listViewsrr.setAdapter(lix); // Sets adapter
    }

    // Checks the chat status for availability
    private void checkAvailability(){
        chat_status = (ListItem3)lix.getItem(1); // Grabs the chat item
        chat_status.getTextView2().setText(R.string.checking); // Sets init value to
        availability = ""; // Assigns availability to nothing
//        jretr.execute();//RUN ASYNC TO GET JSON
    }

    // Updates chat status for employee
    public void assignConnected(){
        if(full_string.compareTo("available") == 0){
            availability = "Available for chat!";
            TextView tv = chat_status.getTextView2();
            tv.setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_green, null)); // Sets text green
            chat_status.getTextView2().setText(availability); // sets the text
            chattable = true;
        }
        else if(full_string.compareTo("unavailable") == 0){
            availability = "Offline";
            chat_status.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_grey, null)); // Sets text grey
            chat_status.getTextView2().setText(availability); // sets the text
        }
        else{
            availability = "Offline";
            chat_status.getTextView2().setText(availability); // sets the text
        }


    }

    private ListItem0 styleLikeHeader(ListItem0 li){
        li.getTextView().setTextAppearance(R.style.listHeader); // Looks like a header
        li.getTextView().setPaintFlags(li.getTextView().getPaintFlags()); // Removed Underlines
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null)); // Sets background color to the standard
        return li;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("position = " + position);
        System.out.println("id = " + id);
        System.out.println("num_research_guides = " + num_research_guides);
        if (position > 0 && position < 5){ // If in the librarian info
            switch (position) {
                case 1:
                    // CHAT
                    if(chattable){
                        String str = "https://libraryh3lp.com/chat/".concat(subjectInfo[9]).concat("@libraryh3lp.com?skin=22280&identity=").concat(subjectInfo[9]);
                        String key_str = subjectInfo[9].concat("_chat"); // Sets key
                        if (!(MainActivity.chat_webs.containsKey(key_str))) // If chat exists in map already
                            MainActivity.chat_webs.put(key_str, new ChatWebViewFragment(str)); // Adds the new ChatWebView to map
                        FragmentTransaction ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, Objects.requireNonNull(MainActivity.chat_webs.get(key_str))); // Replace the page
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage); // Adds page to back
                    }
                    break;
                case 2:
                    // PHONE
                    Intent dialer = new Intent(Intent.ACTION_DIAL); // Creates a new phone intent
                    dialer.setData(Uri.parse("tel:" + subjectInfo[2])); // Passes URI to intent
                    startActivity(dialer); // Starts activity
                    break;
                case 3:
                    // EMAIL
                    Intent emailer = new Intent(Intent.ACTION_SEND);
                    emailer.setType("message/rfc822");
                    emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    emailer.setType("vnd.android.cursor.item/email");
                    emailer.putExtra(Intent.EXTRA_EMAIL, new String[]{subjectInfo[3]});
                    try {
                        startActivity(Intent.createChooser(emailer, "Send mail..."));
                    } catch (ActivityNotFoundException ex) {
                        Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
        else if (position > 5){
            String database_url = null;
            webViewFragment webview;
            FragmentTransaction ft;
            switch(num_research_guides){
                case 0:
                    try {
                        JSONObject obj = (JSONObject) databaseObject.get((String) databases1.get(position-6));
                        database_url = obj.getString("url");
                        System.out.println(database_url);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    database_url = database_urls[databases[position - 6]];
                    webview = new webViewFragment(database_url, "Research Guide");
                    ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_container, webview);
                    ft.addToBackStack(null).commit();
                    MainActivity.pageStack.push(MainActivity.researchPage);
                    break;
                case 1:
                    if(position == 6){
                        String research_url = "";
                        if(subjectInfo[7].compareTo("") == 0)
                            research_url = subjectInfo[8];
                        else if(subjectInfo[8].compareTo("") == 0)
                            research_url = subjectInfo[7];
                        webview = new webViewFragment(research_url, "Research Guide");
                        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    else if(position > 7){
                        try {
                            JSONObject obj = (JSONObject) databaseObject.get((String) databases1.get(position-8));
                            database_url = obj.getString("url");
                            System.out.println(database_url);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        database_url = database_urls[databases[position - 8]];
                        webview = new webViewFragment(database_url, "Database");
                        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    break;
                case 2:
                    if(position == 6){
                        String research_url = subjectInfo[7];
                        webview = new webViewFragment(research_url, "Research Guide");
                        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    else if(position == 7){
                        String research_url = subjectInfo[8];
                        webview = new webViewFragment(research_url, "Research Guide");
                        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    else if(position > 8){
                        try {
                            JSONObject obj = (JSONObject) databaseObject.get((String) databases1.get(position-9));
                            database_url = obj.getString("url");
                            System.out.println(database_url);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
//                        database_url = database_urls[databases[position - 9]];
                        webview = new webViewFragment(database_url, "Database");
                        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    break;
            }
        }
    }
}
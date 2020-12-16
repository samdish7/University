package thelibrarians2.sulibraryapp;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SubjectDetailedFragment extends Fragment implements AdapterView.OnItemClickListener{

    static int tab;
    View view;
    ImageView icon, rectangle, staff_pic;
    TextView title;
    String[] sectionHeader,titles,database_names,database_urls;
    DrawerToggleListener toggleListener;
    Integer[] databases;
    ListView listViewsrr;
    ListviewX lix;
    ArrayList<ListItem> listItems;
    String availability, full_string, base_url;
    HttpURLConnection conn;
    JSONRetriever jretr;
    ListItem3 chat_status;
    boolean connected, chattable;
    int num_research_guides;
    ActionBar toolbar;
    String[] sectionTitles;

    /*
        DEFAULT CONSTRUCTOR
     */
    public SubjectDetailedFragment() {
        chattable =false;
    }

    /*
        CONSTRUCTOR w/ POSITION
     */
    public SubjectDetailedFragment(int pos){
        tab = pos;
        chattable = false;
    }

    /*
        CREATES THE VIEW
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
            Grabs necessary resources from resources
         */
        view = inflater.inflate(R.layout.subject_detailed, container, false); // Grabs whole view
        icon = (ImageView) view.findViewById(R.id.acc_icon);  //icon for the subject
        rectangle = (ImageView) view.findViewById(R.id.acc_header);  //rectangle portion
        title = (TextView) view.findViewById(R.id.acc_title);  //text displaying name of subject
        staff_pic = (ImageView) view.findViewById(R.id.staff_icon); // image for staff picture
        databases = new Integer[]{}; // Initializes empty Integer array
        database_names = getResources().getStringArray(R.array.database_name); // Grabs array of database names
        database_urls = getResources().getStringArray(R.array.database_url); // Grabs array of database urls
        sectionHeader = getResources().getStringArray(R.array.subject_headers); // Grabs array of subject headers
        sectionTitles = getResources().getStringArray(R.array.subject_detailed_constant); // Grabs the item text shared by all pages


        /*
            Sets the details displayed on the page and grabs the necessary info from strings.xml
         */
        num_research_guides = 2;
            switch (tab) {
                //Accounting & Legal Studies
                case 0:
                    //Referencing the XML that will create the overall header for the page
                    icon.setImageResource(R.drawable.accounting);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Accounting & Legal Studies");
                    titles = getResources().getStringArray(R.array.acct);
                    databases= new Integer[]{0,1,2,3};
                    staff_pic.setImageResource(R.drawable.rtvickery);
                    break;
                //Anthropology
                case 1:
                    titles = getResources().getStringArray(R.array.anthro);
                    icon.setImageResource(R.drawable.anthropology);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Anthropology");
                    titles = getResources().getStringArray(R.array.anthro);
                    databases=new Integer[]{4,5,6};
                    staff_pic.setImageResource(R.drawable.jlparrigin);
                    break;
                //Applied Health Physiology
                case 2:
                    titles = getResources().getStringArray(R.array.ahp);
                    icon.setImageResource(R.drawable.ahp);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Applied Health & Physiology");
                    databases=new Integer[]{7,8,9,10,11,12,13,14};
                    staff_pic.setImageResource(R.drawable.mxchakraborty);
                    break;
                //Art & Art History
                case 3:
                    titles = getResources().getStringArray(R.array.art);
                    icon.setImageResource(R.drawable.art);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Art & Art History");
                    databases = new Integer[]{15,16,17};
                    staff_pic.setImageResource(R.drawable.cmeckardt);
                    break;
                //Biology
                case 4:
                    titles = getResources().getStringArray(R.array.bio);
                    icon.setImageResource(R.drawable.biology);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Biology");
                    databases = new Integer[]{19,20,21,4,14,15};
                    staff_pic.setImageResource(R.drawable.sebrazer);
                    break;
                //Business
                case 5:
                    titles = getResources().getStringArray(R.array.bus);
                    icon.setImageResource(R.drawable.business);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Business");
                    databases = new Integer[]{0, 22,1, 2, 23};
                    staff_pic.setImageResource(R.drawable.rtvickery);
                    break;
                //Chemistry
                case 6:
                    icon.setImageResource(R.drawable.chemistry);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Chemistry");
                    titles = getResources().getStringArray(R.array.chem);
                    databases = new Integer[]{24,21,14,25,15};
                    staff_pic.setImageResource(R.drawable.sebrazer);
                    break;
                //Communication Arts
                case 7:
                    titles = getResources().getStringArray(R.array.comm);
                    icon.setImageResource(R.drawable.comm);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Communication Arts");
                    databases = new Integer[]{26,27,4,6};
                    staff_pic.setImageResource(R.drawable.jlparrigin);
                    break;
                //Computer Science
                case 8:
                    titles = getResources().getStringArray(R.array.comp);
                    icon.setImageResource(R.drawable.compsci);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Computer Science");
                    databases = new Integer[]{4,14,15};
                    staff_pic.setImageResource(R.drawable.genericperson);
                    break;
                //Conflict Analysis & Dispute Resolution
                case 9:
                    titles = getResources().getStringArray(R.array.cadr);
                    icon.setImageResource(R.drawable.cadr);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Conflict Analysis & Dispute Resolution");
                    databases = new Integer[]{7, 26,4,28};
                    staff_pic.setImageResource(R.drawable.mxchakraborty);
                    break;
                //Dance
                case 10:
                    titles = getResources().getStringArray(R.array.dance);
                    icon.setImageResource(R.drawable.dance);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Dance");
                    databases = new Integer[]{7,29,4};
                    staff_pic.setImageResource(R.drawable.arprichard);
                    break;
                //Economics & Finance
                case 11:
                    titles = getResources().getStringArray(R.array.econ);
                    icon.setImageResource(R.drawable.economy);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Economics & Finance");
                    databases = new Integer[]{0,1, 30,23, 31};
                    staff_pic.setImageResource(R.drawable.rtvickery);
                    break;
                //Education
                case 12:
                    titles = getResources().getStringArray(R.array.edu);
                    icon.setImageResource(R.drawable.education);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Education");
                    databases = new Integer[]{32,33,34,35,36,37};
                    staff_pic.setImageResource(R.drawable.saford);
                    break;
                //Engineering
                case 13:
                    titles = getResources().getStringArray(R.array.engin);
                    icon.setImageResource(R.drawable.engineering);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Engineering");
                    databases = new Integer[]{7,38,4,14,15};
                    staff_pic.setImageResource(R.drawable.genericperson);
                    break;
                //English
                case 14:
                    titles = getResources().getStringArray(R.array.engl);
                    icon.setImageResource(R.drawable.english);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("English");
                    databases = new Integer[]{};
                    staff_pic.setImageResource(R.drawable.jlparrigin);
                    break;
                //English Language Institute
                case 15:
                    titles = getResources().getStringArray(R.array.eli);
                    icon.setImageResource(R.drawable.eli);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("English Language Institute");
                    databases = new Integer[]{29, 4, 39, 40, 41};
                    staff_pic.setImageResource(R.drawable.cmeckardt);
                    break;
                //Environmental Studies
                case 16:
                    titles = getResources().getStringArray(R.array.env);
                    icon.setImageResource(R.drawable.environ);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Environmental Studies");
                    databases = new Integer[]{19,38, 42, 21, 4, 14,43,15};
                    staff_pic.setImageResource(R.drawable.sebrazer);
                    break;
                //Geography & Geosciences
                case 17:
                    titles = getResources().getStringArray(R.array.geog);
                    icon.setImageResource(R.drawable.geog);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Geography & Geosciences");
                    databases = new Integer[]{7,4,14,15};
                    staff_pic.setImageResource(R.drawable.sebrazer);
                    break;
                //Government Information
                case 18:
                    titles = getResources().getStringArray(R.array.govt);
                    icon.setImageResource(R.drawable.govt);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Government Information");
                    databases = new Integer[]{};
                    staff_pic.setImageResource(R.drawable.ggrobb);
                    break;
                //Health & Sport Sciences
                case 19:
                    titles = getResources().getStringArray(R.array.hss);
                    icon.setImageResource(R.drawable.hss);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Health & Sport Sciences");
                    staff_pic.setImageResource(R.drawable.cmeckardt);
                    databases = new Integer[]{7, 8, 32,33,44,4,45, 11, 46, 14};
                    break;
                //History
                case 20:
                    titles = getResources().getStringArray(R.array.hist);
                    icon.setImageResource(R.drawable.history);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("History");
                    databases = new Integer[]{7, 47, 29, 4};
                    staff_pic.setImageResource(R.drawable.genericperson);
                    break;
                //Information & Decision Sciences
                case 21:
                    titles = getResources().getStringArray(R.array.info);
                    icon.setImageResource(R.drawable.ids);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Information & Decision Sciences");
                    databases = new Integer[]{0,1,14,15};
                    staff_pic.setImageResource(R.drawable.rtvickery);
                    break;
                //Interdisciplinary Studies
                case 22:
                    titles = getResources().getStringArray(R.array.inter);
                    icon.setImageResource(R.drawable.inter);
                    rectangle.setImageResource(R.drawable.custom_rectangle_red);
                    title.setText("Interdisciplinary Studies");
                    titles = getResources().getStringArray(R.array.inter);
                    databases = new Integer[]{7,4,48};
                    num_research_guides = 1;
                    staff_pic.setImageResource(R.drawable.cmeckardt);
                    break;
                //Management & Marketing
                case 23:
                    titles = getResources().getStringArray(R.array.mgmt);
                    icon.setImageResource(R.drawable.mgmt);
                    rectangle.setImageResource(R.drawable.custom_rectangle_yellow);
                    title.setText("Management & Marketing");
                    databases=new Integer[]{0,22,1,2};
                    staff_pic.setImageResource(R.drawable.rtvickery);
                    break;
                //Mathematics
                case 24:
                    titles = getResources().getStringArray(R.array.math);
                    icon.setImageResource(R.drawable.math);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Mathematics");
                    databases = new Integer[]{14,15};
                    staff_pic.setImageResource(R.drawable.genericperson);
                    break;
                //Medical Laboratory Science
                case 25:
                    titles = getResources().getStringArray(R.array.med);
                    icon.setImageResource(R.drawable.mls);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Medical Laboratory Science");
                    databases = new Integer[]{8,9,10,11,12,15};
                    staff_pic.setImageResource(R.drawable.mxchakraborty);
                    break;
                //Military Science
                case 26:
                    titles = getResources().getStringArray(R.array.mil);
                    icon.setImageResource(R.drawable.mil);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Military Science");
                    databases = new Integer[]{7,49,4,2,48};
                    staff_pic.setImageResource(R.drawable.ggrobb);
                    break;
                //Modern Languages
                case 27:
                    titles = getResources().getStringArray(R.array.modl);
                    icon.setImageResource(R.drawable.modlang);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Modern Languages");
                    databases = new Integer[]{7, 50, 27,4,40};
                    staff_pic.setImageResource(R.drawable.ggrobb);
                    break;
                //Music
                case 28:
                    titles = getResources().getStringArray(R.array.music);
                    icon.setImageResource(R.drawable.music);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Music");
                    databases = new Integer[]{51, 52,4,53};
                    staff_pic.setImageResource(R.drawable.arprichard);
                    break;
                //Nursing
                case 29:
                    titles = getResources().getStringArray(R.array.nurse);
                    icon.setImageResource(R.drawable.nursing);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Nursing");
                    databases = new Integer[]{8,54, 44,9,10,11,12};
                    staff_pic.setImageResource(R.drawable.mxchakraborty);
                    break;
                //Philosophy
                case 30:
                    titles = getResources().getStringArray(R.array.phil);
                    icon.setImageResource(R.drawable.philosophy);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Philosophy");
                    databases = new Integer[]{7,4,55,56};
                    staff_pic.setImageResource(R.drawable.genericperson);
                    break;
                //Physical Education
                case 31:
                    titles = getResources().getStringArray(R.array.physed);
                    icon.setImageResource(R.drawable.physed);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    title.setText("Physical Education");
                    databases = new Integer[]{7,32,4,45,11};
                    num_research_guides = 1;
                    staff_pic.setImageResource(R.drawable.ggrobb);
                    break;
                //Physics
                case 32:
                    titles = getResources().getStringArray(R.array.phys);
                    icon.setImageResource(R.drawable.physics);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Physics");
                    databases = new Integer[]{7,4,14,15};
                    staff_pic.setImageResource(R.drawable.genericperson);
                    break;
                //Political Science
                case 33:
                    titles = getResources().getStringArray(R.array.polit);
                    icon.setImageResource(R.drawable.polisci);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Political Science");
                    databases = new Integer[]{7,4,2};
                    staff_pic.setImageResource(R.drawable.arprichard);
                    break;
                //Psychology
                case 34:
                    titles = getResources().getStringArray(R.array.psych);
                    icon.setImageResource(R.drawable.psychology);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Psychology");
                    databases = new Integer[]{28,46};
                    staff_pic.setImageResource(R.drawable.arprichard);
                    break;
                //Respiratory Therapy
                case 35:
                    titles = getResources().getStringArray(R.array.resp);
                    icon.setImageResource(R.drawable.resp);
                    rectangle.setImageResource(R.drawable.custom_rectangle_green);
                    title.setText("Respiratory Therapy");
                    databases = new Integer[]{7,8,54,44,9,10,11,12};
                    staff_pic.setImageResource(R.drawable.mxchakraborty);
                    break;
                //Social Work
                case 36:
                    titles = getResources().getStringArray(R.array.soc);
                    icon.setImageResource(R.drawable.socialwork);
                    rectangle.setImageResource(R.drawable.custom_rectangle_purple);
                    databases = new Integer[]{7,57,28,5,46,6,58,15};
                    title.setText("Social Work");
                    staff_pic.setImageResource(R.drawable.mxchakraborty);
                    break;
                //Sociology
                case 37:
                    titles = getResources().getStringArray(R.array.socio);
                    icon.setImageResource(R.drawable.sociology);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Sociology");
                    databases = new Integer[]{7,4,6};
                    staff_pic.setImageResource(R.drawable.arprichard);
                    break;
                //Theatre
                case 38:
                    titles = getResources().getStringArray(R.array.thea);
                    icon.setImageResource(R.drawable.theatre);
                    rectangle.setImageResource(R.drawable.custom_rectangle_blue);
                    title.setText("Theatre");
                    databases = new Integer[]{7,29,4};
                    staff_pic.setImageResource(R.drawable.arprichard);
                    break;
        }

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if(toolbar != null)
            toolbar.setTitle(R.string.research);

        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);
        populateListView(sectionHeader, titles);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        jretr = new JSONRetriever();
        checkAvailability();
    }

    public void onPause(){
        super.onPause();
        jretr.cancel(true);
    }

    private class JSONRetriever extends AsyncTask<Void, Void, Void> {

            /*
            * THIS STARTS WHEN JSONRetriever.execute() IS CALLED
            *
            * THIS IS STRICTLY FOR GRABBING THE STRING. DO NOT ATTEMPT TO
            * CALL ANY PARENT CLASS METHODS OR CHANGE ANY UI ELEMENTS IN
            * THIS METHOD. IT WILL FAIL AND YOU WILL BE SAD. I'M SORRY.
            * */

        @Override
        protected Void doInBackground(Void... params) {
            try {
                full_string = "";
                String[] url_parts = getActivity().getResources().getStringArray(R.array.url_for_subject_chat);
                base_url = url_parts[0].concat(titles[9]).concat(url_parts[1]);
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
                        } catch (IOException e) {
                        }
                    } catch (IOException e) {
                    }
                } catch (MalformedURLException e) {
                }
                full_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {
            }
            return null;
        }

            /*
            * THIS STARTS ONCE doInBackground(...) COMPLETES
            *
            * THIS CONTINUES ON THE MAIN THREAD (UI ELEMENTS CAN BE CHANGED)
            * */

        protected void onPostExecute(Void v){
            assignConnected();
        }

        protected void onCancelled(){}
    }

    /*
        DESTROYS VIEW
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
    }

    /*
        POPULATES THE LISTVIEW BELOW THE HEADER
     */
    public void populateListView(String[] sectionHeader, String[] titles) {
        int position = 0;  //current position in each item array
        lix = new ListviewX(getActivity()); // Creates a new listview
        listItems = new ArrayList<ListItem>(); // Creates a new ArrayList to hold the list items
        listViewsrr = (ListView) view.findViewById(R.id.subject_list); // Assigns listview
        listViewsrr.setOnItemClickListener(this); // Sets the list to react on this fragment in onItemClick method
        for(int i=0; i<sectionHeader.length; i++){ // Runs as many times as there are section headers
            switch(i) {
                case 0:
                    ListItem0 li = new ListItem0(getActivity(), titles[i].toUpperCase()); // Sets name of the librarian
                    listItems.add(styleLikeHeader(li)); // Adds to list
                    position++; // Ups current position in list
                    for(int j = 0; j < 4; j++) {
                        listItems.add(new ListItem3(getActivity(),sectionTitles[j],titles[j+1])); // Adds item to the list with a subtitle dependant on the staff
                        position++; // Ups current position in list
                    }
                    break;
                case 1:
                    ListItem0 li2 = new ListItem0(getActivity(), sectionHeader[i].toUpperCase()); // Sets resources header
                    listItems.add(styleLikeHeader(li2)); // Adds to list
                    for(int j = 0; j < 2; j++) {
                        if(titles[position].compareTo("") != 0) { // If the item isn't empty
                            listItems.add(new ListItem0(getActivity(), titles[position])); // Add to list
                        }
                        position++; // Ups current position in list
                    }
                    break;
                case 2:
                    if(databases.length != 0) {
                        ListItem0 li3 = new ListItem0(getActivity(), sectionHeader[i].toUpperCase()); // Sets database header
                        listItems.add(styleLikeHeader(li3)); // Adds to the list
                        position++; // Ups current position in list
                        for (int j = 0; j < databases.length; j++) {
                            listItems.add(new ListItem0(getActivity(), database_names[databases[j]])); // Adds databases
                            position++; // Ups current position in list
                        }
                    }
                    break;
            }
        }
        lix.populate(listItems); // Links ArrayList to resource
        listViewsrr.setAdapter(lix); // Sets adapter

    }

    /*
        CHECKS AVAILABILITY FOR THE CHAT FEATURE
     */
    private void checkAvailability(){
        chat_status = (ListItem3)lix.getItem(1); // Grabs the chat item
        chat_status.getTextView2().setText("Checking..."); // Sets init value to
        availability = ""; // Assigns availability to nothing
        jretr.execute();//RUN ASYNC TO GET JSON
    }

    /*
        SETS VISIBLE TEXT TO APPROPRIATE CHAT STATUS
     */
    public void assignConnected(){
        /*
            Sets text and chattable boolean
         */
        if(full_string.compareTo("available") == 0){
            availability = "Available for chat!";
            chat_status.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_green, null)); // Sets text green
            chattable = true;
        }
        else if(full_string.compareTo("unavailable") == 0){
            availability = "Unavailable for chat!";
            chat_status.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.color_red, null)); // Sets text red
        }
        else{
            availability = "Chat is unreachable...";
        }

        if(chat_status != null) // if chat_status has been created properly
            chat_status.getTextView2().setText(availability); // sets the text
    }

    private ListItem0 styleLikeHeader(ListItem0 li){
        li.getTextView().setTextAppearance(getActivity(), R.style.listHeader); // Looks like a header
        li.getTextView().setPaintFlags(li.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG); // Underlines
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null)); // Sets background color to the standard
        return li;
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position > 0 && position < 5){ // If in the librarian info
            switch (position) {
                case 1:
                    // CHAT
                    if(chattable){
                        String str = "https://libraryh3lp.com/chat/".concat(titles[9]).concat("@libraryh3lp.com?skin=22280&identity=").concat(titles[9]);
                        String key_str = titles[9].concat("_chat"); // Sets key
                        if (!(MainActivity.chat_webs.containsKey(key_str))) // If chat exists in map already
                            MainActivity.chat_webs.put(key_str, new ChatWebViewFragment(str)); // Adds the new chatwebview to map
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, MainActivity.chat_webs.get(key_str)); // Replace the page
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage); // Adds page to back
                    }
                    break;
                case 2:
                    // PHONE
                    Intent dialer = new Intent(Intent.ACTION_DIAL); // Creates a new phone intent
                    dialer.setData(Uri.parse("tel:" + titles[2])); // Passes URI to intent
                    startActivity(dialer); // Starts activity
                    break;
                case 3:
                    // EMAIL
                    Intent emailer = new Intent(Intent.ACTION_SEND);
                    emailer.setType("message/rfc822");
                    emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    emailer.setType("vnd.android.cursor.item/email");
                    emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{titles[3]});
                    try {
                        startActivity(Intent.createChooser(emailer, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getActivity(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
        else if (position > 5){
            String database_url;
            webViewFragment webview;
            FragmentTransaction ft;
            switch(num_research_guides){
                case 0:
                    database_url = database_urls[databases[position - 6]];
                    webview = new webViewFragment(database_url, "Research Guide");
                    ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.content_container, webview);
                    ft.addToBackStack(null).commit();
                    MainActivity.pageStack.push(MainActivity.researchPage);
                    break;
                case 1:
                    if(position == 6){
                        String research_url = "";
                        if(titles[7].compareTo("") == 0)
                            research_url = titles[8];
                        else if(titles[8].compareTo("") == 0)
                            research_url = titles[7];
                        webview = new webViewFragment(research_url, "Research Guide");
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    else if(position > 7){
                        database_url = database_urls[databases[position - 8]];
                        webview = new webViewFragment(database_url, "Database");
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    break;
                case 2:
                    if(position == 6){
                        String research_url = titles[7];
                        webview = new webViewFragment(research_url, "Research Guide");
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    else if(position == 7){
                        String research_url = titles[8];
                        webview = new webViewFragment(research_url, "Research Guide");
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    else if(position > 8){
                        database_url = database_urls[databases[position - 9]];
                        webview = new webViewFragment(database_url, "Database");
                        ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, webview);
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.researchPage);
                    }
                    break;
            }
        }
    }
}
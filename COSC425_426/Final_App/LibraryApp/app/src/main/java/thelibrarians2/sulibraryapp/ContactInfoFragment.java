package thelibrarians2.sulibraryapp;

/*
    Uses ListItem0 for the Headers
    Uses ListItem2 for the chat, phone, and email items
    Uses ContactListItem for the staff members contact information
 */

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ContactInfoFragment extends Fragment implements AdapterView.OnItemClickListener {

    View view;
    int[] icons;
    ListviewX lix;
    String[] strings;
    boolean isConnected;
    int index, value, iconStart;
    ActionBar toolbar;
    ListView listViewct;
    static int position;
    HttpURLConnection conn;
    JSONObject contacts, order;
    String contact_url, order_url;
    ArrayList<ListItem> listItems;
    Thread retrieverThread, postThread;
    ArrayList<Boolean> chat_avail_bools;
    ArrayList<String> chat_avail_grab, chat_avail_urls;
    static boolean connected_makerlabs, connected_resources;

    private class JSONRetriever implements Runnable {

        @Override
        public void run() {
            try {
                contacts = getContacts(contact_url);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            try {
                order = getContactsOrder(order_url);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            getChatStatus();
        }
    }

    private class postRetriever implements Runnable {

        @Override
        public void run() {

            if (icons.length <= iconStart) {
                fillIcons();
                if (icons.length <= iconStart) {
                    fillIcons();
                }

                try {
                    chatChange();
                    fillList();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.fragment_contacts, container, false);

        // Set toolbar title.
        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.contact_info));

        // Initialize Variables.
        chat_avail_grab = new ArrayList<>();
        chat_avail_bools = new ArrayList<>();
        icons = new int[]{R.drawable.unknown, R.drawable.unknown, R.drawable.unknown,
                R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call, R.drawable.phone_call,
                R.drawable.contact_color, R.drawable.contact_color, R.drawable.contact_color, R.drawable.contact_color,
                R.drawable.contact_color, R.drawable.contact_color};

        // If there is no internet connection, inflate the fragment connection error fragment.
        ConnectivityManager cm =
                (ConnectivityManager) Objects.requireNonNull(getContext()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            return inflater.inflate(R.layout.fragment_connection_error, container, false);
        } else {
            strings = getResources().getStringArray(R.array.list_strings);  // This has all the info for each person

            order_url = getResources().getString(R.string.order_url);
            contact_url = getResources().getString(R.string.contact_url);

            iconStart = icons.length;

            // ****************************************************************

            chat_avail_urls = new ArrayList<>();
            chat_avail_urls.add("http://libraryh3lp.com/presence/jid/su-crc/chat.libraryh3lp.com/text");
            chat_avail_urls.add("http://libraryh3lp.com/presence/jid/makerlab/chat.libraryh3lp.com/text");
            chat_avail_urls.add("http://libraryh3lp.com/presence/jid/su-allstaff/chat.libraryh3lp.com/text");

            lix = new ListviewX(Objects.requireNonNull(getActivity()));
            listItems = new ArrayList<>();
            listViewct = view.findViewById(R.id.listView);
            listViewct.setVisibility(View.INVISIBLE);
            // ****************************************************************

            retrieverThread = new Thread(new JSONRetriever());
            postThread = new Thread(new postRetriever());

            try {
                retrieverThread.start();
                retrieverThread.join();
                postThread.start();
                postThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return view;
        }
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    // Fills in the icons array based on the staff members to be displayed, and the available drawables for those staff
    public void fillIcons() {
        icons = Arrays.copyOf(icons, icons.length + order.length() + 1);
        for(int i = 0; i < order.length(); i++) {
            String name = null;
            try {
                name = (String) order.get(String.valueOf(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            int id = getResources().getIdentifier(name, "drawable", "thelibrarians2.sulibraryapp");
            if(id > 0 ) {
                icons[(icons.length - order.length()) + i-1] = id;
            }
            else {
                icons[(icons.length - order.length()) + i-1] = R.drawable.su_seal;
            }
        }
    }

    // Pulls JSON file of current chat status
    public void getChatStatus() {
        try {
            URL url; // URL object
            StringBuilder response = new StringBuilder(); // Allows string appending
            String inputLine; // Buffer for inputStream
            try {
                for (int i = 0; i < chat_avail_urls.size(); i++) {
                    url = new URL(chat_avail_urls.get(i)); // url passed in
                    try {
                        conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException ignored) { }
                    } catch (IOException ignored) { }
                    chat_avail_grab.add(response.toString()); // Sets string in parent class to be the string taken from the URL
                    response = new StringBuilder();
                }
            } catch (MalformedURLException ignored) { }
        } catch (Exception ignored) { }
    }

    public void launchDialog(JSONObject person) throws JSONException {
        CallOrClickDialogFragment c1;//creates new fragment
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        Fragment prev;
        Bundle args;//passes position to fragment as an argument via Bundle; this determines which case within the sub fragment is executed
        c1 = new CallOrClickDialogFragment(Objects.requireNonNull(getActivity()), person);
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        assert getFragmentManager() != null;
        prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        args = new Bundle();
        c1.setArguments(args);
        c1.show(fragmentTransaction,"dialog");
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public void launchEmail(String address){
        Intent emailer;
        emailer = new Intent(Intent.ACTION_SEND);
        emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        emailer.setType("vnd.android.cursor.item/email");
        emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{address});
        startActivity(emailer);
    }

    public void launchPhone(String phoneNumber){
        Intent dialer;
        dialer = new Intent(Intent.ACTION_DIAL);
        dialer.setData(Uri.parse(phoneNumber));
        startActivity(dialer);
    }

    // So i tried to think of a way to figure out which strings were headers and which weren't.
    // Couldn't do it.
    // Anyways, this fills the list with every list item
    private void fillList() throws JSONException {

        int stringCount = 0;
        int iconCount = 0;
        int length = icons.length + 3; //four section headers
        for(int x = 0; x < length; x++) {
            switch(x) {
                case 0: //section headers
                case 4:
                case 9:
                case 16:
                    // This is for the headers
                    ListItem0 li0 = new ListItem0(Objects.requireNonNull(getActivity()), strings[stringCount++]);
                    li0.getTextView().setTextAppearance(R.style.listHeader);
                    li0.getTextView().setPaintFlags(li0.getTextView().getPaintFlags()); // removed Underline
                    li0.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
                    listItems.add(li0);
                    break;
                default:
                    if (x > 16) {
                        // This is for the staff members with their profile pictures and information
                        String username = (String) order.get(String.valueOf(x-17));
                        JSONObject person = (JSONObject) contacts.get(username);
                        ContactListItem cli = new ContactListItem(getActivity(), icons[iconCount++],person.getString("name"), person.getString("title"), person.getString("phone"), person.getString("email"));
                        cli.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.listSecondText, null));
                        listItems.add(cli);
                    }
                    else{
                        // This is for evey other input like chat, phone, and email
                        ListItem2 li2 = new ListItem2(getActivity(), icons[iconCount++], strings[stringCount++], strings[stringCount++]);
                        li2.getTextView2().setTextColor(ResourcesCompat.getColor(getResources(), R.color.listSecondText, null));
                        listItems.add(li2);
                    }
            }
        }

        lix.populate(listItems);
        listViewct.setVisibility(View.VISIBLE);
        listViewct.setAdapter(lix);

        listViewct.setOnItemClickListener(this);

    }

    // Changes the chat availability based on getChatStatus()
    public void chatChange() {
        for (int i = 0; i < chat_avail_grab.size(); i++) {
            if (isNetworkAvailable()) {
                switch (i) {
                    case 0:
                        if (chat_avail_grab.get(i).compareTo("unavailable") == 0 && !ChatFragment.connected) {
                            chat_avail_bools.add(Boolean.FALSE);
                            icons[i] = R.drawable.unavailable;
                            strings[(i+1)*2] = "Chat is currently unavailable.";
                        } else if (chat_avail_grab.get(i).compareTo("available") == 0 && !ChatFragment.connected) {
                            chat_avail_bools.add(Boolean.TRUE);
                            icons[i] = R.drawable.available;
                            strings[(i+1)*2] = "Chat is available! Tap to chat with library staff.";
                        } else if (ChatFragment.connected) {
                            chat_avail_bools.add(Boolean.TRUE);
                            icons[i] = R.drawable.available;
                            strings[(i+1)*2] = "Chat is available! Tap to chat with library staff.";
                        }
                        break;
                    case 1:
                        if (chat_avail_grab.get(i).compareTo("unavailable") == 0 && !connected_makerlabs) {
                            icons[i] = R.drawable.unavailable;
                            chat_avail_bools.add(Boolean.FALSE);
                            strings[(i+1)*2] = "Chat is currently unavailable.";
                        } else if (chat_avail_grab.get(i).compareTo("available") == 0 && !connected_makerlabs) {
                            icons[i] = R.drawable.available;
                            chat_avail_bools.add(Boolean.TRUE);
                            strings[(i+1)*2] = "Chat is available! Tap to chat with library staff.";
                        } else if (connected_makerlabs) {
                            icons[i] = R.drawable.available;
                            chat_avail_bools.add(Boolean.TRUE);
                            strings[(i+1)*2] = "Chat is available! Tap to chat with library staff.";
                        }
                        break;
                    case 2:
                        if (chat_avail_grab.get(i).compareTo("unavailable") == 0 && !connected_resources) {
                            icons[i] = R.drawable.unavailable;
                            chat_avail_bools.add(Boolean.FALSE);
                            strings[(i+1)*2] = "Chat is currently unavailable.";
                        } else if (chat_avail_grab.get(i).compareTo("available") == 0 && !connected_resources) {
                            icons[i] = R.drawable.available;
                            chat_avail_bools.add(Boolean.TRUE);
                            strings[(i+1)*2] = "Chat is available! Tap to chat with library staff.";
                        } else if (connected_resources) {
                            icons[i] = R.drawable.available;
                            chat_avail_bools.add(Boolean.TRUE);
                            strings[(i+1)*2] = "Chat is available! Tap to chat with library staff.";
                        }
                        break;
                }
            }
            else {
                icons[index] = R.drawable.unavailable;
                chat_avail_bools.add(Boolean.FALSE);
                strings[value] = "Chat is currently unreachable.";
            }
        }
    }

    // Pulls JSON file of staff members information
    public JSONObject getContacts(String base_url) throws JSONException {
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
                    catch (IOException ignored) { }
                }
                catch (IOException ignored) { }
            }
            catch (MalformedURLException ignored) { }
            json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
        }
        catch (Exception ignored) { }
        return new JSONObject(json_string);
    }

    // Pulls JSON file of staff members username's and the order they should be displayed
    public JSONObject getContactsOrder(String base_url) throws JSONException {
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
                    catch (IOException ignored) { }
                }
                catch (IOException ignored) { }
            }
            catch (MalformedURLException ignored) { }
            json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
        }
        catch (Exception ignored) { }
        return new JSONObject (json_string);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ContactInfoFragment.position = position;

        if(isNetworkAvailable()) {
            //CAUTION: section headers count as positions
            //i.e. position 0 is section header 1
            FragmentTransaction ft;
            switch (position) {
                //Three live chats with different parts of the library
                case 1://CHAT 1 - General Staff
                    if(chat_avail_bools.get(0)) {
                        if (!(MainActivity.chat_webs.containsKey("library_chat")))
                            MainActivity.chat_webs.put("library_chat", new ChatWebViewFragment("https://us.libraryh3lp.com/mobile/su-allstaff@chat.libraryh3lp.com?skin=22280&identity=Librarian"));
                        ChatFragment.connected = true;
                        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, Objects.requireNonNull(MainActivity.chat_webs.get("library_chat")));
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.contactPage);
                    }
                    break;
                case 2://CHAT 2 - 3D Printer Lab
                    if(chat_avail_bools.get(1)) {
                        if (!(MainActivity.chat_webs.containsKey("makerlabs_chat")))
                            MainActivity.chat_webs.put("makerlabs_chat", new ChatWebViewFragment("https://us.libraryh3lp.com/chat/makerlab@chat.libraryh3lp.com?skin=22280&identity=Staff"));
                        connected_makerlabs = true;
                        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, Objects.requireNonNull(MainActivity.chat_webs.get("makerlabs_chat")));
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.contactPage);
                    }
                    break;
                case 3://CHAT 3 - Librarians
                    if(chat_avail_bools.get(2)) {
                        if (!(MainActivity.chat_webs.containsKey("resources_chat")))
                            MainActivity.chat_webs.put("resources", new ChatWebViewFragment("https://us.libraryh3lp.com/chat/su-crc@chat.libraryh3lp.com?skin=22280&identity=Librarian"));
                        connected_resources = true;
                        ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_container, Objects.requireNonNull(MainActivity.chat_webs.get("resources")));
                        ft.addToBackStack(null).commit();
                        MainActivity.pageStack.push(MainActivity.contactPage);
                    }
                    break;

                //Section for calling for Library Support
                case 5:// Research Help 410 548 5988
                    launchPhone("tel:4105485988");//calls launchPhone method
                    break;
                case 6:// Circulation 410 543 6130
                    launchPhone("tel:4105436130");
                    break;
                case 7:// Toll free 888 543 0148
                    launchPhone("tel:8885430148");
                    break;
                case 8:// App support 410 543 6306
                    launchPhone("tel:4105436306");
                    break;

                //Section for Emailing for Library Support
                case 10:// Research help
                    launchEmail("libraries@salisbury.edu");//calls launchEmail method
                    break;
                case 11:// Circulation
                    launchEmail("illoans@salisbury.edu");
                    break;
                case 12:// Interlibrary loan
                    launchEmail("libcirc@salisbury.edu");
                    break;
                case 13:// MakerLab
                    launchEmail("makerlab@salisbury.edu");
                    break;
                case 14:// SOAR@SU
                    launchEmail("soar@salisbury.edu");
                    break;
                case 15:// App support
                    launchEmail("libapp@salisbury.edu");
                    break;
                default:
                    break;
            }


            if (position >= 16 && position <= 50)//for the rest of the cases, launch the dialog box to call or email a staff member
            {
                try {
                    launchDialog(contacts.getJSONObject(order.getString(String.valueOf(position-17))));//pass the position as a parameter to the dialog launch function
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



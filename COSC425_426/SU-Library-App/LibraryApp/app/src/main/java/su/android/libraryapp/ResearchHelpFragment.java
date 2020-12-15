package su.android.libraryapp;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

public class ResearchHelpFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListviewX lix;
    ActionBar toolbar;
    Activity activity;
    ListView listView;
    JSONObject subject;
    boolean isConnected;
    JSONArray subjectArray;
    Thread retrieverThread;
    ArrayList<ListItem> listItems;
    JSONObject databaseObject, contactsObject;
    String contact_url, database_url, subject_url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_research_help, container, false);

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.research));

        contact_url = getResources().getString(R.string.contact_url);
        database_url = getResources().getString(R.string.database_url);
        subject_url = getResources().getString(R.string.subject_url);

        // If there is no internet connection, inflate the fragment connection error fragment.
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            return inflater.inflate(R.layout.fragment_connection_error, container, false);
        } else {

            // Creating custom listview
            lix = new ListviewX(Objects.requireNonNull(getActivity())); // ListView object
            listItems = new ArrayList<>(); // ArrayList of list items
            activity = getActivity(); // MainActivity object

            listView = view.findViewById(R.id.listView); // Need to be able to access an xml element with java so that you can modify it dynamically
            listView.setVisibility(View.INVISIBLE); // Set invisible until set up
            retrieverThread = new Thread(new ResearchHelpFragment.JSONRetriever());

            try {
                retrieverThread.start();
                retrieverThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            FillList();

            return view;
        }
    }

    // Fills list of subjects
    public void FillList(){
        Resources r = getResources();
        ListItem0 li0 = new ListItem0(activity, r.getString(R.string.lib_basic)); // New header
        listItems.add(styleLikeHeader(li0));

        //manually add first items into the listview (Library Basics items)
        listItems.add(new SubjectListItem(activity, R.drawable.topic, r.getString(R.string.topic), "default"));
        listItems.add(new SubjectListItem(activity, R.drawable.keywords, r.getString(R.string.keywords), "default"));
        listItems.add(new SubjectListItem(activity, R.drawable.book, r.getString(R.string.e_book), "default"));
        listItems.add(new SubjectListItem(activity, R.drawable.articles, r.getString(R.string.article), "default"));
        listItems.add(new SubjectListItem(activity, R.drawable.evaluate, r.getString(R.string.eval_info), "default"));
        listItems.add(new SubjectListItem(activity, R.drawable.bibliography, r.getString(R.string.bib), "default"));

        li0 = new ListItem0(activity, r.getString(R.string.res_subj));
        listItems.add(styleLikeHeader(li0));

        for(int i = 0; i < subjectArray.length(); i++)
        {
            subject = null;
            int drawableID = 0;
            String subjectName = null, schoolName = null;
            try {
                subject = (JSONObject) subjectArray.get(i); 
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                assert subject != null;
                drawableID= getResources().getIdentifier((String) subject.get("image"), "drawable", "su.android.libraryapp");
                if(drawableID == 0)
                {
                    drawableID= getResources().getIdentifier("general", "drawable", "su.android.libraryapp");
                }
                subjectName = (String) subject.get("name");
                schoolName = (String) subject.get("school");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            listItems.add(new SubjectListItem(activity, drawableID, subjectName, schoolName));

        }

        lix.populate(listItems);
        listView.setAdapter(lix);
        listView.setVisibility(View.VISIBLE);
        listView.setOnItemClickListener(this);
    }

    private ListItem0 styleLikeHeader(ListItem0 li){
        li.getTextView().setTextAppearance(R.style.listHeader); // Looks like a header
        li.getTextView().setPaintFlags(li.getTextView().getPaintFlags());// Removed Underlines
        li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null)); // Sets background color to the standard
        return li;
    }

    // Runnable for JSON retrievers
    private class JSONRetriever implements Runnable {

        @Override
        public void run() {
            try {
                subjectArray = getSubjects(subject_url);
                databaseObject = getDatabases(database_url);
                contactsObject = getContacts(contact_url);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Gets all subjects and their information
    public JSONArray getSubjects(String base_url) throws JSONException {
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
        return new JSONArray(json_string);
    }

    // Gets contact list to match with each subject
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

    // Get a list of databases to fill the database list
    public JSONObject getDatabases(String base_url) throws JSONException {
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1
        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        webViewFragment webView;

        switch(position) {
            case 0:
                break;
            //Select a Research Topic URL
            case 1:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/topic", "Research Topic");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Develop Keywords URL
            case 2:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/keywords", "Keywords");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Find Books & eBooks URL
            case 3:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/findbooks", "Find Books");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Find Articles URL
            case 4:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/findarticles", "Find Articles");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Critically Evaluate Information URL
            case 5:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/howtodolibrary/criticallyevaluate", "Evaluate Information");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            //Create an Annotated Bibliography URL
            case 6:
                webView = new webViewFragment("https://libraryguides.salisbury.edu/c.php?g=327806&p=3146470", "Annotated Bibliography");
                fragmentTransaction.replace(R.id.content_container, webView);
                break;
            // Case 7 is the field header
            case 7:
                break;
            default:
                int pos = position - 8;
                try {
                    fragmentTransaction.replace(R.id.content_container, new SubjectDetailedFragment(pos, (JSONObject) subjectArray.get(pos), contactsObject, databaseObject));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        //add to back stack -> create the sequence of pages visited to come back to later if necessary
        fragmentTransaction.addToBackStack(null).commit();

        MainActivity.pageStack.push(MainActivity.researchPage);
    }

}
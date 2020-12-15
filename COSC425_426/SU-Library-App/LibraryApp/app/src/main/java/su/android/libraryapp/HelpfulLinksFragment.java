package su.android.libraryapp;

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
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Objects;

/**
HELPFUL LINKS FRAGMENT
 * Revamped in 2020 by Sam Disharoon, Jack Stoetzel, Declan Sheehan, & Jordan Welch

- This displays numerous helpful links provided by the library
- This file is supplemented by fragment_helpful_links.xml
- Uses ListItem0
 */
public class HelpfulLinksFragment extends Fragment implements AdapterView.OnItemClickListener {

    ListView listViewhl; //listView helpful links

    //array of items pulled from strings.xml
    String[] strings; //all text for the listview
    ListviewX lix;
    ArrayList<ListItem> listItems;
    ActionBar toolbar;
    TextView loading_msg;
    FragmentManager fm;
    FragmentTransaction ft;

    // sets up the view
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        strings = getResources().getStringArray(R.array.helpful_strings);

        View view = inflater.inflate(R.layout.fragment_helpful_links, container, false);

        loading_msg = view.findViewById(R.id.helpful_links_loading);

        fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();

        lix = new ListviewX(getActivity());
        listItems = new ArrayList<>();

        listViewhl = view.findViewById(R.id.listView);
        listViewhl.setVisibility(View.INVISIBLE);

        for(int x = 0; x <= 22; x++) {
            if(x == 0 || x == 5 || x == 10 || x == 18){
                ListItem0 li = new ListItem0(getActivity(), strings[x]);

                li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
                li.getTextView().setTextAppearance(R.style.listHeader);
                li.getTextView().setPaintFlags(li.getTextView().getPaintFlags()); // Removed Underline

                listItems.add(li);
            } else {
                ListItem0 li = new ListItem0(getActivity(), strings[x]);
                li.seeArrow(); // This sets the arrow to be visible for clickable tabs
                listItems.add(li);
            }


        }

        // populates list of links
        lix.populate(listItems);
        listViewhl.setVisibility(View.VISIBLE);
        loading_msg.setVisibility(View.INVISIBLE);
        listViewhl.setAdapter(lix);
        listViewhl.setOnItemClickListener(this);

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.helpful_links));

        return view;
    }

    // creates the view
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    // called when an item gets clicked
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch(position) {
            case 0: //section headers... DOES NOTHING!
            case 5:
            case 10:
            case 18:
                return;
        }

        Fragment currentFragment;
        String hl = "Helpful Links";
        ft = fm.beginTransaction();
            //CAUTION: section headers count as positions
        //i.e. position 0 is section header 1

        switch(position) {
            case 1://Academic Search Complete
             
                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://libraryguides.salisbury.edu/go.php?c=7603479", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libraryguides.salisbury.edu/go.php?c=7603479", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 2://JSTOR

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://libraryguides.salisbury.edu/go.php?c=7603557", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libraryguides.salisbury.edu/go.php?c=7603557", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 3://Science Direct

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://libraryguides.salisbury.edu/go.php?c=7603627", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libraryguides.salisbury.edu/go.php?c=7603627", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 4://Web of Science

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://libraryguides.salisbury.edu/go.php?c=7603648", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libraryguides.salisbury.edu/go.php?c=7603648", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            //case 5 is section header HELP WITH CITATIONS

            case 6://SU Libraries Citation Style Guide

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://libraryguides.salisbury.edu/citation", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libraryguides.salisbury.edu/citation", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 7://EasyBib

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://proxy-su.researchport.umd.edu/login?url=http://www.easybib.com/", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://proxy-su.researchport.umd.edu/login?url=http://www.easybib.com/", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 8://EndNote Web

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://proxy-su.researchport.umd.edu/login?url=https://www.myendnoteweb.com/touch/EndNoteWeb.html", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://proxy-su.researchport.umd.edu/login?url=https://www.myendnoteweb.com/touch/EndNoteWeb.html", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 9://Purdue OWL

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://owl.english.purdue.edu/owl/", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://owl.english.purdue.edu/owl/", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            //case 10 is section header OTHER LIBRARY RESOURCES

            case 11://Presenting Your Research

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://libraryguides.salisbury.edu/present", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libraryguides.salisbury.edu/present", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 12://Copyright

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://libraryguides.salisbury.edu/copyright", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libraryguides.salisbury.edu/copyright", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 13://SOAR@SU

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://mdsoar.org/handle/11603/9", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://mdsoar.org/handle/11603/9", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 14://SU Libraries Research Guides

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://libraryguides.salisbury.edu", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://libraryguides.salisbury.edu", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 15://SU Library Website

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://www.salisbury.edu/library", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://www.salisbury.edu/library", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 16://Nabb Center for Delmarva History

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://www.salisbury.edu/nabb/", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://www.salisbury.edu/nabb/", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 17://Curriculum Resource Center

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://www.salisbury.edu/seidel/crc/", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://www.salisbury.edu/seidel/crc/", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            //case 18 is section header SU LINKS

            case 19://IT Help Desk

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://www.salisbury.edu/helpdesk/", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://www.salisbury.edu/helpdesk/", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 20://Center for Student Achievement

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://www.salisbury.edu/achievement/", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://www.salisbury.edu/achievement/", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 21://Writing Center

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://www.salisbury.edu/uwc/", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://www.salisbury.edu/uwc/", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

            case 22://Salisbury University Homepage

                if (isNetworkAvailable()) {
                    currentFragment = new webViewFragment("https://www.salisbury.edu", hl);
                } else {
                    currentFragment = new ConnectionErrorFragment(new webViewFragment("https://www.salisbury.edu", hl));
                }
                ft.replace(R.id.content_container, currentFragment);
                break;

        }
        ft.addToBackStack(null).commit();
        MainActivity.pageStack.push(MainActivity.helpfulPage);
    }

    // tests if internet is available
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}


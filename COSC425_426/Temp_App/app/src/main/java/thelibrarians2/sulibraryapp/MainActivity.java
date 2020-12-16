
package thelibrarians2.sulibraryapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, DrawerToggleListener {

    //create classes
    DrawerLayout drawer;
    Toolbar toolbar;
    static FragmentManager fm;
    FragmentTransaction ft;
    ActionBarDrawerToggle drawerToggle;
    FrameLayout frame;
    ListView navList;
    SearchView searchView;
    ListviewX lix; //listview for navigation bar
    public static HashMap<String, ChatWebViewFragment> chat_webs = new HashMap<String, ChatWebViewFragment>();
    public static Stack<Integer> pageStack; //stack of pages visited; corresponds with index of navigation bar


    //Fragment class instances
    Fragment currentFragment;
    HomeFragment home = new HomeFragment();
    BarCodeFragment myCard = new BarCodeFragment();
    LibraryHoursFragment libHours = new LibraryHoursFragment();
    ResearchHelpFragment researchHelp = new ResearchHelpFragment();
    ComputerAvailabilityListFragment computerAvailable = new ComputerAvailabilityListFragment();
    //StudyRoomReserveFragment studyRoomReserve = new StudyRoomReserveFragment();
    //DeviceAvailabilityFragment deviceAvailable = new DeviceAvailabilityFragment();
    AboutFragment about = new AboutFragment();
    HelpfulLinksFragment help = new HelpfulLinksFragment();
    ContactInfoFragment contact = new ContactInfoFragment();
    ChatFragment chat = new ChatFragment();
    NewsFragment news = new NewsFragment();

    //page indices
    //subheader = 0
    final public static int homePage = 1;
    final public static int hoursPage = 2;
    final public static int cardPage = 3;
    final public static int chatPage = 4;
    //subheader = 5
    final public static int researchPage = 6;
    final public static int studyroomPage = 7;
    final public static int devicePage = 8;
    final public static int helpfulPage = 9;
    //subheader = 10
    final public static int newsPage = 11;
    final public static int mapPage = 12;
    final public static int contactPage = 13;
    final public static int aboutPage = 14;

    final public static int computerPage = 100;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        frame = (FrameLayout) findViewById(R.id.content_container);

        //navigation drawer list view
        navList = (ListView) findViewById(R.id.drawer_list);
        setUpNavList();
        navList.setOnItemClickListener(this);

        //navigation drawer setup
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
                //drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                //drawerOpened = true;
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawer.addDrawerListener(drawerToggle);  //animate hamburger icon (i think)
        drawerToggle.syncState();

        //set app bar
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("SU Libraries");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        pageStack = new Stack<Integer>();
        pageStack.push(homePage);

        selectedPageColor(pageStack.peek(), -1);

        fm = getSupportFragmentManager();
        if (savedInstanceState == null)/*when app is started, nothing has happened*/ {
            //only adds home fragment to frame layout if nothing has happened previously
            ft = fm.beginTransaction(); //new instance of fragment transaction class
            ft.add(R.id.content_container, home).commit(); //by default frame layout is empty, so we have to add a new fragment, in this case home, to it
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
/*
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            int previousStackSize = 0;
            @Override
            public void onBackStackChanged() {
                int currentStackSize = fm.getBackStackEntryCount();
                if(previousStackSize == 0 && currentStackSize == 1) { //edge case: first item added

                } else if(currentStackSize == previousStackSize+2) { //backstack grew
                    previousStackSize++;
                } else if(currentStackSize == previousStackSize-2) { //backstack shrank

                }

                currentFragment = getTopFragmentFromBackstack();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        menu.findItem(R.id.filter_icon).setVisible(false);
        menu.findItem(R.id.support_string).setVisible(false);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerToggle.isDrawerIndicatorEnabled()) { //if nav drawer
                    drawer.openDrawer(GravityCompat.START);
                } else { //if up arrow
                    fm.popBackStackImmediate();
                    pageStack.pop();
                }

                //if search box is visible then close/make icon
                if (!searchView.isIconified()) {
                    searchView.clearFocus();
                    searchView.setIconified(true);
                }

                //if keyboard is up then close
                View view = this.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                break;
            case R.id.filter_icon:
                fm.beginTransaction().replace(R.id.content_container, DeviceFilterFragment.getInstance()).addToBackStack(null).commit();
                pageStack.push(devicePage);
                break;
            case R.id.support_string:
                Intent emailer;
                emailer = new Intent(Intent.ACTION_SENDTO);
                emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                emailer.setData(Uri.parse("mailto:"));
                emailer.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"libapp@salisbury.edu"});
                emailer.putExtra(android.content.Intent.EXTRA_SUBJECT, new String[]{"SU Libraries App Support"});
                emailer.putExtra(android.content.Intent.EXTRA_CC, new String[]{"cmwoodall@salisbury.edu"});
                //SU Libraries App Support
                startActivity(emailer);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    // `onPostCreate` called when activity start-up is complete after `onStart()`
    // NOTE! Make sure to override the method with only a single `Bundle` argument
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //drawer item clicked listener
        //section header positions: 0, 5, 11

        if(pageStack.peek() != position) {

            ft = fm.beginTransaction();

            //if pageStack not empty and position not subheader
            if (pageStack.size() > 0 && position != 0 && position != 5 && position != 11)
                selectedPageColor(position, pageStack.peek());

            //replace fragment depending on which item u click in the menu bar
            switch (position)/*position in the array*/ {
                case homePage:
                    //HOME
                    currentFragment = home;
                    ft.replace(R.id.content_container, currentFragment);//replace current fragment with home fragment
                    pageStack.clear();
                    break;
                case hoursPage:
                    //LIBRARY HOURS
                    currentFragment = libHours;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case cardPage:
                    // MY CARD
                    currentFragment = myCard;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case chatPage:
                    // CHAT
                    currentFragment = chat;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case researchPage:
                    //RESEARCH HELP
                    currentFragment = researchHelp;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case studyroomPage:
                    //STUDY ROOM RESERVATIONS
                    if (isNetworkAvailable()) {
                        currentFragment = new webViewFragment("https://salisbury.libcal.com/allspaces", "Study Room Reservations");
                    } else {
                        currentFragment = new ConnectionErrorFragment(new webViewFragment("https://salisbury.libcal.com/allspaces", "Study Room Reservations"), studyroomPage);
                    }
                    ft.replace(R.id.content_container, currentFragment); //replace current fragment with study room reservations fragment
                    break;
                case devicePage:
                    //DEVICE AVAILABILITY
                    if (isNetworkAvailable()) {
                        currentFragment = new webViewFragment("https://www.salisbury.edu/libraries/services/mobile-devices.aspx", "Device Availability");
                    } else
                        currentFragment = new ConnectionErrorFragment(new webViewFragment("https://www.salisbury.edu/libraries/services/mobile-devices.aspx", "Device Availability"), devicePage);
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case helpfulPage:
                    //HELPFUL LINKS
                    if (isNetworkAvailable()) {
                        currentFragment = help;
                    } else {
                        currentFragment = new ConnectionErrorFragment(help, helpfulPage);
                    }
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case newsPage:
                    // NEWS
                    if (isNetworkAvailable()) {
                        currentFragment = news;
                    } else {
                        currentFragment = new ConnectionErrorFragment(news, newsPage);
                    }
                    ft.replace(R.id.content_container, currentFragment);//replace current fragment with home fragment
                    break;
                case mapPage:
                    //BUILDING MAPS
                    if (isNetworkAvailable()) {
                        currentFragment = new webViewFragment("https://libapps.salisbury.edu/maps/", "Building Maps");
                    } else {
                        currentFragment = new ConnectionErrorFragment(new webViewFragment("http://libapps.salisbury.edu/maps/", "Building Maps"), mapPage);
                    }
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                //ft.replace(R.id.content_container, buildingMaps);//replace current fragment with building maps fragment
                case contactPage:
                    //CONTACT INFORMATION
                    currentFragment = contact;
                    ft.replace(R.id.content_container, currentFragment);
                    break;
                case aboutPage:
                    //ABOUT
                    currentFragment = about;
                    ft.replace(R.id.content_container, currentFragment);//replace current fragment with about fragment
                    break;
                case 0: //if section header is selected do nothing
                case 5:
                case 10:
                    return;
            }

            pageStack.push(position);

            ft.addToBackStack(null).commit();

            drawer.closeDrawers();
        }
    }


    @Override
    public void onBackPressed() {
        if(fm.findFragmentById(R.id.content_container).getClass().equals(new webViewFragment().getClass())) {
            if (webViewFragment.getWebView().canGoBack()) {
                webViewFragment.getWebView().goBack();
                return;
            } else {
                if (pageStack.size() > 1) {
                    int page = pageStack.pop();
                    selectedPageColor(pageStack.peek(), page);
                }
                super.onBackPressed();
            }
        } else {
            //define function of phone's back button
            if (this.drawer.isDrawerOpen(GravityCompat.START)) {
                this.drawer.closeDrawer(GravityCompat.START);
            } else if (!searchView.isIconified()) {
                searchView.clearFocus();
                searchView.setIconified(true);
            } else {
                if (pageStack.size() > 1) {
                    int page = pageStack.pop();
                    selectedPageColor(pageStack.peek(), page);
                }
                super.onBackPressed();
            }
        }
    }

    private void setUpNavList() {
        lix = new ListviewX(this);
        String[] strings = getResources().getStringArray(R.array.nav_links);
        ArrayList<ListItem> listItems = new ArrayList<ListItem>();

        ListItem0 li;
        for(int x = 0; x < 16; x++) {
            li = new ListItem0(this, strings[x]);
            switch (x) {
                //list headers
                case 0:
                case 5:
                case 10:
                    //li.getTextView().setTextColor(Color.parseColor("#FFFFFF"));
                    li.getLayout().setBackgroundColor(ResourcesCompat.getColor(getResources(), R.color.listHeader, null));
                    //li.getTextView().setTextAppearance(this, R.style.listHeader);
                    li.getTextView().setPaintFlags(li.getTextView().getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    li.getTextView().setTextAppearance(this, R.style.listHeader);
                    break;
                default:
                    li.getTextView().setTextColor(ResourcesCompat.getColor(getResources(), R.color.listMainText, null));
            }
            li.getTextView().setTextSize(20);
            li.getLayout().setMinimumHeight(48);
            listItems.add(li);
        }

        lix.populate(listItems);
        navList.setAdapter(lix);

    }

    @Override
    public void toggleDrawer(boolean toggle) { //toggles home icon between nav drawer and up arrow
        drawerToggle.setDrawerIndicatorEnabled(toggle);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            ft = fm.beginTransaction();
            Fragment currentFragment;
            //searches worldcat library database for whatever query string contains
            if (isNetworkAvailable()) {
                currentFragment = new webViewFragment("http://salisbury.worldcat.org/m/search?q=" + query, searchView, "Search Results");
            } else {
                currentFragment = new ConnectionErrorFragment(new webViewFragment("http://salisbury.worldcat.org/m/search?q=" + query, searchView, "Search Results"));
            }

            pageStack.push(homePage);
            ft.replace(R.id.content_container, currentFragment).addToBackStack(null).commit();

            searchView.clearFocus();
        }
    }

    private void selectedPageColor(int position, int lastPosition) {
        //change navigation list item text color for selected item
        ListItem0 item = (ListItem0) lix.getItem(position);
        //item.getTextView().setTextColor(ResourcesCompat.getColor(getResources(), R.color.colorPrimary, null));

        if(lastPosition != -1 && lastPosition != position) {
            item = (ListItem0) lix.getItem(lastPosition);
            item.getTextView().setTextColor(ResourcesCompat.getColor(getResources(), R.color.listMainText, null));
        }
    }

    private Fragment getTopFragmentFromBackstack() {
        Fragment f = fm.findFragmentById(R.id.content_container);
        Log.i("nick", "fragment.getClass() = "+f.getClass());
        return f;
    }
}

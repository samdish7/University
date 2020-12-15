package su.android.libraryapp;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.HashMap;
import java.util.Objects;
import java.util.Stack;

/**
 * Main activity page Revamped by Sam Disharoon, Jordan Welch, Declan Sheehan, & Jack Stoetzel in 2020
  - This is the driver for the app
  - It leads to all other fragments and manages them.
  - Manages how the back button functions
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //create classes
    static FragmentManager fm;
    FragmentTransaction ft;
    FrameLayout frame;
    public static HashMap<String, ChatWebViewFragment> chat_webs = new HashMap<>();
    public static Stack<Integer> pageStack; //stack of pages visited; corresponds with index of navigation bar


    //Fragment class instances
    Fragment currentFragment;
    HomeFragment home = new HomeFragment();
    BarCodeFragment myCard = new BarCodeFragment();
    LibraryHoursFragment libHours = new LibraryHoursFragment();
    ResearchHelpFragment researchHelp = new ResearchHelpFragment();
    DeviceAvailabilityFragment deviceAvailable = new DeviceAvailabilityFragment();
    AboutFragment about = new AboutFragment();
    HelpfulLinksFragment help = new HelpfulLinksFragment();
    ContactInfoFragment contact = new ContactInfoFragment();
    ChatFragment chat = new ChatFragment();
    StudyRoomListFragment studyRoomReserve = new StudyRoomListFragment();
    NewsFragment news = new NewsFragment();

    //page indices
    //sub header = 0
    final public static int homePage = 1;
    final public static int hoursPage = 2;
    final public static int cardPage = 3;
    final public static int chatPage = 4;
    //sub header = 5
    final public static int researchPage = 6;
    final public static int studyroomPage = 7;
    final public static int devicePage = 8;
    final public static int helpfulPage = 9;
    //sub header = 10
    final public static int newsPage = 11;
    final public static int mapPage = 12;
    final public static int contactPage = 13;
    final public static int aboutPage = 14;

    public static boolean firstOpen = true;

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

        frame = findViewById(R.id.content_container);


        pageStack = new Stack<>();
        pageStack.push(homePage);

        fm = getSupportFragmentManager();
        if (savedInstanceState == null)/*when app is started, nothing has happened*/ {
            //only adds home fragment to frame layout if nothing has happened previously
            ft = fm.beginTransaction(); //new instance of fragment transaction class
            ft.add(R.id.content_container, home).commit(); //by default frame layout is empty, so we have to add a new fragment, in this case home, to it
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.app_bar_menu, menu);
        menu.findItem(R.id.filter_icon).setVisible(false);
        menu.findItem(R.id.support_string).setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:

                    fm.popBackStackImmediate();
                    pageStack.pop();

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
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //drawer item clicked listener
        //section header positions: 0, 5, 11

        if(pageStack.peek() != position) {

            ft = fm.beginTransaction();

            //if pageStack not empty and position not sub header
            if (pageStack.size() > 0 && position != 0 && position != 5 && position != 11)

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
                        currentFragment = studyRoomReserve;
                    } else {
                        currentFragment = new ConnectionErrorFragment(studyRoomReserve, studyroomPage);
                    }
                    ft.replace(R.id.content_container, currentFragment); //replace current fragment with study room reservations fragment
                    break;

                case devicePage:
                    //DEVICE AVAILABILITY
                    if (isNetworkAvailable()) {
                        currentFragment = deviceAvailable;
                    } else
                        currentFragment = new ConnectionErrorFragment(deviceAvailable, devicePage);
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

        }
    }


    @Override
    public void onBackPressed() {
        if(Objects.requireNonNull(fm.findFragmentById(R.id.content_container)).getClass().equals(webViewFragment.class)) {
            if (webViewFragment.getWebView().canGoBack()) {
                webViewFragment.getWebView().goBack();
            }
            else {
                super.onBackPressed();
            }
        } else {
                super.onBackPressed();
        }
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
                // TODO:
                //  - Define a title for the content shown.
                //  - Make sure this auto-generated URL is correct
                .setName("Main Page")
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

}

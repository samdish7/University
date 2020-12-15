package su.android.libraryapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/** Code Revised by: Declan Sheehan
    Date: October, 2020.
    Description: This fragment works with DeviceFragment.java to retrieve the JSON information
    for device availability using JSONRetriever. Next it parses the information using ParseJSON
    found in DeviceFragment.java. Next postRetriever will set up the slidingTabLayout. The other
    remaining functions help with navigation for this fragment.
 */

public class DeviceAvailabilityFragment extends Fragment {

    // Define the class variables that will be used.
    SectionsPagerAdapter mSectionsPagerAdapter;
    SlidingTabLayout mSlidingTabLayout;
    String base_url, json_string;
    HttpURLConnection conn;
    ViewPager mViewPager;
    Boolean isConnected;
    ActionBar toolbar;
    Menu appMenu;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Set the current view to inflate this fragment once returned at the end of this function.
        view = inflater.inflate(R.layout.fragment_device_availability, container, false);

        // Set the string to the device availability url.
        base_url = getResources().getString(R.string.device_url);

        // Set the name of the top toolbar.
        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.device));

        // Check for the internet availability.
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        // If there is no internet, inflate the connection error fragment.
        // Else, run the necessary threads and return this current view.
        if (!isConnected) {
            return inflater.inflate(R.layout.fragment_connection_error, container, false);
        } else {
            setHasOptionsMenu(true);
            Thread thread1 = new Thread(new JSONRetriever());
            Thread thread2 = new Thread(new postRetriever());

            try {
                thread1.start();
                thread1.join();
                thread2.start();
                thread2.join();
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            return view;
        }
    }

    // Sets up the top-bar menu to display the filter button.
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        appMenu = menu;
        menu.findItem(R.id.filter_icon).setVisible(true);
    }

    // Set up the FragmentPagerAdapter.
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            // Return PlaceholderFragment.newInstance(position + 1);

            switch (position) {
                case 0:
                case 1:
                    return new DeviceFragment(position);
            }
            return new DeviceFragment(position);
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SHOW ALL";
                case 1:
                    return "AVAILABLE ONLY";
            }
            return null;
        }
    }

    // A class that runs as a thread, which will grab any JSON data on the device availability,
    // then parse the resulting string for device information.
    private class JSONRetriever implements Runnable {
        @Override
        public void run (){
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
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException ignored) { }
                    } catch (IOException e) { }
                } catch (MalformedURLException ignored) { }
                json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception ignored) { }
            DeviceFragment.parseJSON(json_string);
        }
    }

    // Another class that runs as a thread that will create an adapter, set it, and define the
    // addOnPageChangeListener function.
    private class postRetriever implements Runnable {
        @Override
        public void run () {
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = view.findViewById(R.id.frag_pager);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            // Initialize the SlidingTabLayout. Note that the order is important. First initialize
            // the ViewPager and Adapter and only then initialize the SlidingTabLayout.
            mSlidingTabLayout = view.findViewById(R.id.sliding_tabs);
            mSlidingTabLayout.setViewPager(mViewPager);
            mSlidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(Objects.requireNonNull(getActivity()), R.color.colorAccent));

            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                // This method will be invoked when the current page is scrolled
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

                // This method will be invoked when a new page becomes selected.
                @Override
                public void onPageSelected(int position) {
                    switch (position) {
                        case 0:
                            break;
                        case 1:
                            break;
                    }
                }
                // Called when the scroll state changes:
                // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
                @Override
                public void onPageScrollStateChanged(int state) { }
            });
        }
    }
}
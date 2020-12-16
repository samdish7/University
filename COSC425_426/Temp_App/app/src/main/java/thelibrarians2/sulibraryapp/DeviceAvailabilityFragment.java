package thelibrarians2.sulibraryapp;

import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// This fragment is for checking device availability. Works together with DeviceFragment.java

public class DeviceAvailabilityFragment extends Fragment {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    SlidingTabLayout mSlidingTabLayout;
    String base_url, json_string;
    HttpURLConnection conn; // Connection object
    View view;
    Menu appMenu;
    ActionBar toolbar;
    TextView loading_msg;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        base_url = getResources().getString(R.string.device_url);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_device_availability, container, false);
        setHasOptionsMenu(true);

        loading_msg = (TextView) view.findViewById(R.id.device_list_loading);
        loading_msg.setVisibility(View.VISIBLE);

        //getActivity().invalidateOptionsMenu();

        new JSONRetriever().execute();

        toolbar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        toolbar.setTitle(getResources().getString(R.string.device));

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        appMenu = menu;
        menu.findItem(R.id.filter_icon).setVisible(true);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);

            switch (position) {
                case 0:
                    return new DeviceFragment(position);
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

    private class JSONRetriever extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

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
                        } catch (IOException e) {
                        }
                    } catch (IOException e) {
                        return null;
                    }
                } catch (MalformedURLException e) {
                }
                json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) {
            }
            DeviceFragment.parseJSON(json_string);
            return null;
        }

        protected void onPostExecute(Void v) {

            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager());
            //mSectionsPagerAdapter.getItem()

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) view.findViewById(R.id.frag_pager);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            loading_msg.setVisibility(View.INVISIBLE);
            // Initialize the SlidingTabLayout. Note that the order is important. First init ViewPager and Adapter and only then init SlidingTabLayout
            mSlidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.sliding_tabs);
            mSlidingTabLayout.setViewPager(mViewPager);
            mSlidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(getActivity(), R.color.colorAccent));

            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                // This method will be invoked when the current page is scrolled
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                // This method will be invoked when a new page becomes selected.
                @Override
                public void onPageSelected(int position) {

                    switch (position) {
                        case 0:
                            //Toast.makeText(getActivity(), "Hello", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            //Toast.makeText(getActivity(), "Goodbye", Toast.LENGTH_SHORT).show();
                            break;


                    }
                }

                // Called when the scroll state changes:
                // SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING
                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }

    }
}

package thelibrarians2.sulibraryapp;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

/**
 * AboutFragment
 * Revised by Sam Disharoon, Jack Stoetzel, Declan Sheehan, & Jordan Welch in 2020
 * Description: Displays information about the app and library.
 * Supplemented by fragment_about.xml
 */

public class AboutFragment extends Fragment {

    // Define class variables.
    ActionBar toolbar;
    Menu app_menu;

    // Once the view is created, execute this function.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Set the toolbar name.
        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.about));
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        // Get the TextView that will open the privacy fragment, and set an anonymous
        // OnClickListener for that TextView.
        TextView privacy = view.findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Fragment fragment = new PrivacyFragment();
                FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content_container, fragment);
                fragmentTransaction.addToBackStack(null).commit();
                MainActivity.pageStack.push(MainActivity.aboutPage);
            }
        });

        return view;
    }

    // Add the support button to the top-bar menu.
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        app_menu = menu;
        menu.findItem(R.id.support_string).setVisible(true);
    }

}

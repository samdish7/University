package su.android.libraryapp;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Revamped by Sam Disharoon, Jack Stoetzel, Declan Sheehan & Jordan Welch in 2020
 * Displays the map of the Library by calling a webview Fragment in main activity
 * Will display a messsage if internet is unavailable 
 */
public class MapsBuildingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps_building, container, false);
    }

}

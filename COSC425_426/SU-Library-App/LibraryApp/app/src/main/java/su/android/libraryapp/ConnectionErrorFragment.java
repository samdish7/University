package su.android.libraryapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;

/**
 * Revamped in 2020 by Jack Stoetzel, Declan Sheehan, & Jordan Welch
 * Displays a message if internet is down or webpage fails to load
 * Used basically wherever internet is required
 */

public class ConnectionErrorFragment extends Fragment {

    View view;
    ActionBar toolbar;
    Fragment fragment;
    private Integer pos;

    public ConnectionErrorFragment(Fragment fragment, Integer position) {
        this.fragment = fragment;
        pos = position;
    }

    public ConnectionErrorFragment(Fragment fragment) {
        this.fragment = fragment;
        pos = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_connection_error, container, false);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isNetworkAvailable()) {
                    FragmentManager fm = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                    if(pos != null)
                        MainActivity.pageStack.push(pos);
                    getActivity().onBackPressed();
                    fragmentTransaction.replace(R.id.content_container, fragment).addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(getResources().getString(R.string.connection_error));
        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

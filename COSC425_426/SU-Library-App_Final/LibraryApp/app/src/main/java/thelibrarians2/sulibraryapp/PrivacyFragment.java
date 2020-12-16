package thelibrarians2.sulibraryapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Objects;


/**
 * Revised by Sam Disharoon, Jack Stoetzel, Declan Sheehan & Jordan Welch in 2020
 * Description: This displays the Attributes & Privacy of the SU library App
 * Supplemented by fragment_privacy.xml
 */

public class PrivacyFragment extends Fragment {

    // Execute this function once view is created.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_privacy, container, false);

        // Get the view associated with the "Privacy Notice", and set the OnClickListener to the
        // anonymous OnClickListener defined below.
        // Define class variables.
        TextView privacyNotice = view.findViewById(R.id.textView6);
        privacyNotice.setTextColor(getResources().getColor(R.color.color_blue, null));
        privacyNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(getResources().getString(R.string.privacy_url));
                Intent webpage_intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (webpage_intent.resolveActivity(Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
                    startActivity(webpage_intent);
                }
            }
        });

        return view;
    }

    // Define the onDestroyView.
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

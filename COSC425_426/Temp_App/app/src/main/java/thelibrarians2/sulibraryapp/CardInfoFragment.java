package thelibrarians2.sulibraryapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Xopher on 3/28/2017.
 */

public class CardInfoFragment extends Fragment {

    View view;
    Fragment fragment;
    View.OnClickListener listener;
    Button enter;
    BarCodeFragment ba;
    MainActivity ma;
    EditText firstName, lastName, barcode_data;
    String fName, lName, bcodeData;
    DrawerToggleListener toggleListener;
    static final String BUNDLE = "key";
    static final String FIRST_NAME = "key1";
    static final String BAR_CODE = "key3";
    static final String LAST_NAME = "key2";
    SharedPreferences settings;
    SharedPreferences.Editor editor;

    CardInfoFragment() {
        ba = new BarCodeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.cardinfo, container, false);
        ma = (MainActivity)getActivity();

        firstName = (EditText) view.findViewById(R.id.firstName);
        lastName = (EditText) view.findViewById(R.id.lastName);
        barcode_data = (EditText) view.findViewById(R.id.barcode_data);

        //add code that makes sure user inputs 14 digits for bar code

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open a fragment to edit existing card info

                if (barcode_data.length() != 14 || firstName.length() < 1 || lastName.length() < 1){
                //checks if barcodeno is exactly 14 digits long or names not input

                    //error checks for each condition
                    if(firstName.length() < 1){
                        firstName.setError("Please input a first name.");
                        firstName.requestFocus();}

                    else if(lastName.length() < 1){
                        lastName.setError("Please input a last name.");
                        lastName.requestFocus();}

                   else  if(barcode_data.length() != 14){
                         barcode_data.setError("Barcode number should be 14 digits. See back of Gullcard.");
                         barcode_data.requestFocus();}

                } else {

                    // Value is valid (between 1 and 175 characters long)
                    fName = firstName.getText().toString();
                    lName = lastName.getText().toString();
                    bcodeData = barcode_data.getText().toString();
                    Log.e("good", "create card");
                    settings = getActivity().getSharedPreferences(FIRST_NAME, 0);
                    editor = settings.edit();
                    editor.putString(FIRST_NAME, fName);
                    editor.commit();
                    settings = getActivity().getSharedPreferences(LAST_NAME, 0);
                    editor = settings.edit();
                    editor.putString(LAST_NAME, lName);
                    editor.commit();
                    settings = getActivity().getSharedPreferences(BAR_CODE, 0);
                    editor = settings.edit();
                    editor.putString(BAR_CODE, bcodeData);
                    editor.commit();
                    Log.e("good", fName + lName + bcodeData);
                    //if keyboard is up then close
                    View view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                    getActivity().onBackPressed();
                }
            }
        } ;

        enter = (Button) view.findViewById(R.id.enterText);
        enter.setOnClickListener(listener);

        //back arrow instead of navigation drawer
        toggleListener = (DrawerToggleListener) getActivity();
        toggleListener.toggleDrawer(false);

        return view;
    }

    //puts name and bar code data into bundle
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        toggleListener.toggleDrawer(true);
    }
}

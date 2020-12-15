package su.android.libraryapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class CardInfoFragment extends Fragment {

    static final String FIRST_NAME = "key1";
    static final String LAST_NAME = "key2";
    static final String BAR_CODE = "key3";

    View view, currView;
    Button enter;
    MainActivity ma;
    BarCodeFragment ba;
    SharedPreferences settings;
    View.OnClickListener listener;
    String fName, lName, bcodeData;
    SharedPreferences.Editor editor;
    EditText firstName, lastName, barcode_data;

    CardInfoFragment(String fn, String ln, String bc){
        fName = fn;
        lName = ln;
        bcodeData = bc;
        ba = new BarCodeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.card_info, container, false);
        ma = (MainActivity)getActivity();

        firstName = view.findViewById(R.id.firstName);
        lastName = view.findViewById(R.id.lastName);
        barcode_data = view.findViewById(R.id.barcode_data);

        firstName.setText(fName);
        lastName.setText(lName);
        barcode_data.setText(bcodeData);

        //add code that makes sure user inputs 14 digits for bar code

        listener = new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                if (barcode_data.length() != 14 || firstName.length() < 1 || lastName.length() < 1){
                    if(firstName.length() < 1){
                        firstName.setError("Please input a first name.");
                        firstName.requestFocus();
                    }
                    else if(lastName.length() < 1){
                        lastName.setError("Please input a last name.");
                        lastName.requestFocus();
                    }
                    // ****************************************************************
                    else  if(barcode_data.length() != 14){
                        currView = getActivity().getCurrentFocus();
                        if (currView != null) {
                            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(currView.getWindowToken(), 0);
                        }
                        barCodeAlert();
                    }
                } else {
                    saveInfo();
                    currView = getActivity().getCurrentFocus();
                    if (currView != null) {
                        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(currView.getWindowToken(), 0);
                    }
                    getActivity().onBackPressed();
                }
            }
        } ;

        enter = view.findViewById(R.id.enterText);
        enter.setOnClickListener(listener);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // Saves name and barcode number to be used in BarCodeFragment
    public void saveInfo() {
        // Value is valid (between 1 and 175 characters long)
        fName = firstName.getText().toString();
        lName = lastName.getText().toString();
        bcodeData = barcode_data.getText().toString();
        Log.e("good", "create card");
        settings = Objects.requireNonNull(getActivity()).getSharedPreferences(FIRST_NAME, 0);
        editor = settings.edit();
        editor.putString(FIRST_NAME, fName);
        editor.apply();
        settings = getActivity().getSharedPreferences(LAST_NAME, 0);
        editor = settings.edit();
        editor.putString(LAST_NAME, lName);
        editor.commit();
        settings = getActivity().getSharedPreferences(BAR_CODE, 0);
        editor = settings.edit();
        editor.putString(BAR_CODE, bcodeData);
        editor.commit();
        Log.e("good", fName + lName + bcodeData);
    }

    // Pop-up for when user enters a bar code number that is less than 14 digits
    public void barCodeAlert() {
        CustomAlertDialogBuilder dialog = new CustomAlertDialogBuilder(getActivity());

        dialog.setTitle(R.string.info_popup_title);
        dialog.setMessage(R.string.info_popup_desc);
        String ok = getString(R.string.info_popup_ok);
        final String link = getString(R.string.info_popup_link);
        final String where = getString(R.string.info_popup_where);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, where, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                webViewFragment webview;
                FragmentTransaction ft;

                webview = new webViewFragment(link, where);

                ft = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.content_container, webview);
                ft.addToBackStack(null).commit();

                MainActivity.pageStack.push(MainActivity.researchPage);
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                currView = getActivity().getCurrentFocus();
                if (currView != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInputFromWindow(currView.getWindowToken(), 0, 0);
                }
            }
        });
        dialog.show();
    }
}
package su.android.libraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class StudyRoomReserveFragment extends Fragment {

    static final String FIRSTNAME = "key4";
    static final String LASTNAME = "key5";
    static final String EMAIL = "key6";

    View view, currView;
    int room_id;
    Button enter;
    MainActivity ma;
    Reservation[] resList;
    Reservation resFrom, resTo;
    SharedPreferences settings;
    View.OnClickListener listener;
    SharedPreferences.Editor editor;
    Thread retrieverThread, submissionThread;
    List<String> daysList, startList, endList;
    Spinner dateSpinner, startSpinner, endSpinner;
    EditText firstName, lastName, email, groupName;
    String availString, availability_url, response, submission_url, from, to, fn, ln, em;

    StudyRoomReserveFragment(int id) {
        this.room_id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ma = (MainActivity)getActivity();
        view = inflater.inflate(R.layout.fragment_study_room_reserve, container, false);

        email = view.findViewById(R.id.resEmail);
        enter = view.findViewById(R.id.submitButton);
        groupName = view.findViewById(R.id.groupName);
        lastName = view.findViewById(R.id.resLastName);
        firstName = view.findViewById(R.id.resFirstName);
        response = "";

        availability_url = getResources().getString(R.string.availability_url);
        availability_url = availability_url + room_id;

        daysList = new java.util.ArrayList<>(Collections.singletonList("Select a Date"));
        startList = new java.util.ArrayList<>(Collections.singletonList("Select a Start Time"));
        endList = new java.util.ArrayList<>(Collections.singletonList("Select an End Time"));

        //*****************************************************
        retrieverThread = new Thread(new StudyRoomReserveFragment.JSONRetriever());

        try {
            retrieverThread.start();
            retrieverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //*****************************************************
        dateSpinner = view.findViewById(R.id.dateSpinner);
        startSpinner = view.findViewById(R.id.startSpinner);
        endSpinner = view.findViewById(R.id.endSpinner);

        setupDateSpinner();

        //*****************************************************
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validEmail = email.getText().toString().contains("@salisbury.edu") || (email.getText().toString().contains("@gulls.salisbury.edu"));
                if (!validEmail || firstName.length() < 1 || lastName.length() < 1 || resFrom == null || resTo == null){
                    if(firstName.length() < 1){
                        firstName.setError("Please input a first name.");
                        firstName.requestFocus();
                    }
                    else if(lastName.length() < 1){
                        lastName.setError("Please input a last name.");
                        lastName.requestFocus();
                    }
                    else if(email.length() == 0){
                        email.setError("Please input an email.");
                        email.requestFocus();
                    }
                    else if(!validEmail){
                        email.setError("Email must end in @salisbury.edu or @gulls.salisbury.edu");
                        email.requestFocus();
                    }

                } else {
                    submission_url = "https://libapps.salisbury.edu/room-reservations/ajax/reserve_space.php?";

//                    // TESTING ROOM ID
//                    room_id = 64713;

                    submission_url += "rid=" + room_id;
                    submission_url += "&fname=" + firstName.getText().toString();
                    submission_url += "&lname=" + lastName.getText().toString();
                    submission_url += "&email=" + email.getText().toString();
                    submission_url += "&nickname=" + groupName.getText().toString();
                    submission_url += "&times="+ resFrom.getJsonFrom() + "%7C" + resTo.getJsonTo() + "&app=2";

                    System.out.println(submission_url);
                    submissionThread = new Thread(new StudyRoomReserveFragment.JSONSender());
                    try {
                        submissionThread.start();
                        submissionThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //**************************************************************************************************
                    saveInfo();
                    reservationAlert();
                }
            }
        } ;

        enter.setOnClickListener(listener);

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.e("good", "onViewStateRestored");
        Activity activity = getActivity();


        assert activity != null;
        if((activity.getSharedPreferences(FIRSTNAME, 0).getString(FIRSTNAME, null) != null ) &&
                (activity.getSharedPreferences(LASTNAME, 0).getString(LASTNAME, null) != null &&
                        (activity.getSharedPreferences(EMAIL, 0).getString(EMAIL, null) != null))) {

            Log.e("good", "get info");

            settings = activity.getSharedPreferences(FIRSTNAME, 0);
            fn = settings.getString(FIRSTNAME, null);

            settings = activity.getSharedPreferences(LASTNAME, 0);
            ln = settings.getString(LASTNAME, null);

            settings = activity.getSharedPreferences(EMAIL, 0);
            em = settings.getString(EMAIL, null);
        }

        firstName = view.findViewById(R.id.resFirstName);
        firstName.setText(fn);

        lastName = view.findViewById(R.id.resLastName);
        lastName.setText(ln);

        email = view.findViewById(R.id.resEmail);
        email.setText(em);

        //log statement used for debugging
        Log.e("good", "end of onViewStateRestored");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    // Saves name and email info for next time this page is opened
    public void saveInfo() {
        em = email.getText().toString();
        ln = lastName.getText().toString();
        fn = firstName.getText().toString();

        settings = Objects.requireNonNull(getActivity()).getSharedPreferences(FIRSTNAME, 0);
        editor = settings.edit();
        editor.putString(FIRSTNAME, fn);
        editor.apply();

        settings = getActivity().getSharedPreferences(LASTNAME, 0);
        editor = settings.edit();
        editor.putString(LASTNAME, ln);
        editor.commit();

        settings = getActivity().getSharedPreferences(EMAIL, 0);
        editor = settings.edit();
        editor.putString(EMAIL, em);
        editor.commit();
    }


    // Fills available dates for the study room
    public void setupDateSpinner() {
        dateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                System.out.println("SELECTED");
                // An item was selected. You can retrieve the selected item using
                String date = (String) parent.getItemAtPosition(pos);
                if(!date.equals("Select a Date")){
                    fillStartTimes(date);
                }
                else{
                    startSpinner.setVisibility(View.GONE);
                    endSpinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("NOTHING");
            }
        });
        dateSpinner.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                currView = getActivity().getCurrentFocus();
                if (currView != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(currView.getWindowToken(), 0);
                }
                return false;
            }
        }) ;
        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, daysList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(adapter);
    }

    // Fills start times for the date selected above
    public void fillStartTimes(final String date){
        endSpinner.setVisibility(View.GONE);
        startList.clear();
        startList = new java.util.ArrayList<>(Collections.singletonList("Select a Start Time"));

        for (Reservation reservation : resList) {
            if (reservation.getDateStart().equals(date)) {
                if (!startList.contains(reservation.getSpinFrom())) {
                    startList.add(reservation.getSpinFrom());
                }
            }
        }

        startSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                String time = (String) parent.getItemAtPosition(pos);
                if(!time.equals("Select a Start Time")){
                    from = time;
                    for (Reservation reservation : resList){
                        if (date.equals(reservation.getDateStart()) && time.equals(reservation.getSpinFrom())){
                            resFrom = reservation;
                        }
                    }
                    fillEndTimes(date, time);
                }
                else {
                    endSpinner.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        startSpinner.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                currView = getActivity().getCurrentFocus();
                if (currView != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(currView.getWindowToken(), 0);
                }
                return false;
            }
        }) ;
        ArrayAdapter<String> startAdapter = new ArrayAdapter<>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, startList);
        startAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startSpinner.setAdapter(startAdapter);
        startSpinner.setVisibility(View.VISIBLE);
    }

    // Fills end times for the date a time selected above (up to 4 hours)
    public void fillEndTimes(final String date, final String time){
        endList.clear();
        endList = new java.util.ArrayList<>(Collections.singletonList("Select an End Time"));


        Time startTime = convertTime(resFrom.getJsonFrom());

        int block = 1;
        for (Reservation reservation : resList) {
            if (reservation.getDateStart().equals(date)) {
                Time endTime = convertTime(reservation.getJsonTo());
                if (((int) (endTime.getTime() - startTime.getTime()) / 60000) == block * 30 && block > 1 && block <= 8) {
                    endList.add(reservation.getSpinTo());
                    block++;
                }
                else if (block == 1 && reservation.spinFrom.equals(time)){
                    endList.add(reservation.getSpinTo());
                    block++;
                }
            }
        }
        endSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // An item was selected. You can retrieve the selected item using
                String time = (String) parent.getItemAtPosition(pos);
                if(!time.equals("Select a Start Time")){
                    to = time;
                    for (Reservation reservation : resList){
                        if (date.equals(reservation.getDateStart()) && time.equals(reservation.getSpinTo())){
                            resTo = reservation;
                        }
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        endSpinner.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                currView = getActivity().getCurrentFocus();
                if (currView != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(currView.getWindowToken(), 0);
                }
                return false;
            }
        }) ;
        ArrayAdapter<String> endAdapter = new ArrayAdapter<>(Objects.requireNonNull(this.getContext()), android.R.layout.simple_spinner_item, endList);
        endAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endSpinner.setAdapter(endAdapter);
        endSpinner.setVisibility(View.VISIBLE);
    }


    // Converts times to to remove timezone
    public Time convertTime(String t) {
        int T_index = t.indexOf('T');
        String substring = t.substring(T_index + 1, t.indexOf('-', T_index));

        return Time.valueOf(substring);
    }

    // Runnable function for send reservation request
    private class JSONSender implements Runnable {
        @Override
        public void run() {
            response = sendReservation(submission_url);
        }
    }

    // Function for sending study room reservation request
    public String sendReservation(String... params){
        StringBuilder data = new StringBuilder();

        HttpURLConnection httpURLConnection = null;
        try {

            httpURLConnection = (HttpURLConnection) new URL(params[0]).openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.flush();
            wr.close();

            InputStream in = httpURLConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(in);

            int inputStreamData = inputStreamReader.read();
            while (inputStreamData != -1) {
                char current = (char) inputStreamData;
                inputStreamData = inputStreamReader.read();
                data.append(current);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }

        return data.toString();
    }

    // Function for getting study room availability dates and times
    public String getAvailability(String base_url) {
        System.out.println("GETTING ROOM AVAILABILITY");
        URL url;
        String inputLine;
        HttpURLConnection conn;
        StringBuilder response = new StringBuilder();
        try {
            try {
                url = new URL(base_url); // url passed in
                try {
                    conn = (HttpURLConnection)url.openConnection(); // Opens new connection
                    conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                    conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                    try {
                        while ((inputLine = br.readLine()) != null) // While there are more contents to read
                            response.append(inputLine); // Append the new data to all grabbed data
                        br.close(); // Close connection
                    } catch (IOException ignored) {System.out.println("CATCH 1");}
                } catch (IOException ignored) {System.out.println("CATCH 2");}
            } catch (MalformedURLException ignored) {System.out.println("CATCH 3");}
        } catch (Exception ignored) {System.out.println("CATCH 4");}
        System.out.println("GOT ROOM AVAILABILITY");
        return response.toString();
    }

    // Runnable function for getting room availability
    private class JSONRetriever implements Runnable {
        @Override
        public void run() {
            availString = getAvailability(availability_url);

            try {
                parseJSON(availString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Parses date and time JSON
    private void parseJSON(String input) throws JSONException {
        JSONArray arr = new JSONArray(input);
        JSONObject obj = arr.getJSONObject(0);
        JSONArray times = obj.getJSONArray("availability");

        resList = new Reservation[times.length()];
        for (int i = 0; i < times.length(); i++)
        {
            resList[i] = new Reservation(times.getJSONObject(i).getString("from"), times.getJSONObject(i).getString("to"));
        }
        daysList.clear();
        daysList = new java.util.ArrayList<>(Collections.singletonList("Select a Date"));
        for (Reservation reservation : resList) {
            if (!daysList.contains(reservation.getDateStart())) {
                daysList.add(reservation.getDateStart());
            }
        }
    }

    // Alert dialog for when the reservation button is clicked
    public void reservationAlert() {
        CustomAlertDialogBuilder dialog = new CustomAlertDialogBuilder(getActivity());

        String ok = getString(R.string.info_popup_ok);


        if (response.contains("cs_")){
            dialog.setTitle(R.string.res_popup_title);
            dialog.setMessage(R.string.res_popup_desc);
        }
        else{
            dialog.setTitle(R.string.res_popup_err_title);
            dialog.setMessage(R.string.res_popup_err_desc);
        }
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                getActivity().onBackPressed();
            }
        });


        dialog.show();
    }
}

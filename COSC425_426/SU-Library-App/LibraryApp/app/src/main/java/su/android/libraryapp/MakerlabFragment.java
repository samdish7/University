package su.android.libraryapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TimeZone;

/**
 * @author Declan Sheehan
 * Date: November 14th, 2020.
 * Description: The MakerlabFragment gets three URLs (today_url, other_hours_url, and printer_url)
 * then formats them according to the current date (formatURLs), and grabs the JSON info for all
 * three links (runnable classes). If data returns empty, it will avoid parsing the JSON data,
 * BUT if it returns properly, it will parse the data (parseMakerlabinfo), and inflate the data
 * (addTodayLayout, addOtherHoursLayout, & addDeviceLayout).
 */

public class MakerlabFragment extends Fragment {

    ActionBar toolbar; // Makerlab topbar
    LibraryHoursFragment lib = new LibraryHoursFragment(); // To get getMonth, getDay ... funcs.

    // String arrays to hold Makerlab hours info.
    String[] other_dates = new String[32];
    String[] methods = new String[32];

    // Strings arrays to hold Makerlab printer info.
    String[] printer_names = new String[32]; // Name
    String[] printer_type = new String[32]; // Type (3D Printer / Laser Cutter / etc)
    String[] printer_text = new String[32]; // Status (In use / Available / etc)
    String[] printer_avail = new String[32]; // Availability details (Available Nov 13 at 9:00AM)

    // Three booleans that determines whether today, otherhours, or printer JSON strings returned
    // empty after JSON retrieval.
    Boolean[] json_availability;
    Boolean isConnected;

    // Strings for URLs and output strings.
    String today_url, today_json_string, today_method,
            other_hours_url, other_hours_string,
            print_url, printer_json_string;

    ViewGroup makerlab;

    // Calendar for determining days, weeks, months, years...
    Calendar calendar;

    public MakerlabFragment() {
        // Required empty public constructor
    }

    public static MakerlabFragment newInstance(String param1, String param2) {
        MakerlabFragment fragment = new MakerlabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If there is no internet connection, inflate the fragment connection error fragment.
        ConnectivityManager cm =
                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected)
            return inflater.inflate(R.layout.fragment_connection_error, container, false);
        else
            return inflater.inflate(R.layout.fragment_makerlab, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set the title of the toolbar.
        toolbar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        toolbar.setTitle("MakerLab");

        if (isConnected) {
            // Get the viewgroup for the Makerlab fragment.
            makerlab = view.findViewById(R.id.makerlab);

            // Initialize the strings and booleans.
            today_json_string = other_hours_string = printer_json_string = "";
            json_availability = new Boolean[3];
            for (int i = 0; i < 32; i++) {
                printer_names[i] = "";
                printer_type[i] = "";
                printer_text[i] = "";
                printer_avail[i] = "";
                other_dates[i] = "";
                methods[i] = "";
            }

            // Set an onclick listener to open a webpage to schedule an appointment for the Makerklab.
            ViewGroup make_app_btn = view.findViewById(R.id.makerlab_make_appt);
            make_app_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri webpage = Uri.parse(getResources().getString(R.string.appointment_url));
                    Intent webpage_intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (webpage_intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(webpage_intent);
                    }
                }
            });

            // Click this button to open/close the hours & availability list.
            ViewGroup apptbtn = view.findViewById(R.id.appointment_label);
            final ViewGroup hrs_avail = view.findViewById(R.id.appointments_times);
            apptbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (hrs_avail.getVisibility() == View.VISIBLE)
                        hrs_avail.setVisibility(View.GONE);
                    else
                        hrs_avail.setVisibility(View.VISIBLE);

                }
            });

            // Click this button to open/close the device list.
            ViewGroup devicesbtn = view.findViewById(R.id.device_label);
            final ViewGroup devices_avail = view.findViewById(R.id.devices);
            devicesbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (devices_avail.getVisibility() == View.VISIBLE)
                        devices_avail.setVisibility(View.GONE);
                    else
                        devices_avail.setVisibility(View.VISIBLE);

                }
            });

            // Added an intent to access the Makerlab polices page.
            ViewGroup policiesbtn = view.findViewById(R.id.makerlab_polices);
            policiesbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri webpage = Uri.parse(getResources().getString(R.string.policies_url));
                    Intent webpage_intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (webpage_intent.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(webpage_intent);
                    }
                }
            });


            // Add an email intent to contact the Makerlab.
            ViewGroup contact = view.findViewById(R.id.makerlab_info);
            final String[] TO = {getResources().getString(R.string.makerlab_email)};
            contact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.setData(Uri.parse("mailto:"));
                    email.setType("text/plain");
                    email.putExtra(Intent.EXTRA_EMAIL, TO);
                    if (email.resolveActivity(getActivity().getPackageManager()) != null) {
                        startActivity(Intent.createChooser(email, "Choose an Email client:"));
                    }
                }
            });


            // Format all three URLs.
            formatURLs();

            // Run threads for each JSON retrievals.
            Thread thread1 = new Thread(new getTodayHours());
            Thread thread2 = new Thread(new getOtherHours());
            Thread thread3 = new Thread(new getPrinterInfo());

            try {
                thread1.start();
                thread1.join();
                thread2.start();
                thread2.join();
                thread3.start();
                thread3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Add "Data Unavailable" textviews to the activity if json strings return empty.
            handleEmptyJSON();

            // Parse the JSON information.
            parseMakerlabinfo();

            // Add each view element to the fragment.
            if (json_availability[0])
                setTodayHours();
            if (json_availability[1])
                addOtherHoursLayout();
            if (json_availability[2])
                addDevicesLayout();
        }
    }

    // Runnable class for parsing today's Makerlab information.
    private class getTodayHours implements Runnable {
        @Override
        public void run () {
            try {
                HttpURLConnection conn;
                URL url; // URL object
                StringBuilder response = new StringBuilder(); // Allows string appending
                String inputLine, output; // Buffer for inputStream
                try {
                    url = new URL(today_url); // url passed in
                    try {
                        conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) { }
                    } catch (IOException e) { }
                } catch (MalformedURLException e) { }
                today_json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) { }
        }
    }

    // Runnable class for parsing other days Makerlab information.
    private class getOtherHours implements Runnable {
        @Override
        public void run () {
            try {
                HttpURLConnection conn;
                URL url; // URL object
                StringBuilder response = new StringBuilder(); // Allows string appending
                String inputLine, output; // Buffer for inputStream
                try {
                    url = new URL(other_hours_url); // url passed in
                    try {
                        conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) { }
                    } catch (IOException e) { }
                } catch (MalformedURLException e) { }
                other_hours_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) { }
        }
    }

    // Runnable class for parsing current printer information.
    private class getPrinterInfo implements Runnable {
        @Override
        public void run() {
            try {
                HttpURLConnection conn;
                URL url; // URL object
                StringBuilder response = new StringBuilder(); // Allows string appending
                String inputLine, output; // Buffer for inputStream
                try {
                    url = new URL(print_url); // url passed in
                    try {
                        conn = (HttpURLConnection) url.openConnection(); // Opens new connection
                        conn.setConnectTimeout(5000); // Aborts connection if connection takes too long
                        conn.setRequestMethod("GET"); // Requests to HTTP that we want to get something from it
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // BufferedReader object
                        try {
                            while ((inputLine = br.readLine()) != null) // While there are more contents to read
                                response.append(inputLine); // Append the new data to all grabbed data
                            br.close(); // Close connection
                        } catch (IOException e) { }
                    } catch (IOException e) { }
                } catch (MalformedURLException e) { }
                printer_json_string = response.toString(); // Sets string in parent class to be the string taken from the URL
            } catch (Exception e) { }
        }
    }

    // This function checks if the json string returned is empty, then adds an unavailability
    // layout in place of today's hours, other Makerlab hours, and for printer devices.
    private void handleEmptyJSON() {
        if (today_json_string.isEmpty())
            json_availability[0] = false;
        else
            json_availability[0] = true;

        if (other_hours_string.isEmpty())
            json_availability[1] = false;
        else
            json_availability[1] = true;

        if (printer_json_string.isEmpty())
            json_availability[2] = false;
        else
            json_availability[2] = true;

    }

    // Function for initializing the URL strings, and formatting the other-hours URL.
    private void formatURLs() {
        // Note: The other_hours_url format requires you to replace the first %@ with todays date
        // and the second %@ with (at max) 100 days from now in the YYYY-MM-DD format.
        // https://salisbury.libcal.com/api/1.0/hours/4200?key=aa98e9bc4b81977a5f0cf397d73ddfa1&from=%@&to=%@
        calendar = Calendar.getInstance(TimeZone.getDefault());
        print_url = getResources().getString(R.string.makerlabprinter_url);
        today_url = getResources().getString(R.string.makerlabhr_url);
        other_hours_url = "https://salisbury.libcal.com/api/1.0/hours/4200?key=aa98e9bc4b81977a5f0cf397d73ddfa1&from=";
        String todays_date,  new_date;
        todays_date = new_date = "";
        int new_year, new_month, new_day;
        new_year = new_month = new_day = 0;
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(calendar.MONTH) + 1;
        int day = calendar.get(calendar.DAY_OF_MONTH);

        // Format the second YYYY-MM-DD to be one month later than the first (todays) date.
        if (month < 12) {
            new_day = day;
            new_month = month + 1;
            new_year = year;
        } else if (month == 12) {
            new_day = day;
            new_month = 1;
            new_year = year + 1;
        }

        if (month > 9)
            todays_date = year + "-" + month;
        else
            todays_date = year + "-0" + month;

        if (day > 9)
            todays_date = todays_date + "-" + day;
        else
            todays_date = todays_date + "-0" + day;

        if (new_month > 9)
            new_date = new_year + "-" + new_month;
        else
            new_date = new_year + "-0" + new_month;

        if (day > 9)
            new_date = new_date + "-" + new_day;
        else
            new_date = new_date + "-0" + new_day;

        other_hours_url = other_hours_url + todays_date + "&to=" + new_date;
    }

    private void parseMakerlabinfo() {
        try {
            // Parse todays hours information (if data returned):
            if (json_availability[0]) {
                JSONObject j1 = new JSONObject(today_json_string);
                JSONObject data = new JSONObject(j1.getJSONArray("locations").getString(0));
                today_method = data.getString("rendered");
            }

            // Parse other hours information (if data returned):
            if (json_availability[1]) {
                JSONArray arr = new JSONArray(other_hours_string);
                JSONObject j2 = arr.getJSONObject(0);
                JSONObject dates = j2.getJSONObject("dates");
                Iterator<String> keys = dates.keys();

                int count = 0;
                while (keys.hasNext()) {
                    if (count >= 31)
                        break;
                    String key = keys.next();
                    other_dates[count] = key;
                    methods[count] = dates.getJSONObject(key).getString("status");
                    count++;
                }
            }

            // Parse the printer information (if data returned):
            if (json_availability[2]) {
                JSONArray j3 = new JSONArray(printer_json_string);
                for (int i = 0; i < j3.length(); i++) {
                    JSONObject device = j3.getJSONObject(i);
                    JSONObject printer = device.getJSONObject("printer");
                    JSONObject printer_av = device.getJSONObject("availability");

                    printer_names[i] = printer.getString("device_name");
                    printer_type[i] = printer.getString("device_type");
                    printer_text[i] = printer_av.getString("text");

                    Iterator<String> printer_keys = printer_av.keys();

                    while (printer_keys.hasNext()) {
                        String key = printer_keys.next();
                        if (key.equals("available")) {
                            printer_avail[i] = printer_av.getString("available");
                        }
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Function to add today's hours information to the fragment.
    private void setTodayHours() {
        // Assign the corresponding textviews to the makerlab_item's textviews.
        ViewGroup today_layout = makerlab.findViewById(R.id.makerlab_today);
        TextView tv_today_hrs = today_layout.findViewById(R.id.today_hours);
        if (today_method.equalsIgnoreCase("closed")) {
            tv_today_hrs.setText(today_method);
            tv_today_hrs.setTextColor(getResources().getColor(R.color.color_red, null));
        } else if (today_method.equalsIgnoreCase("open")) {
            tv_today_hrs.setText(today_method);
            tv_today_hrs.setTextColor(getResources().getColor(R.color.color_green, null));
        } else if (today_method.equalsIgnoreCase("by appointment")){
            tv_today_hrs.setText(today_method);
        } else {
            tv_today_hrs.setText(today_method);
            tv_today_hrs.setTextSize(12); // Make it smaller in order to fit it on the screen.
        }
    }

    // Function to add other "hours" (today TO ~(today + 30 days)) info to the fragment.
    private void addOtherHoursLayout(){
        // For each grabbed day:
        ViewGroup appointments = makerlab.findViewById(R.id.appointments_times);
        for (int count = 0; count < other_dates.length; count++) {
            if (!other_dates[count].isEmpty() && count != 0) {
                // Inflate a list_item_4 and format margin/spacing parameters.
                View otherHour = LayoutInflater.from(getContext()).inflate(R.layout.list_item_4, appointments, false);
                LayoutParams otherHour_params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                otherHour_params.setMargins(0, 0, 0, 0);
                otherHour.setLayoutParams(otherHour_params);

                // Get the month from that days value (Jan, Feb, Mar, etc)
                String[] substr = other_dates[count].split("-");
                String mon = lib.getMonth(other_dates[count]);
                // Get the day of the month.
                String day = lib.getDay(other_dates[count]);
                calendar.set(calendar.MONTH, Integer.parseInt(substr[1]) - 1);
                calendar.set(calendar.DAY_OF_MONTH, Integer.parseInt(substr[2]));
                String dayofwk = lib.getDayOfWeek(calendar.get(calendar.DAY_OF_WEEK));

                // Get corresponding Textviews to display information.
                TextView tv_month = otherHour.findViewById(R.id.text_item4_1);
                TextView tv_day = otherHour.findViewById(R.id.text_item4_2);
                TextView tv_dayofwk = otherHour.findViewById(R.id.text_item4_3);
                TextView tv_status = otherHour.findViewById(R.id.text_item4_4);

                // Set the textview texts.
                tv_month.setText(mon);
                tv_day.setText(day);
                tv_dayofwk.setText(dayofwk);

                if (methods[count].equalsIgnoreCase("closed")) {
                    tv_status.setText("Closed");
                } else if (methods[count].equalsIgnoreCase("byapp")){
                    tv_status.setText("Schedule by Appointment");
                } else {
                    tv_status.setText(methods[count]);
                }

                // Add the appointments to the ViewGroup.
                appointments.addView(otherHour);
            }
        }
    }

    // Function to add Makerlab device information to the fragment.
    public void addDevicesLayout() {
        ViewGroup devices = makerlab.findViewById(R.id.devices);
        // For each grabbed printer/device:
        for (int count = 0; count < printer_names.length; count++) {
            if (!printer_names[count].isEmpty()){
                // Inflate a new makerlab_item 2 and set margin/space parameters.
                View printerDevices = LayoutInflater.from(getContext()).inflate(R.layout.makerlab_item1, devices, false);
                LayoutParams device_params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                device_params.setMargins(0, 0, 0, 0);
                printerDevices.setLayoutParams(device_params);

                // Get corresponding textviews in the makerlab_item 2
                TextView title = printerDevices.findViewById(R.id.name);
                TextView type = printerDevices.findViewById(R.id.type);
                TextView status = printerDevices.findViewById(R.id.status);

                // Set the text for each textview depending on parsed data.
                title.setText(printer_names[count]);
                type.setText("(" + printer_type[count] + ")");
                status.setText(printer_text[count]);

                if (printer_text[count].equalsIgnoreCase("available")){
                    status.setTextColor(getResources().getColor(R.color.color_green, null));
                } else if (printer_text[count].equalsIgnoreCase("holding")){
                    status.setTextColor(getResources().getColor(R.color.color_yellow, null));
                } else if (printer_text[count].equalsIgnoreCase("in use")){
                    status.setTextColor(getResources().getColor(R.color.color_red, null));
                }

                TextView avail = printerDevices.findViewById(R.id.avail);
                if (!printer_avail[count].isEmpty()){
                    avail.setText("Available: " + printer_avail[count]);
                } else {
                    avail.setText("Currently available for walk-in patrons.");
                }

                // Add the view to the fragment.
                devices.addView(printerDevices);
            }
        }
    }

}
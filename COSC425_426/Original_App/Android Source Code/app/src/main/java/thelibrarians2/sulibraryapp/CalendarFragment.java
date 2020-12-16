package thelibrarians2.sulibraryapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * general fragment for HomeFragment's viewpager
 */

public class CalendarFragment extends Fragment {

    String date, rendered;
    boolean hasInternet = false;
    int position = 0;
    TextView day;
    TextView month;
    TextView times;
    TextView weekday;
    //TextView left_arrow, right_arrow;

    public CalendarFragment() {}

    public CalendarFragment(int p) {
        position = p;
    }

    public CalendarFragment(JSONObject j, int p) {
        position = p;
        hasInternet = true;
        try {
            date = j.getString("date");
            rendered = j.getString("rendered");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public CalendarFragment(JSONObject j) {
        hasInternet = true;
        try {
            date = j.getString("date");
            rendered = j.getString("rendered");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calendar, container, false);

        day = (TextView) v.findViewById(R.id.day);     //numeric day
        month = (TextView) v.findViewById(R.id.month); //month name
        times = (TextView) v.findViewById(R.id.times); //open hours
        weekday = (TextView) v.findViewById(R.id.weekday); //day of the week name
        //left_arrow = (TextView) v.findViewById(R.id.left_arrow);
        //right_arrow = (TextView) v.findViewById(R.id.right_arrow);
/*

        if(position == 0) {
            left_arrow.setText("");
            right_arrow.setText(">");
        } else if(position == 6) {
            right_arrow.setText("");
            left_arrow.setText("<");
        } else {
            left_arrow.setText("<");
            right_arrow.setText(">");
        }
*/

        day.setTypeface(null, Typeface.BOLD);
        month.setTypeface(null, Typeface.BOLD);

        if(hasInternet) {
            day.setText(getDay(date));
            month.setText(getMonth(date));
            times.setText(getTime(rendered));
        } else {
            day.setText("Unavailable");
            month.setText("");
            times.setText("Unavailable");
        }


        Calendar cal = Calendar.getInstance();
        weekday.setText(findWeekday(cal.get(Calendar.DAY_OF_WEEK))); //if using viewpager seven day week, add '+ position' inside get()



        return v;
    }

    private String findWeekday(int dayOfWeek) {

        if(dayOfWeek > 7) dayOfWeek -= 7;

        switch(dayOfWeek) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
        }

        return "Unavailable";
    }

    private String getDay(String d) {
        String[] parts = d.split("-");
        return parts[2];
    }

    private String getMonth(String d) {
        String[] parts = d.split("-");
        int month = Integer.parseInt(parts[1]);

        switch(month) {
            case 1:
                return "JANUARY";
            case 2:
                return "FEBRUARY";
            case 3:
                return "MARCH";
            case 4:
                return "APRIL";
            case 5:
                return "MAY";
            case 6:
                return "JUNE";
            case 7:
                return "JULY";
            case 8:
                return "AUGUST";
            case 9:
                return "SEPTEMBER";
            case 10:
                return "OCTOBER";
            case 11:
                return "NOVEMBER";
            case 12:
                return "DECEMBER";
        }

        return "unavailable";
    }
    private String getTime(String hours){  //takes the JSON information and return the specified hour range

        switch(hours){ //hours from JSON are as follows
            case "7:30am - 8pm":
                return "7:30am - 8:00pm";
            case "10am - 8pm":
                return "10:00am - 8:00pm";
            case "11am - 2am":
                return "11:00am - 2:00am";
            case "7:30am - 2am":
                return "7:30am - 2:00am";
            case "7:30am - 5pm":
                return "7:30am - 5:00pm";
            case "7:30am - 4pm":
                return "7:30am - 4:00pm";
            case "Closing at 7 PM":
                return "Closing at 7 PM";
            case "24 Hours":
                return "Open 24 Hours";
            case "Library Closed":
                return "Library Closed";
            case "Open at 7:30 AM":
                return "Open at 7:30 AM";
            case "10am - 2am":
                return "10:00am - 2:00am";
            case "10am - 10pm":
                return "10:00am - 10:00pm";
        }
        return "unavailable"; //return unavailable if none of the above options are pulled
    }

}

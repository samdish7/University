package thelibrarians2.sulibraryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarListItem implements ListItem {
    private final int type;

    private final View view;

    CalendarListItem(Activity a, String s1, String s2) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.calendar_list_item, null);
        type = 10;
        TextView tv1 = view.findViewById(R.id.calendar_1);
        tv1.setText(s1);
        TextView tv2 = view.findViewById(R.id.calendar_2);
        tv2.setText(s2);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        return (LinearLayout) view.findViewById(R.id.layout_calendar);
    }

}

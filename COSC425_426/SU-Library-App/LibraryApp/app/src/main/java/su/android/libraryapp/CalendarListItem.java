package su.android.libraryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarListItem implements ListItem {
    private int type;
    private TextView tv1;
    private TextView tv2;
    private LinearLayout layout;
    private ImageView img;

    private LayoutInflater inflater;
    private View view;

    CalendarListItem(Activity a, String s1, String s2) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.calendar_list_item, null);
        type = 10;
        tv1 = (TextView) view.findViewById(R.id.calendar_1);
        tv1.setText(s1);
        tv2 = (TextView) view.findViewById(R.id.calendar_2);
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
        layout = (LinearLayout) view.findViewById(R.id.layout_calendar);
        return layout;
    }

    public TextView getTextView1() {
        return tv1;
    }

    public TextView getTextView2() {
        return tv2;
    }

}

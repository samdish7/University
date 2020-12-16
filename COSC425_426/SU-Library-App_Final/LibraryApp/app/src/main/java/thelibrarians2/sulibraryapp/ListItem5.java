package thelibrarians2.sulibraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by njraf_000 on 2/9/2017.
 * Revamped by Sam Disharoon & Jordan Welch in 2020
 * Used for "Today" in Library Hours
 */

public class ListItem5 implements ListItem {

    private final int type;

    private final View view;

    // CTOR
    @SuppressLint("InflateParams")
    ListItem5(Activity a, String s1, String s2) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_5, null);
        type = 5;
        TextView tv1 = view.findViewById(R.id.text_item5_1);
        tv1.setText(s1);
        TextView tv2 = view.findViewById(R.id.text_item5_2);
        tv2.setText(s2);
    }

    // returns type of list
    public int getType() {
        return type;
    }

    // returns view
    public View getView() {
        return view;
    }

    // returns layout
    public LinearLayout getLayout() {
        return (LinearLayout) view.findViewById(R.id.layout_item5);
    }

}

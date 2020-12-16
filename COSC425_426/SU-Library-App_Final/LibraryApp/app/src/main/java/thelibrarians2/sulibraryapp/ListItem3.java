package thelibrarians2.sulibraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by njraf_000 on 2/15/2017.
 * Revamped by Sam Disharoon & Jack Stoetzel
 * Used in Subject Detailed Fragment
 */

public class ListItem3 implements ListItem {
    private final int type;
    private final View view;
    private final TextView tv2;

    @SuppressLint("InflateParams")
    ListItem3(Activity a, String s1, String s2) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_3, null);
        type = 3;
        TextView tv1 = view.findViewById(R.id.text_item3_1);
        tv1.setText(s1);
        tv2 = view.findViewById(R.id.text_item3_2);
        tv2.setText(s2);
    }

    // get type of list
    @Override
    public int getType() {
        return type;
    }

    // returns view
    @Override
    public View getView() {
        return view;
    }

    // returns layout
    public RelativeLayout getLayout() {
        return view.findViewById(R.id.layout_item3);
    }

    // returns textView 2
    public TextView getTextView2() {
        return tv2;
    }

}

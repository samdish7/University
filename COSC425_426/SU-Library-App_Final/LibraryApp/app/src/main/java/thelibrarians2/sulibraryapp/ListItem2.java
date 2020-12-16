package thelibrarians2.sulibraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by njraf_000 on 2/11/2017.
 * Revamped by Sam Disharoon, Jack Stoetzel, Jordan Welch & Declan Sheehan in 2020
 * Used in Device Availability and Contact Information
 */

public class ListItem2 implements ListItem {
    private int type = -1;
    private TextView tv1;
    private TextView tv2;
    private final ImageView img;

    private View view;

    // CTOR
    ListItem2(Activity a, int i, String s1, String s2) {
        init(a, s1, s2);
        img = view.findViewById(R.id.image_item2);
        img.setImageResource(i);
        img.setTag(i);
    }

    // helper function for initializing list
    @SuppressLint("InflateParams")
    private void init(Activity a, String s1, String s2) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_2, null);
        type = 2;
        tv1 = view.findViewById(R.id.text_item2_1);
        tv1.setText(s1);
        tv2 = view.findViewById(R.id.text_item2_2);
        tv2.setText(s2);
    }

    // returns type of list
    @Override
    public int getType() {
        return type;
    }

    // returns view
    @Override
    public View getView() {
        return view;
    }

    // gets Layout
    public LinearLayout getLayout() {
        return (LinearLayout) view.findViewById(R.id.layout_item2);
    }

    // return textView 1
    public TextView getTextView1() {
        return tv1;
    }

    // return textView 2
    public TextView getTextView2() {
        return tv2;
    }

    // return ImageView
    public ImageView getImageView() {
        return img;
    }
}

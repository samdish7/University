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
 * Created by njraf_000 on 2/9/2017.
 * Revamped by Sam Disharoon, Jack Stoetzel, Declan Sheehan, & Jordan Welch in 2020.
 * Description: Used in Helpful Links, Contact Information, and Research Help to display useful
 * information regarding the SU library
 */

public class ListItem0 implements ListItem {

    // Define class variables.
    private final ImageView arrow;
    private final TextView tv;
    private final View view;
    private final int type;

    // Constructor
    @SuppressLint("InflateParams")
    ListItem0(Activity a, String s) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_0, null);
        type = 0;
        tv = view.findViewById(R.id.text_item0);
        arrow = view.findViewById(R.id.arrow_right);
        arrow.setVisibility(View.INVISIBLE);
        tv.setText(s);
    }

    // Returns the type number.
    public int getType() {
        return type;
    }

    // Returns the view.
    public View getView() {
        return view;
    }

    // Returns the id number.
    public LinearLayout getLayout() {
        return (LinearLayout) view.findViewById(R.id.layout_item0);
    }

    // Returns the textview.
    public TextView getTextView() {
        return tv;
    }

    // Sets arrow to visible.
    public void seeArrow() {arrow.setVisibility(View.VISIBLE);}

}

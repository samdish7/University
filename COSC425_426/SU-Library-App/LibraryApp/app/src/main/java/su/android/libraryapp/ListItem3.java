package su.android.libraryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by njraf_000 on 2/15/2017.
 * Revamped by Sam Disharoon & Jack Stoetzel
 * Used in Subject Detailed Fragment
 */

public class ListItem3 implements ListItem {
    private int type = -1;
    private TextView tv1;
    private TextView tv2;
    private RelativeLayout layout;

    private LayoutInflater inflater;
    private View view;

    // CTOR
    ListItem3(Activity a, String s1, String s2) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_3, null);
        type = 3;
        tv1 = view.findViewById(R.id.text_item3_1);
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
        layout = view.findViewById(R.id.layout_item3);
        return layout;
    }

    // returns textView 1 (Unsure if used anywhere)
    public TextView getTextView1() {
        return tv1;
    }

    // returns textView 2
    public TextView getTextView2() {
        return tv2;
    }

}

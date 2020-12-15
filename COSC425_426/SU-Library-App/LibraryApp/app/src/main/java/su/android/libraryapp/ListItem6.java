package su.android.libraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by njraf_000 on 2/9/2017
 * revamped by Sam Disharoon in 2020
 * Used in News Fragment
 */

public class ListItem6 implements ListItem {

    private int type = 0;
    private ImageView iv;
    private TextView tv1, tv2, tv3;

    private LayoutInflater inflater;
    private View view;

    // CTOR
    ListItem6(Activity a, Bitmap bm, String s1, String s2, String s3) {

        init(a, s1, s2, s3);
        ImageView iv = view.findViewById(R.id.image_item6);
        iv.setImageBitmap(bm);

    }
    // helper function to initialize the list item
    @SuppressLint("InflateParams") //Added to get rid of passing null warning
    private void init(Activity a, String s1, String s2, String s3) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_6, null);
        type = 6;
        tv1 = view.findViewById(R.id.text_item6_1);
        tv1.setText(s1);
        tv2 = view.findViewById(R.id.text_item6_2);
        tv2.setText(s2);
        tv3 = view.findViewById(R.id.text_item6_3);
        tv3.setText(s3);
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
        return (LinearLayout) view.findViewById(R.id.layout_item6);
    }

    // returns imageView (not sure if used)
    public ImageView getImageView() {
        return iv;
    }

    // returns textView1 (not sure if used)
    public TextView getTextView1() {
        return tv1;
    }

    // returns textView2 (not sure if used)
    public TextView getTextView2() {
        return tv2;
    }

    // returns textView3
    public TextView getTextView3() {
        return tv3;
    }

}

package su.android.libraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by njraf_000 on 2/11/2017.
 * Used in Study Room List Fragment
 */

public class ListItem1 implements ListItem {

    private int type = -1;
    private TextView tv;
    private ImageView arrow;
    private View view;

    ListItem1(Activity a, int i, String s) {
        init(a, s);
        ImageView img = view.findViewById(R.id.image_item1);
        img.setImageResource(i);
        img.setTag(i);
    }

    @SuppressLint("InflateParams")
    private void init(Activity a, String s) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_1, null);
        type = 1;
        tv = view.findViewById(R.id.text_item1);
        tv.setText(s);
        arrow= view.findViewById(R.id.arrow_right_1);
        arrow.setVisibility(View.VISIBLE);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public View getView() {
        return view;
    }

    public RelativeLayout getLayout() {
        return  view.findViewById(R.id.layout_item1);
    }

    public TextView getTextView() {
        return tv;
    }

}

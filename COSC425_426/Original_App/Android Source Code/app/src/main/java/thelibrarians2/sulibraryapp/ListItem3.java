package thelibrarians2.sulibraryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by njraf_000 on 2/15/2017.
 */

public class ListItem3 implements ListItem {
    private int type = -1;
    private TextView tv1;
    private TextView tv2;
    private LinearLayout layout;
    private ImageView img;

    private LayoutInflater inflater;
    private View view;

    ListItem3(Activity a, String s1, String s2) {
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.list_item_3, null);
        type = 3;
        tv1 = (TextView) view.findViewById(R.id.text_item3_1);
        tv1.setText(s1);
        tv2 = (TextView) view.findViewById(R.id.text_item3_2);
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
        layout = (LinearLayout) view.findViewById(R.id.layout_item3);
        return layout;
    }

    public TextView getTextView1() {
        return tv1;
    }

    public TextView getTextView2() {
        return tv2;
    }

}

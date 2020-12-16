package thelibrarians2.sulibraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class HomeListItem implements ListItem {

    private int type;
    private TextView tv;
    private View view;

    HomeListItem(Activity a, int i, String s) {
        init(a, s);
        ImageView img = view.findViewById(R.id.image_itemHome);
        img.setImageResource(i);
        img.setTag(i);
    }
    @SuppressLint("InflateParams") //Added to get rid of passing null warning
    private void init(Activity a, String s) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.home_list_item, null);
        type = 9;
        tv = view.findViewById(R.id.text_itemHome);
        tv.setText(s);
    }

    public int getType() {
        return type;
    }

    public View getView() {
        return view;
    }

    public LinearLayout getLayout() {
        return (LinearLayout) view.findViewById(R.id.layout_itemHome);
    }

    public TextView getTextView() {
        return tv;
    }

}

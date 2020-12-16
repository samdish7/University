package thelibrarians2.sulibraryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AlertListItem implements ListItem {

    private final int type;
    private final TextView tv;
    private final View view;

    // constructor
    AlertListItem(Activity a, String s) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.alert_list_item, null);
        type = 11;
        tv = view.findViewById(R.id.text_item);
        tv.setText(s);
    }

    // returns the type number
    public int getType() {
        return type;
    }

    // returns the view
    public View getView() {
        return view;
    }

    // returns the id number
    public LinearLayout getLayout() {
        return (LinearLayout) view.findViewById(R.id.layout_item);
    }

    // returns the textview
    public TextView getTextView() {
        return tv;
    }

}

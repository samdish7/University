package thelibrarians2.sulibraryapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;


public class ContactListItem implements ListItem {
    private int type = -1;
    private TextView tv2;

    private View view;

    public ContactListItem(FragmentActivity activity, int icon, String name, String title, String phone, String email) {
        init(activity, name, title, phone, email);
        ImageView img = view.findViewById(R.id.image);
        img.setImageResource(icon);
        img.setTag(icon);
    }

    @SuppressLint("InflateParams")
    private void init(Activity a, String s1, String s2, String s3, String s4) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.contact_list_item, null);
        type = 7;
        TextView tv1 = view.findViewById(R.id.name);
        tv1.setText(s1);
        tv2 = view.findViewById(R.id.title);
        tv2.setText(s2);
        TextView tv3 = view.findViewById(R.id.phone);
        tv3.setText(s3);
        TextView tv4 = view.findViewById(R.id.email);
        tv4.setText(s4);
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public View getView() {
        return view;
    }

    public TextView getTextView2() {
        return tv2;
    }

}

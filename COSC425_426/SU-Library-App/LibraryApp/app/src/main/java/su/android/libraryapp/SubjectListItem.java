package su.android.libraryapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


public class SubjectListItem implements ListItem {

    private int type = -1;
    private TextView tv;
    private View view;

    SubjectListItem(Activity a, int i, String s, String s2) {


        init(a, s);
        ImageView img = view.findViewById(R.id.image_item1);
        img.setImageResource(i);
        img.setBackground(ContextCompat.getDrawable(img.getContext(), R.drawable.custom_circle));
        int color = img.getResources().getIdentifier("school_"+s2, "color", "su.android.libraryapp");
        img.setBackgroundTintList(ContextCompat.getColorStateList(img.getContext(), color));
        img.setTag(i);
    }

    //    TODO: Warning for passing null
    private void init(Activity a, String s) {
        LayoutInflater inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.subject_list_item, null); // fixed, was pulling from list item 1 initially
        type = 8;
        tv = view.findViewById(R.id.text_item1);
        ImageView arrow = view.findViewById(R.id.sub_arrow_right);
        arrow.setVisibility(View.VISIBLE);
        tv.setText(s);
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
        return (RelativeLayout) view.findViewById(R.id.layout_item1);
    }

    public TextView getTextView() {
        return tv;
    }

}

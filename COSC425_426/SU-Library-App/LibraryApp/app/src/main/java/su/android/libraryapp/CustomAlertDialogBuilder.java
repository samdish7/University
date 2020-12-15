package su.android.libraryapp;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by njraf_000 on 3/23/2017
 * Revamped by Jordan Welch & Sam Disharoon in 2020
 * Builds a custom dialog to displayed when needed
 * Used in HomeFragment for the search and alert functions
 */

public class CustomAlertDialogBuilder extends AlertDialog {

    private final TextView mTitle;
    private final TextView mMessage;

    // main CTOR
    public CustomAlertDialogBuilder(Context context) {
        super(context);

        View customTitle = View.inflate(context, R.layout.alert_dialog_title, null);
        mTitle = customTitle.findViewById(R.id.alert_title);
        setCustomTitle(customTitle);

        View customMessage = View.inflate(context, R.layout.alert_dialog_message, null);
        mMessage = customMessage.findViewById(R.id.dialog_message);
        setView(customMessage);

    }

    // creates the view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // sets titles and messages by ID
    public void setTitle(int textResId) {
        mTitle.setText(textResId);
    }
    public void setMessage(int textResId) {
        mMessage.setText(textResId);
    }

    // sets tites and messages by hardcoded string
    public void setTitle(CharSequence text) {
        mTitle.setText(text);
    }
    public void setMessage(CharSequence text) {
        mMessage.setText(text);
    }

}
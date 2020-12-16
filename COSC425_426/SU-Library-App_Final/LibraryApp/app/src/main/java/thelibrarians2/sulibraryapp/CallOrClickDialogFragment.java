package thelibrarians2.sulibraryapp;

/*This fragment opens a dialog box on the screen within the Contact info fragment, which asks the
user if they want to either call or email a specified staff member. Selecting the Phone number
will start a phone call with that staff member; selecting the email address will start an email to them;
 Clicking cancel will close the fragment
*/

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.json.JSONException;
import org.json.JSONObject;


public class CallOrClickDialogFragment extends DialogFragment  {

    Activity act;
    String staff;
    String email;
    String phone;

    // Constructor passes activity object
    public CallOrClickDialogFragment(Activity act, JSONObject person) throws JSONException {
        this.act = act;
        staff = person.getString("name");
        email = person.getString("email");
        phone = person.getString("phone");
    }

    // Creates dialog
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use the Builder class for convenient dialog construction
        String ask = act.getResources().getString(R.string.contact_ask);
        ask = ask.concat(" ");
        ask = ask.concat(staff);//concatenates staff name to ask string
        ask = ask.concat("?");
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if(email.compareTo("") != 0) {
            String[] items = {"Call", "Email", "Cancel"};
            builder = builder.setTitle(ask).setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            Intent dialer = new Intent(Intent.ACTION_DIAL); // Creates a new phone intent
                            dialer.setData(Uri.parse("tel:" + phone)); // Passes URI to intent
                            startActivity(dialer); // Starts activity
                            break;
                        case 1:
                            Intent emailer = new Intent(Intent.ACTION_SEND); // Creates a new email send intent
                            emailer.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Sets flags for new intent
                            emailer.setType("vnd.android.cursor.item/email"); // Sets type for new intent
                            emailer.putExtra(Intent.EXTRA_EMAIL, new String[]{email}); // Adds email address as default in To: field
                            startActivity(emailer); // Starts activity
                            break;
                        case 2:
                            dismiss(); // Closes dialog
                            break;
                    }
                }
            });
        }
        else{
            String[] items = {"Call", "Cancel"};
            builder = builder.setTitle(ask).setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            Intent dialer = new Intent(Intent.ACTION_DIAL); // Creates a new phone intent
                            dialer.setData(Uri.parse("tel:" + phone)); // Passes URI to intent
                            startActivity(dialer); // Starts activity
                            break;
                        case 1:
                            dismiss(); // Closes dialog
                            break;
                    }
                }
            });
        }
        return builder.create(); // creates builder
    }
}


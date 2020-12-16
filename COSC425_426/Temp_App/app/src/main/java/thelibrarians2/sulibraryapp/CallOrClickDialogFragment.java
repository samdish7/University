package thelibrarians2.sulibraryapp;

/**
 * Created by Xopher on 10/21/2016.
 */

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
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AlertDialog;


public class CallOrClickDialogFragment extends DialogFragment  {

    Activity act; // Activity needed because Fragment not "attached"

    //import string array containing names of staff
    String[] names;

    //create strings whose contents will be determined by arguments
    String staff; // Assigns name
    String email; // Assigns email
    String phone; // Assigns phone

    // Constructor passes activity object
    public CallOrClickDialogFragment(Activity act){
        this.act = act; // Allocates value to local activity object
        names = act.getResources().getStringArray(R.array.contact_who);
        //initializes strings
        staff = "";
        email = "";
        phone = "";
    }

    // Creates dialog
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments(); // Gets passed int value from ContactInfoFragment
        int position = args.getInt("position", 0);//get arguments and pass to integer

        staff = names[position-4]; // Assigns name
        //depending on case from which fragment is created, diff args are passed for diff staff
        switch (position) {
            /*if in ContactInfoFragment, the 16th item is selected, the CallorClickDialofFragment will be launched
            * diplaying contact info regarding Jan Bellistri. If the 17th item is selected, the fragment will be
            * launched displaying contact info for Susan Brazer; and so on up until the last item in the list*/
            case 16:// Susan Brazrer
                email = "sebrazer@salisbury.edu";
                phone = "tel:4105464370";
                break;
            case 17:// Cindy Causey
                email = "clcausey@salisbury.edu";
                phone = "tel:4105436026";
                break;
            case 18:// Mou Chakraborty
                email = "mxchakraborty@salisbury.edu";
                phone = "tel:4105436131";
                break;
            case 19:// Stacy Cooper
                email = "srcooper@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 20:// Beverly Dennis
                email = "bddennis@salisbury.edu";
                phone = "tel:4105436132";
                break;
            case 21:// Jesse Drewer
                email = "jdrewer@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 22:// Caroline Eckhardt
                email = "cmeckardt@salisbury.edu";
                phone = "tel:4105487972";
                break;
            case 23:// Natasha Finnegan
                email = "nefinnegan@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 24:// Stephen Ford
                email = "saford@salisbury.edu";
                phone = "tel:4105485972";
                break;
            case 25:// Bea Hardy
                email = "bbhardy@salisbury.edu";
                phone = "tel:4105436133";
                break;
            case 26:// T. Aaron Horner
                email = "tahorner@salisbury.edu";
                phone = "tel:4105436312";
                break;
            case 27:// Iris Jenkins
                email = "iljenkins@salisbury.edu";
                phone = "tel:4106774642";
                break;
            case 28:// Amy Jones
                email = "amjones@salisbury.edu";
                phone = "tel:4106775478";
                break;
            case 29:// Nicole Kulp
                email = "nlkulp@salisbury.edu";
                phone = "tel:4105489183";
                break;
            case 30:// Cassy Lewis
                email = "cklewis@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 31:// Creston Long
                email = "cslong@salisbury.edu";
                phone = "tel:4105482154";
                break;
            case 32:// Jennifer Martin
                email = "jmmartin@salisbury.edu";
                phone = "tel:4105436135";
                break;
            case 33:// Donna Messick
                email = "dtmessick@salisbury.edu";
                phone = "tel:4105436429";
                break;
            case 34:// Cynthia Nyirenda
                email = "cinyirenda@salisbury.edu";
                phone = "tel:4106770116";
                break;
            case 35:// James Parrigin
                email = "jlparrigin@salisbury.edu";
                phone = "tel:4106770131";
                break;
            case 36:// Tina Plottel
                email = "taplottel@salisbury.edu";
                phone = "tel:4105436206";
                break;
            case 37:// Ian Post
                email = "impost@salisbury.edu";
                phone = "tel:4106770020";
                break;
            case 38:// Angeline Prichard
                email = "arprichard@salisbury.edu";
                phone = "tel:4106770118";
                break;
            case 39:// Gaylord Robb
                email = "ggrobb@salisbury.edu";
                phone = "tel:4105436234";
                break;
            case 40:// Rashid Robinson
                email = "rvrobinson@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 41:// Susie Ruddy
                email = "mxruddy@salisbury.edu";
                phone = "tel:4105484571";
                break;
            case 42:// Audrey Schadt
                email = "ahschadt@salisbury.edu";
                phone = "tel:4105483236";
                break;
            case 43:// Teddy Stocking
                email = "ehstocking@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 44:// Liz Wallace
                email = "eawallace@salisbury.edu";
                phone = "tel:4105436130";
                break;
            case 45:// Chris Woodall
                email = "cmwoodall@salisbury.edu";
                phone = "tel:4105436306";
                break;
            case 46:// Martha Zimmerman
                email = "mczimmerman@salisbury.edu";
                phone = "tel:4106770110";
                break;
        }

        // Use the Builder class for convenient dialog construction
        String ask = act.getResources().getString(R.string.contact_ask);
        ask = ask.concat(" ");
        ask = ask.concat(staff);//concatenates staff name to ask string
        ask = ask.concat("?");
        AlertDialog.Builder builder = new AlertDialog.Builder(act);
        if(email.compareTo("") != 0) {
            String[] items = {"Call", "Email", "Cancel"};
            builder = builder.setTitle(ask)
                    .setItems(items, new DialogInterface.OnClickListener() {
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
            builder = builder.setTitle(ask)
                    .setItems(items, new DialogInterface.OnClickListener() {
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
        //Create the AlertDialog object and return it
        return builder.create(); // creates builder
    }
}


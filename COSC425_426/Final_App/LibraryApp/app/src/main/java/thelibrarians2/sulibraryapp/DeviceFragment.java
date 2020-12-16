package thelibrarians2.sulibraryapp;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;



/**
 * This fragment is for each tab of the DeviceAvailabilityFragment.java
 *
 */
public class DeviceFragment extends Fragment implements AdapterView.OnItemClickListener {
    ArrayList<Integer> iconsArr = new ArrayList<>();
    ArrayList<Integer> typesArr = new ArrayList<>();
    ArrayList<String> strArr = new ArrayList<>();
    ArrayList<ListItem> listItems;

    ListviewX lix;
    ListView listView;
    DeviceFilterFragment deviceFilter;
    int tabNumber;
    static int airsCount = 17, prosCount = 4, accessoriesCount = 4, totalCount = 25; //only tracks devices to be displayed
    static int availAirs = 0,  availPros = 0, availAccess = 0, totalAvail = 0; //only tracks devices to be displayed

    static ArrayList<JSONObject> airsList;
    static ArrayList<JSONObject> prosList;
    static ArrayList<JSONObject> accessoriesList;

    static ArrayList<JSONObject> availAirsList;
    static ArrayList<JSONObject> availProsList;
    static ArrayList<JSONObject> availAccessoriesList;

    public DeviceFragment(int sectionNumber) {
        tabNumber = sectionNumber;
    }

    public DeviceFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null)
            tabNumber = savedInstanceState.getInt("tab");

        deviceFilter = DeviceFilterFragment.getInstance();

        lix = new ListviewX(Objects.requireNonNull(getActivity()));
        listItems = new ArrayList<>();

        View view = inflater.inflate(R.layout.fragment_device_pager, container, false);
        listView = view.findViewById(R.id.listView);
        listView.setVisibility(View.INVISIBLE);

        filter(); //filter devices and add them to the list

        listView.setAdapter(lix);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", tabNumber);
    }

    private void filter() {
        // Search devices array for devices that should be filtered and filters them

        boolean[] mask = deviceFilter.getDeviceMask();

        strArr = new ArrayList<>();
        typesArr = new ArrayList<>();
        iconsArr = new ArrayList<>();

        if(!mask[0]) { // iPad Airs not filtered

            //section items//
            if(tabNumber == 0) {
                addToList(airsList);
            } else {
                addToList(availAirsList);
            }

        }

        if(!mask[1]) { // iPad Pros not filtered

            //section items//
            if(tabNumber == 0) {
                addToList(prosList);
            } else {
                addToList(availProsList);
            }
        }
        if(!mask[2]) { // Accessories not filtered

            //section items//
            if(tabNumber == 0) {
                addToList(accessoriesList);
            } else {
                addToList(availAccessoriesList);
            }
        }

        //populate listview
        lix.populate(listItems);
        listView.setVisibility(View.VISIBLE);
    }

    public void addToList(ArrayList<JSONObject> list) {
        //populates arrays to be added to the listview

        String itemName;
        String itemStatusString;
        int itemImage;

        for(int a = 0; a < list.size(); a++) {
            JSONObject ob = list.get(a);
            typesArr.add(2);
            try {
                //item name
                itemName = ob.getString("device_name");
                //item icon
                switch (ob.getInt("status")) {
                    case 1:
                        itemImage = R.drawable.available;
                        //item detail
                        if (!ob.getString("detail").equals("null"))
                            itemStatusString = ob.getString("type_name") + " (" + ob.getString("detail") + ")";
                        else
                            itemStatusString = ob.getString("type_name");
                        listItems.add(new ListItem2(getActivity(), itemImage, itemName, itemStatusString));
                        break;
                    case 2:
                        itemImage = R.drawable.checked_out;
                        itemStatusString = getResources().getString(R.string.device_due) + " " + formatDate(ob.getString("due_date"));
                        listItems.add(new ListItem2(getActivity(), itemImage, itemName, itemStatusString));
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 10:
                    case 11:
                        itemImage = R.drawable.unavailable;
                        itemStatusString = getResources().getString(R.string.device_unavailable);
                        listItems.add(new ListItem2(getActivity(), itemImage, itemName, itemStatusString));
                        break;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private String formatDate(String d) {
        String date = null;
        String[] pieces = d.split("-");

        switch(pieces[1]) {
            case "01":
                date = "January";
                break;
            case "02":
                date = "February";
                break;
            case "03":
                date = "March";
                break;
            case "04":
                date = "April";
                break;
            case "05":
                date = "May";
                break;
            case "06":
                date = "June";
                break;
            case "07":
                date = "July";
                break;
            case "08":
                date = "August";
                break;
            case "09":
                date = "September";
                break;
            case "10":
                date = "October";
                break;
            case "11":
                date = "November";
                break;
            case "12":
                date = "December";
        }

        date += " " + pieces[2] + ", " + pieces[0];

        return date;
    }

    public static void parseJSON(String jString) {
        //make arrays of devices and available devices

        JSONObject j;
        JSONArray jArray;
        int status;  //device status

        airsList = new ArrayList<>();
        prosList = new ArrayList<>();
        accessoriesList = new ArrayList<>();

        availAirsList = new ArrayList<>();
        availProsList = new ArrayList<>();
        availAccessoriesList = new ArrayList<>();

        nullCounts();

        try {
            jArray = new JSONArray(jString);

            //loop through JSON devices array
            for (int x = 0; x < jArray.length(); x++) {
                /*do not show devices with specific status codes
                Status:
                1 available
                2 checked out
                3 temp unavailable
                4 ""
                5 ""
                10 ""
                11 ""
                else do not display
                */
                j = new JSONObject(jArray.getString(x));
                status = j.getInt("status"); // Get device status

                if (status == 1 || status == 2 || status == 3 || status == 4 || status == 5 || status == 10 || status == 11) { // Check status//count number of devices in each category
                    if (j.getString("device_name").toLowerCase().contains("air")) {
                        airsCount++;
                        airsList.add(j);
                        if(j.getInt("status") == 1) {
                            availAirs++;
                            availAirsList.add(j);
                        }
                    } else if (j.getString("device_name").toLowerCase().matches("ipad #[0-9][0-9]") ||  j.getString("device_name").toLowerCase().matches("ipad #[0-9]") || j.getString("device_name").toLowerCase().matches("ipad #[0-9] ")) {
                        prosCount++;
                        prosList.add(j);
                        if(j.getInt("status") == 1) {
                            availPros++;
                            availProsList.add(j);
                        }
                    } else if (j.getString("device_name").toLowerCase().matches("ipad keyboard #[0-9]") || j.getString("device_name").toLowerCase().matches("ipad keyboard #[0-9][0-9]")) {
                        prosCount++;
                        prosList.add(j);
                        if(j.getInt("status") == 1) {
                            availPros++;
                            availProsList.add(j);
                        }
                    } else if (j.getString("device_name").toLowerCase().matches(("ipad pro #[0-9][0-9]")) || j.getString("device_name").toLowerCase().matches(("ipad pro #[0-9]"))) {
                        prosCount++;
                        prosList.add(j);
                        if(j.getInt("status") == 1) {
                            availPros++;
                            availProsList.add(j);
                        }
                    } else if (j.getString("device_name").toLowerCase().matches("ipad pro keyboard #[0-9]") || j.getString("device_name").toLowerCase().matches("ipad pro keyboard #[0-9][0-9]")) {
                        prosCount++;
                        prosList.add(j);
                        if(j.getInt("status") == 1) {
                            availPros++;
                            availProsList.add(j);
                        }
                    } else {
                        accessoriesCount++;
                        accessoriesList.add(j);
                        if(j.getInt("status") == 1) {
                            availAccess++;
                            availAccessoriesList.add(j);
                        }
                    }
                }
            }
            totalCount = airsCount + prosCount + accessoriesCount;

            totalAvail = availAirs + availPros + availAccess;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //click a listview item

        ListItem li = (ListItem) parent.getItemAtPosition(position);

        if (li.getType() == 2) {  //if clicked item is not section header
            ListItem2 li2 = (ListItem2) li;
            TextView title = li2.getTextView1();
            ImageView pic = li2.getImageView();

            Log.i("nick", "title "+title.getText());

            //AlertDialog.Builder dialog = new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.Theme_Dialog));
            CustomAlertDialogBuilder dialog = new CustomAlertDialogBuilder(getActivity());
            dialog.setTitle(title.getText().toString());
            //dialog.setView(df.getView());

            String tag = String.valueOf(pic.getTag()); //tag is set in each ListItem class

            //set text on dialog
            if (tag.equals(String.valueOf(R.drawable.available))) { //device is available
                //device is available
                /*df.getT1().setText(getResources().getString(R.string.device_avail_dialog));
                df.getT2().setText(getResources().getString(R.string.device_status_reminder_dialog));*/

                dialog.setMessage(getResources().getString(R.string.device_avail_dialog)
                        + "\n\n" + getResources().getString(R.string.device_status_reminder_dialog));
                //dialog.setMessage(getResources().getString(R.string.device_status_reminder_dialog));
            } else if (tag.equals(String.valueOf(R.drawable.checked_out))) { //device is checked out
                //device is checked out
                TextView subtitle = view.findViewById(R.id.text_item2_2);
                /*df.getT1().setText(String.format(getResources().getString(R.string.device_checkout_dialog), subtitle.getText().toString()));
                df.getT2().setText(getResources().getString(R.string.device_status_reminder_dialog));*/

                dialog.setMessage(String.format(getResources().getString(R.string.device_checkout_dialog), subtitle.getText().toString())
                        + "\n\n" + getResources().getString(R.string.device_status_reminder_dialog));
                //dialog.setMessage(getResources().getString(R.string.device_status_reminder_dialog));
            } else { //device is out of circulation
                //device is not available
                /*df.getT1().setText(getResources().getString(R.string.device_navail_dialog1));
                df.getT2().setText(getResources().getString(R.string.device_navail_dialog2));
                df.getT3().setText(getResources().getString(R.string.device_navail_dialog3));
                ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) df.getT3().getLayoutParams();*/

                dialog.setMessage(getResources().getString(R.string.device_navail_dialog1)
                        + "\n\n" + getResources().getString(R.string.device_navail_dialog2)
                        + "\n\n" + getResources().getString(R.string.device_navail_dialog3));
                //dialog.setMessage(getResources().getString(R.string.device_navail_dialog2));
                //dialog.setMessage(getResources().getString(R.string.device_navail_dialog3));

                //mlp.setMargins(80, 0, 80, 80);
            }

            String ok = getResources().getString(R.string.ok);
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            dialog.show();

            Button button = dialog.getButton(DialogInterface.BUTTON_POSITIVE); //button only referenced after show()

            if(button != null) {
                button.setBackgroundColor(Color.parseColor("#FFFFFF"));
                button.setTextColor(Color.parseColor("#000000"));
            }
        }
    }

    public static void nullCounts() {
        availAirs = 0;
        availPros = 0;
        availAccess = 0;
        totalAvail = 0;

        airsCount = 17;
        prosCount = 4;
        accessoriesCount = 4;
        totalCount = 25;
    }
}
package thelibrarians2.sulibraryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.fragment.app.Fragment;


/**
 * Created by njraf_000 on 10/22/2016.
 * Revised by Declan Sheehan
 * Date: October, 2020
 * Description: This fragment handles filtering what displays on the device availability
 * fragment by using a 'mask' to define what will (true) and what should not (false).
 */

public class DeviceFilterFragment extends Fragment {

    // Define class variables.
    static boolean[] deviceMask = {false, false, false};
    AppCompatCheckBox[] checkboxes;

    View view;

    // Define the non-default constructor.
    public DeviceFilterFragment() {
        checkboxes = new AppCompatCheckBox[3];
    }

    // Returns an instance of this class.
    public static DeviceFilterFragment getInstance() {
        DeviceFilterFragment df;
        df = new DeviceFilterFragment();
        return df;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Get the current view to later be displayed once returned.
        view = inflater.inflate(R.layout.fragment_device_filter, container, false);

        // Get the views associated with each device on the xml layout.
        checkboxes[0] = view.findViewById(R.id.air_check);
        checkboxes[1] = view.findViewById(R.id.pro_check);
        checkboxes[2] = view.findViewById(R.id.accessory_check);

        // Set the masks for each above view.
        for (AppCompatCheckBox checkbox : checkboxes) {
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setMask(v);
                }
            });
        }

        return view;
    }

    // Returns the mask.
    public boolean[] getDeviceMask() {
        return deviceMask;
    }

    public void setMask(View v) {
        // See warning below. If this no longer functions, it is likely due to IDs used in case
        // statements.
        System.out.println(v.getId());

        if (v.getId() == R.id.air_check) {
            deviceMask[0] = !checkboxes[0].isChecked();
        } else if (v.getId() == R.id.pro_check) {
            deviceMask[1] = !checkboxes[1].isChecked();
        } else if (v.getId() == R.id.accessory_check) {
            deviceMask[2] = !checkboxes[2].isChecked();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}

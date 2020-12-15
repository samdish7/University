package su.android.libraryapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class StudyRoomDetailFragment extends Fragment{

    View roomView;
    boolean button;
    ActionBar toolbar;
    String full_string;
    ImageView roomImage;
    LinearLayout roomAll;
    RoomDetail room_detail;
    TextView loadingmsg,roomName,roomCap,roomLoc,roomDescription,roomReserve;

    public StudyRoomDetailFragment(RoomDetail rd, boolean btn){
        this.room_detail = rd;
        this.button = btn;
    }

    public StudyRoomDetailFragment(){}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            full_string=savedInstanceState.getString("JSON");
        }
        roomView = inflater.inflate(R.layout.fragment_study_room_detail, container, false); // Gets View
        roomAll = roomView.findViewById(R.id.study_room_all); // Gets Layout
        roomAll.setVisibility(View.INVISIBLE); // Sets View to Invisible until loading is finished
        loadingmsg= roomView.findViewById(R.id.loadingstudydisplay);
        loadingmsg.setVisibility(View.VISIBLE);
        roomImage = roomView.findViewById(R.id.roomImage);
        roomName = roomView.findViewById(R.id.roomName); // Assigns TextView from xml
        roomCap = roomView.findViewById(R.id.roomCap); // Assigns TextView from xml
        roomLoc = roomView.findViewById(R.id.roomLoc); // Assigns TextView from xml
        roomDescription = roomView.findViewById(R.id.roomDescription); // Assigns TextView from xml
        roomReserve = (Button) roomView.findViewById(R.id.reserveButton); // Assigns Button from xml

        toolbar = ((AppCompatActivity) Objects.requireNonNull(getActivity())).getSupportActionBar();
        assert toolbar != null;
        toolbar.setTitle(room_detail.getName());

        if(button) {
            roomReserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { // OnClick
                    Fragment p1;
                    FragmentManager fragmentManager;
                    FragmentTransaction fragmentTransaction;
                    if (isNetworkAvailable()) {
                        p1 = new StudyRoomReserveFragment(room_detail.getRoomID()); // Creates new Fragment
                    } else {
                        p1 = new ConnectionErrorFragment(new StudyRoomReserveFragment(room_detail.getRoomID()));
                    }
                    fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
                    fragmentTransaction = fragmentManager.beginTransaction(); // Begins transaction
                    fragmentTransaction.replace(R.id.content_container, p1); // Replaces fragment
                    fragmentTransaction.addToBackStack(null).commit(); // Adds this fragment to backstack
                    MainActivity.pageStack.push(MainActivity.studyroomPage);

                }
            });
        }
        else{
            roomReserve.setVisibility(View.GONE);
        }

        fillInfo();

        return roomView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // Fills info for the study room
    private void fillInfo(){
        roomName.setText(shortName(room_detail.getName())); // Gets and sets room name
//            roomAvail.setText(calculateAvailability(avail)); // Gets and sets availability message
        String capacity = "Up to " + room_detail.getCapacity() + " people";
        roomCap.setText(capacity); // Gets and sets capacity
        roomLoc.setText(room_detail.getDirections()); // Gets and sets directions
        roomDescription.setText(room_detail.getDescription()); // Gets and sets room description
        setRoomIcon();
        loadingmsg.setVisibility(View.INVISIBLE);
        roomAll.setVisibility(View.VISIBLE); // Sets Layout to Visible
    }

    // Sets the image
    private void setRoomIcon(){
        if(room_detail.getPicAvailable()){
            String new_name = "";
            if(room_detail.getName().length() > 8){
                for(int i = 0; i < 3; i++){
                    new_name = new_name.concat(Character.toString(room_detail.getName().charAt(i+5)));
                }
            }
            String study_room_pic_name = "studyrm_" + new_name;
            int image_id = getResources().getIdentifier(study_room_pic_name, "drawable", "su.android.libraryapp");
            roomImage.setImageResource(image_id);
        }
    }

    // Removes the size (small, med, large) from the name
    String shortName(String name ) {
        if (name.length() > 8){
            int startIndex = name.indexOf("(");
            int endIndex = name.indexOf(")");
            String toBeReplaced = name.substring(startIndex, endIndex+1);
            name = name.replace(toBeReplaced, " ");
        }
        return name;
    }

    // Checks for network availability
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

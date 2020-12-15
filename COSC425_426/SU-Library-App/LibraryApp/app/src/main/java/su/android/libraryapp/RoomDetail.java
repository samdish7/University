package su.android.libraryapp;

import org.json.JSONException;
import org.json.JSONObject;

public class RoomDetail{
    int room_id;
    int group_id;
    String name;
    int capacity;
    String description;
    String directions;

    int icon;
    String section;
    boolean pic_available;
    boolean currently_available;

    public RoomDetail(JSONObject room) throws JSONException {
        setRoomID(room.getInt("room_id"));
        setGroupID(room.getInt("group_id"));
        setName(room.getString("name"));
        setCapacity(room.getInt("capacity"));
        setDescription(room.getString("description"));
        setDirections(room.getString("directions"));

        section = StudyRoomListFragment.sections[0];

        if (name.contains("Small")) {
            setIcon(R.drawable.group_study_small);
        }
        else if (name.contains("Large")) {
            setIcon(R.drawable.group_study_large);
        }
        else {
            setIcon(R.drawable.group_study_medium);
        }

        pic_available = isPicAvailable();
        currently_available = false;
    }

    public boolean isPicAvailable(){
        String new_name = "";
        if(getName().length() > 5){
            for(int i = 0; i < 5; i++){
                new_name = new_name.concat(Character.toString(getName().charAt(i)));
            }
            return new_name.compareTo("Room ") == 0;
        }
        return false;
    }

    public boolean getPicAvailable(){
        return pic_available;
    }

    //*********************************************************
    public void setIcon(int res){
        icon = res;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setRoomID(int room_id){
        this.room_id = room_id;
    }
    public void setGroupID(int group_id){
        this.group_id = group_id;
    }
    public void setSection(String section){
        this.section = section;
    }
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }
    public void setDirections(String directions){
        this.directions = directions;
    }
    public void setDescription(String description){
        this.description = description.replace(',', '\n');
    }
    //*********************************************************
    public int getIcon(){
        return icon;
    }
    public String getName(){
        return name;
    }
    public int getRoomID(){
        return room_id;
    }
    public int getGroupID(){
        return group_id;
    }
    public int getCapacity(){
        return capacity;
    }
    public String getSection(){
        return section;
    }
    public String getDirections(){
        return directions;
    }
    public String getDescription(){
        return description;
    }
    //*********************************************************
}
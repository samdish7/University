package thelibrarians2.sulibraryapp;

import java.sql.Date;
import java.sql.Time;

class Reservation{
    String dateStart;
    String spinFrom, spinTo;    // For the Spinner display
    String jsonFrom, jsonTo;    // For the JSON psuh

    Reservation(String from, String to){
        this.jsonFrom = from;
        this.jsonTo = to;
        int T_index = from.indexOf('T');
        String substring = from.substring(0, T_index);
        Date today;
        today = Date.valueOf(substring);
        this.dateStart = today.toLocaleString();
        if(this.dateStart.charAt(11) == ' ') {
            this.dateStart = this.dateStart.substring(0, 11);
        }
        else {
            this.dateStart = this.dateStart.substring(0, 12);
        }


        substring = from.substring(T_index + 1, from.indexOf('-', T_index));
        Time time = Time.valueOf(substring);
        spinFrom = time.toLocaleString();
        spinFrom = spinFrom.substring(12);


        substring = to.substring(T_index + 1, to.indexOf('-', T_index));
        time = Time.valueOf(substring);
        spinTo = time.toLocaleString();
        spinTo = spinTo.substring(12);

        if(spinFrom.contains(":00 AM")){
            spinFrom =spinFrom.replace(":00 AM", " AM");
        }
        else{
            spinFrom = spinFrom.replace(":00 PM", " PM");
        }

        if(spinTo.contains(":00 AM")){
            spinTo = spinTo.replace(":00 AM", " AM");
        }
        else{
            spinTo = spinTo.replace(":00 PM", " PM");
        }

    }

    public String getDateStart() {
        return dateStart;
    }

    public String getSpinFrom() {
        return spinFrom;
    }
    public String getSpinTo() {
        return spinTo;
    }

    public String getJsonFrom(){
        return jsonFrom;
    }
    public String getJsonTo(){
        return jsonTo;
    }

}

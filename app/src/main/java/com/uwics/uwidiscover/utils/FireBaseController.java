package com.uwics.uwidiscover.utils;

import android.app.Application;
import android.provider.Settings;

import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.classes.models.Faculty;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

/**
 * Created by Rajay Bitter on 1/6/17.
 */

public class FireBaseController extends Application {

    private List<Event> eventList = new ArrayList<>();

    @Override
    public void onCreate(){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public List<Event> getEventList() {
        return eventList;
    }
    //can gwan
    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;

        for (Event e : eventList) {
            // Some extra space popping up in the first replace unexpectedly, so I had to remove duplicates in the replaceAll
            if (e.getStartTime().contains("AM"))
                e.setStartTime(e.getStartTime().replace("AM", " AM").replaceAll("\\s+", " "));
            else e.setStartTime(e.getStartTime().replace("PM", " PM").replaceAll("\\s+", " "));

            if (e.getEndTime().contains("AM"))
                e.setEndTime(e.getEndTime().replace("AM", " AM").replaceAll("\\s+", " "));
            else e.setEndTime(e.getEndTime().replace("PM", " PM").replaceAll("\\s+", " "));
        }

        Collections.sort(eventList, new Comparator<Event>() {
            DateFormat format = new SimpleDateFormat("h:mm a",Locale.US);

            @Override
            public int compare(Event lhs, Event rhs) {
                try {
                    return format.parse(lhs.getStartTime()).compareTo(format.parse(rhs.getStartTime()));
                } catch (ParseException e) {
                    return -1;
                }
            }
        });
    }
}

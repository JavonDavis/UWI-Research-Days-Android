package com.uwics.uwidiscover.utils;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;
import com.uwics.uwidiscover.classes.models.Event;
import com.uwics.uwidiscover.classes.models.Faculty;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Howard on 1/22/2016.
 */
public class ParseController extends Application {

    private List<Event> eventList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Event.class);
        ParseObject.registerSubclass(Faculty.class);
        Parse.initialize(this);
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;

        for (Event e : eventList) {
            if (e.getStartTime().contains("AM"))
                e.setStartTime(e.getStartTime().replace("AM", " AM"));
            else
                e.setStartTime(e.getStartTime().replace("PM", " PM"));

            if (e.getEndTime().contains("AM"))
                e.setEndTime(e.getEndTime().replace("AM", " AM"));
            else
                e.setEndTime(e.getEndTime().replace("PM", " PM"));
        }

        Collections.sort(eventList, new Comparator<Event>() {
            DateFormat format = new SimpleDateFormat("h:mm a");

            @Override
            public int compare(Event lhs, Event rhs) {
                try {
                    return format.parse(lhs.getStartTime()).compareTo(format.parse(rhs.getStartTime()));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
    }
}

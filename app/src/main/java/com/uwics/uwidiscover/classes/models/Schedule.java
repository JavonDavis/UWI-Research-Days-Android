package com.uwics.uwidiscover.classes.models;

import java.util.Calendar;

/**
 * Created by Howard on 1/23/2016.
 */
public class Schedule {

    Calendar date;
    String time;
    String type;
    String details;
    String location;

    public Schedule(Calendar date, String time, String type, String details, String location) {
        this.date = date;
        this.time = time;
        this.type = type;
        this.details = details;
        this.location = location;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

package com.uwics.uwidiscover.classes.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * @author Javon Davis
 *         Created by Javon Davis on 02/02/16.
 */
@ParseClassName("urdschedule")
public class Event extends ParseObject {

    public String getDate() {
        return getString("date");
    }

    public void setDate(String date) {
        put("date", date);
    }

    public String getDetails() {
        return getString("details");
    }

    public String getFacultyTag() { return getString("faculty"); }

    public void setFacultyTag(String tag) { put("faculty", tag); }

    public void setDetails(String details) {
        put("details", details);
    }

    public String getVenue() {
        return getString("venue");
    }

    public void setVenue(String venue) {
        put("venue", venue);
    }

    public String getType() {
        return getString("type");
    }

    public void setType(String type) {
        put("type", type);
    }

    public String getStartTime() {
        return getString("start_time");
    }

    public void setStartTime(String startTime) {
        put("start_time", startTime);
    }

    public String getEndTime() {
        return getString("end_time");
    }

    public void setEndTime(String endTime) {
        put("end_time", endTime);
    }
}

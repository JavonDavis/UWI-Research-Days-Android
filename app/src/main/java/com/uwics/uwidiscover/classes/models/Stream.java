package com.uwics.uwidiscover.classes.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by shane on 2/14/16.
 */
@ParseClassName("stream")
public class Stream extends ParseObject {

    public Stream(int id, String name, String startTime, String endTime, String location, String url) {
        put("id", id);
        put("name", name);
        put("start_time", startTime);
        put("end_time", endTime);
        put("location", location);
        put("url", url);
    }
    public int getId() {
        return getInt("id");
    }

    public String getName() {
        return getString("name");
    }

    public String getStartTime() {
        return getString("start_time");
    }


}

package com.uwics.uwidiscover.classes.models;


/**
 * Created by shane on 2/14/16.
 */

public class Stream {
    private int id;
    private String name;
    private String startTime;
    private String endtime;
    private String location;
    private String url;

    public Stream(int id, String name, String startTime, String endTime, String location, String url) {
        this.id = id;
        this.name = name;
        this.startTime = startTime;
        this.endtime = endTime;
        this.location = location;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

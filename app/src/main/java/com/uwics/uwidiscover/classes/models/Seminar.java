package com.uwics.uwidiscover.classes.models;

import java.util.ArrayList;

/**
 * Created by Howard on 1/22/2016.
 */
public class Seminar {

    String id;
    String topic;
    String startTime;
    String endTime;
    String description;
    Representative speaker;
    Location location;
    ArrayList<Feedback> feedback;

    public Seminar(String id, String topic, String startTime, String endTime, String description,
                   Representative speaker, Location location, ArrayList<Feedback> feedback) {
        this.id = id;
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.speaker = speaker;
        this.location = location;
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Representative getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Representative speaker) {
        this.speaker = speaker;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<Feedback> feedback) {
        this.feedback = feedback;
    }
}

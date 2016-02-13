package com.uwics.uwidiscover.classes.models;

import java.util.ArrayList;

/**
 * Created by Howard on 1/22/2016.
 */
public class Project {

    String id;
    String title;
    String description;
    ArrayList<Media> media;
    ArrayList<Representative> moderators;
    ArrayList<Feedback> feedback;
    Location location;

    public Project(String id, String title, String description, ArrayList<Media> media,
                   ArrayList<Representative> moderators, ArrayList<Feedback> feedback,
                   Location location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.media = media;
        this.moderators = moderators;
        this.feedback = feedback;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Media> getMedia() {
        return media;
    }

    public void setMedia(ArrayList<Media> media) {
        this.media = media;
    }

    public ArrayList<Representative> getModerators() {
        return moderators;
    }

    public void setModerators(ArrayList<Representative> moderators) {
        this.moderators = moderators;
    }

    public ArrayList<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<Feedback> feedback) {
        this.feedback = feedback;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

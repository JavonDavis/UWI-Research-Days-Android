package com.uwics.uwidiscover.classes.models;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Howard on 1/22/2016.
 */
public class Location {

    String id;
    String name;
    LatLng location;

    public Location(String id, String name, LatLng location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getLocation() {
        return location;
    }

    public void setLocation(LatLng location) {
        this.location = location;
    }
}

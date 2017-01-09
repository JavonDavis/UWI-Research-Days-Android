package com.uwics.uwidiscover.classes.models;

/**
 * Created by Howard on 1/22/2016.
 */
public class Number {

    String id;
    String number;

    public Number(String id, String number) {
        this.id = id;
        // TODO: Determine how a number should be entered into app so as to know how to properly format it
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}

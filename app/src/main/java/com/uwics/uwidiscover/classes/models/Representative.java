package com.uwics.uwidiscover.classes.models;

import java.util.ArrayList;

/**
 * Created by Howard on 1/22/2016.
 */
public class Representative {

    String id;
    String name;
    String position;
    String email;
    String fax;
    ArrayList<Number> numbers;

    public Representative(String id, String name, String position, String email, String fax,
                          ArrayList<Number> numbers) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.email = email;
        this.fax = fax;
        this.numbers = numbers;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public ArrayList<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Number> numbers) {
        this.numbers = numbers;
    }
}

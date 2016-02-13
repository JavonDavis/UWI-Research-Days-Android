package com.uwics.uwidiscover.classes.models;

import java.util.ArrayList;

/**
 * @author Howard Edwards
 */
public class Department {

    String id;
    String name;
    String email;
    String fax;
    ArrayList<Project> projects;
    ArrayList<Number> numbers;
    Representative head;
    Location location;

    public Department(String id, String name, String email, String fax, ArrayList<Project> projects,
                      ArrayList<Number> numbers, Representative head, Location location) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.fax = fax;
        this.projects = projects;
        this.numbers = numbers;
        this.head = head;
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

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<Number> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<Number> numbers) {
        this.numbers = numbers;
    }

    public Representative getHead() {
        return head;
    }

    public void setHead(Representative head) {
        this.head = head;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

package com.uwics.uwidiscover.classes.models;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Howard on 1/22/2016.
 */
@ParseClassName("Faculty")
public class Faculty extends ParseObject {

    public String getName() {
        return getString("name");
    }

    public String getDean() {
        return getString("dean");
    }

    public String getNumber() {
        return getString("number");
    }

    public String getEmail() {
        return getString("email");
    }


//    String id;
//    String name;
//    String email;
//    String fax;
//    String announcement;
//    ArrayList<Department> departments;
//    ArrayList<Number> numbers;
//    Representative dean;
//    Location location;

//    public Faculty(String id, String name, String email, String fax, String announcement,
//                   ArrayList<Department> departments, ArrayList<Number> numbers,
//                   Representative dean, Location location) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.fax = fax;
//        this.announcement = announcement;
//        this.departments = departments;
//        this.numbers = numbers;
//        this.dean = dean;
//        this.location = location;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getFax() {
//        return fax;
//    }
//
//    public void setFax(String fax) {
//        this.fax = fax;
//    }
//
//    public String getAnnouncement() {
//        return announcement;
//    }
//
//    public void setAnnouncement(String announcement) {
//        this.announcement = announcement;
//    }
//
//    public ArrayList<Department> getDepartments() {
//        return departments;
//    }
//
//    public void setDepartments(ArrayList<Department> departments) {
//        this.departments = departments;
//    }
//
//    public ArrayList<Number> getNumbers() {
//        return numbers;
//    }
//
//    public void setNumbers(ArrayList<Number> numbers) {
//        this.numbers = numbers;
//    }
//
//    public Representative getDean() {
//        return dean;
//    }
//
//    public void setDean(Representative dean) {
//        this.dean = dean;
//    }
//
//    public Location getLocation() {
//        return location;
//    }
//
//    public void setLocation(Location location) {
//        this.location = location;
//    }
}

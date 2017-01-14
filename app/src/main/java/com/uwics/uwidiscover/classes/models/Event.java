package com.uwics.uwidiscover.classes.models;



/**
 * @author Javon Davis
 *         Created by Javon Davis on 02/02/16.
 */
public class Event {

    private String date;
    private String details;
    private String faculty;
    private String venue;
    private String type;
    private String time;
    private String end_time;
    private String start_time;
    private String coordinator;
    private String category;
    private String department;

    public Event(String date, String details, String faculty, String venue, String type, String time, String coordinator, String department){
        this.date = date;
        this.details = details;
        this.faculty = faculty;
        this.venue = venue;
        this.type = type;
        this.time = time;
        this.coordinator = coordinator;
        this.department = department;
    }

    public Event(){
        //Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public String getFaculty() { return faculty; }

    public void setFaculty(String tag) { this.faculty = tag; }

    public String getFacultyTag() { return faculty; }

    public void setFacultyTag(String tag) { this.faculty = tag; }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartTime() {
        String[] result = time.split("\\-+");
        start_time = result[0];
        return start_time;
    }

    public void setStartTime(String startTime) {
        this.start_time = startTime;
    }

    public String getEndTime() {
        String[] result = time.split("\\-+");
        end_time = result[1];
        return end_time;
    }

    public void setEndTime(String endTime) {
        this.end_time = endTime;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

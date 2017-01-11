//package com.uwics.uwidiscover.utils;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//import com.parse.ParseRelation;
//import com.parse.SaveCallback;
//import com.uwics.uwidiscover.classes.models.Department;
//import com.uwics.uwidiscover.classes.models.Faculty;
//
//import java.util.ArrayList;
//
///**
// * @author Howard Edwards
// */
//public class ParseLayer {
//
//    ArrayList<Department> departmentArrayList = new ArrayList<>();
//    Context context;
//
//    public ParseLayer(Context context) {
//        this.context = context;
//    }
//
//    public void getDepartmentInfo() {
//        ParseObject project = new ParseObject("project");
//        project.put("title", "Some title here");
//
//        ParseObject dept = new ParseObject("department");
//        dept.put("name", "Some name here");
//        dept.put("description", "Some description here");
//
//        ParseRelation<ParseObject> relation = dept.getRelation("projects");
//        relation.add(project);
//
//        dept.put("projects", relation);
//        dept.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if (e == null) {
//                    Log.d("dept", "saved");
//                } else {
//                    Log.d("dept", "Error: " + e.getMessage());
//                }
//            }
//        });
//
////        ParseQuery<ParseObject> query = ParseQuery.getQuery("department");
////        query.findInBackground(new FindCallback<ParseObject>() {
////            @Override
////            public void done(List<ParseObject> objects, ParseException e) {
////                if (e == null) {
////                    for (ParseObject dept : objects) {
////
////                    }
////                } else {
////                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
////                }
////            }
////        });
//    }
//
//    private ParseQuery<ParseObject> getParseRelationQuery(ParseObject parseObject, String fieldName) {
//        return parseObject.getRelation(fieldName).getQuery();
//    }
//
//    public void getFacultyInfo() {
//        ParseQuery<Faculty> query = ParseQuery.getQuery(Faculty.class);
//
//    }
//
//    public void getFeedbackInfo() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("feedback");
//    }
//
//    public void getLocationInfo() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("location");
//    }
//
//    public void getMediaInfo() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("media");
//    }
//
//    public void getNumberInfo() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("number");
//    }
//
//    public void getProjectInfo() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("project");
//    }
//
//    public void getRepresentativeInfo() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("representative");
//    }
//
//    public void getSessionInfo() {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("session");
//    }
//
////    public static class ParseInfo {
////        static ArrayList<Department> departments = departmentArrayList;
////
////        public static ArrayList<Department> getDepartments() {
////            return departments;
////        }
////    }
//}

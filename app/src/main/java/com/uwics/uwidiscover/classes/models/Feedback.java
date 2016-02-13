package com.uwics.uwidiscover.classes.models;

/**
 * Created by Howard on 1/22/2016.
 */
public class Feedback {

    String id;
    String name;
    String content;
    Feedback commentOnFeedback;

    public Feedback(String id, String name, String content, Feedback commentOnFeedback) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.commentOnFeedback = commentOnFeedback;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Feedback getCommentOnFeedback() {
        return commentOnFeedback;
    }

    public void setCommentOnFeedback(Feedback commentOnFeedback) {
        this.commentOnFeedback = commentOnFeedback;
    }
}

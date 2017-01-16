package com.uwics.uwidiscover.classes.models;


import java.io.File;

/**
 * Created by Howard on 1/22/2016.
 */
public class Media {

    String id;
    // TODO: Evaluate the use of ParseFile should Parse be removed as back-end provider
    File media;

    public Media(String id, File media) {
        this.id = id;
        this.media = media;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public File getMedia() {
        return media;
    }

    public void setMedia(File media) {
        this.media = media;
    }
}

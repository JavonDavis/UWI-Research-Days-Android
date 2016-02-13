package com.uwics.uwidiscover.classes.models;

import com.parse.ParseFile;

/**
 * Created by Howard on 1/22/2016.
 */
public class Media {

    String id;
    // TODO: Evaluate the use of ParseFile should Parse be removed as back-end provider
    ParseFile media;

    public Media(String id, ParseFile media) {
        this.id = id;
        this.media = media;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParseFile getMedia() {
        return media;
    }

    public void setMedia(ParseFile media) {
        this.media = media;
    }
}

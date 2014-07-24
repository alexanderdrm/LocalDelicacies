package com.mobiquity.LocalDelicacies;

import com.google.gson.annotations.Expose;

import javax.annotation.Generated;

/**
 * Created by dalexander on 7/24/14.
 */
public @Generated("org.jsonschema2pojo")
class Specality {

    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
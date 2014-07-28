package com.mobiquity.LocalDelicacies.delicacies;

import com.google.gson.annotations.Expose;
import com.mobiquity.LocalDelicacies.location.Location;

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

    private Location city = null;

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

    public void setCity(Location l) {
        this.city = l;
    }

    public Location getCity() {
        return city;
    }

}
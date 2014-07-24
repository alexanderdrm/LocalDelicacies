package com.mobiquity.LocalDelicacies.location;

import com.google.gson.annotations.Expose;
import com.mobiquity.LocalDelicacies.delicacies.Specality;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dalexander on 7/24/14.
 */
public @Generated("org.jsonschema2pojo")
class LocationData {

    @Expose
    private String title;
    @Expose
    private String description;
    @Expose
    private String imageUrl;
    @Expose
    private List<Specality> specalities = new ArrayList<Specality>();

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

    public List<Specality> getSpecalities() {
        return specalities;
    }

    public void setSpecalities(List<Specality> specalities) {
        this.specalities = specalities;
    }

}
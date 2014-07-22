package com.mobiquity.LocalDelicacies.Delicacies;

/**
 * Created by jwashington on 7/22/14.
 */
public class Delicacy {
    private String name;
    private String imageUrl;

    public Delicacy(String name, String imageUrl)
    {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

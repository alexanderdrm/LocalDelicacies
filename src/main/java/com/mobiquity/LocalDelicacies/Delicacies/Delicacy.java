package com.mobiquity.LocalDelicacies.delicacies;

/**
 * Created by jwashington on 7/22/14.
 */
public class Delicacy {
    private String name;
    private String imageUrl;
    private boolean bookmarked;

    public Delicacy(String name, String imageUrl)
    {
        this.name = name;
        this.imageUrl = imageUrl;
        this.bookmarked = false;
    }

    public Delicacy(String name, String imageUrl, boolean bookmarked)
    {
        this(name, imageUrl);
        this.bookmarked = bookmarked;
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

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }
}

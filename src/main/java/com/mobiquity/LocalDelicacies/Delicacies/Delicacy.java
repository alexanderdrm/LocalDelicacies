package com.mobiquity.LocalDelicacies.delicacies;

import android.os.Bundle;

/**
 * Created by jwashington on 7/22/14.
 */
public class Delicacy {
    private String name;
    private String imageUrl;
    private boolean bookmarked;

    private int ratingInHalfStars;

    public Delicacy(String name, String imageUrl, boolean bookmarked, int ratingInHalfStars)
    {
        this.name = name;
        this.imageUrl = imageUrl;
        this.bookmarked = bookmarked;
        this.ratingInHalfStars = ratingInHalfStars;
    }

    public Delicacy(String name, String imageUrl)
    {
        this(name, imageUrl, false);
    }

    public Delicacy(String name, String imageUrl, boolean bookmarked)
    {
        this(name, imageUrl, bookmarked, 0);
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

    public int getRatingInHalfStars()
    {
        return ratingInHalfStars;
    }

    public void setRatingInHalfStars(int ratingInHalfStars)
    {
        this.ratingInHalfStars = ratingInHalfStars;
    }

    public static Bundle createBundleFromDelicacy(Delicacy delicacy)
    {
        Bundle bundle = new Bundle();
        bundle.putString("name", delicacy.name);
        bundle.putString("imageURL", delicacy.imageUrl);
        bundle.putBoolean("bookmarked", delicacy.bookmarked);
        bundle.putInt("rating", delicacy.ratingInHalfStars);
        return bundle;
    }

    public static Delicacy createDelicacyFromBundle(Bundle bundle)
    {
        return new Delicacy(
                bundle.getString("name"),
                bundle.getString("imageURL"),
                bundle.getBoolean("bookmarked"),
                bundle.getInt("rating")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delicacy delicacy = (Delicacy) o;

        if (bookmarked != delicacy.bookmarked) return false;
        if (ratingInHalfStars != delicacy.ratingInHalfStars) return false;
        if (imageUrl != null ? !imageUrl.equals(delicacy.imageUrl) : delicacy.imageUrl != null) return false;
        if (!name.equals(delicacy.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (bookmarked ? 1 : 0);
        result = 31 * result + ratingInHalfStars;
        return result;
    }
}

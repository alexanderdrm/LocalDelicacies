package com.mobiquity.LocalDelicacies.location;

import android.os.Bundle;

/**
 * @since 1.0
 */
public class Location
{
    private String name;
    private String imageURL;
    private boolean bookmarked;

    public Location( String name, String imageURL )
    {
        this.name = name;
        this.imageURL = imageURL;
        bookmarked = false;
    }

    public Location(String name, String imageURL, boolean bookmarked)
    {
        this(name, imageURL);
        this.bookmarked = bookmarked;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getImageURL()
    {
        return imageURL;
    }

    public void setImageURL( String imageURL )
    {
        this.imageURL = imageURL;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public static Bundle createBundleFromLocation(Location location)
    {
        Bundle bundle = new Bundle();
        bundle.putString("name", location.name);
        bundle.putString("imageURL", location.imageURL);
        bundle.putBoolean("bookmarked", location.bookmarked);
        return bundle;
    }

    public static Location createLocationFromBundle(Bundle bundle)
    {
        return new Location(
                bundle.getString("name"),
                bundle.getString("imageURL"),
                bundle.getBoolean("bookmarked")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (bookmarked != location.bookmarked) return false;
        if (imageURL != null ? !imageURL.equals(location.imageURL) : location.imageURL != null) return false;
        if (!name.equals(location.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (imageURL != null ? imageURL.hashCode() : 0);
        result = 31 * result + (bookmarked ? 1 : 0);
        return result;
    }
}

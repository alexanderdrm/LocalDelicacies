package com.mobiquity.LocalDelicacies.location;

import android.os.Bundle;
import com.mobiquity.LocalDelicacies.LocationData;

/**
 * @since 1.0
 */
public class Location
{
    private String title;
    private String imageUrl;
    private boolean bookmarked;
    private String description;

    public Location(LocationData ld) {
        this(ld.getTitle(), ld.getImageUrl(), ld.getDescription(), false);
    }

    public Location( String title, String imageUrl)
    {
        this(title, imageUrl, "",  false);
    }

    public Location(String title, String imageUrl, String description, boolean bookmarked)
    {
        this.title = title;
        this.imageUrl = imageUrl;
        this.bookmarked = bookmarked;
        this.description = description;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
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
        bundle.putString("title", location.title);
        bundle.putString("imageUrl", location.imageUrl);
        bundle.putBoolean("bookmarked", location.bookmarked);
        bundle.putString("description", location.description);
        return bundle;
    }

    public static Location createLocationFromBundle(Bundle bundle)
    {
        return new Location(
                bundle.getString("title"),
                bundle.getString("imageUrl"),
                bundle.getString("description"),
                bundle.getBoolean("bookmarked")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        if (bookmarked != location.bookmarked) return false;
        if (imageUrl != null ? !imageUrl.equals(location.imageUrl) : location.imageUrl != null) return false;
        if (!title.equals(location.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (imageUrl != null ? imageUrl.hashCode() : 0);
        result = 31 * result + (bookmarked ? 1 : 0);
        return result;
    }
}

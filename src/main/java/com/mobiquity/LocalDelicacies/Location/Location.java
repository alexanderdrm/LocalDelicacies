package com.mobiquity.LocalDelicacies.Location;

/**
 * @since 1.0
 */
public class Location
{
    private String  name;
    private String imageURL;
    private boolean loved;

    public Location( String name, String imageURL )
    {
        this.name = name;
        this.imageURL = imageURL;
        loved = false;
    }

    public Location(String name, String imageURL, boolean loved)
    {
        this(name, imageURL);
        this.loved = loved;
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

    public boolean isLoved() {
        return loved;
    }

    public void setLoved(boolean loved) {
        this.loved = loved;
    }
}

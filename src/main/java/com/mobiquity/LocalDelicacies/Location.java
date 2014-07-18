package com.mobiquity.LocalDelicacies;

/**
 * @since 1.0
 */
public class Location
{
    private String  name;
    private String imageURL;

    public Location( String name, String imageUrl )
    {
        this.name = name;
        this.imageURL = imageUrl;
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
}

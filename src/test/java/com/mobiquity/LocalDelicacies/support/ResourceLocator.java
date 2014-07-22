package com.mobiquity.LocalDelicacies.support;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import org.robolectric.Robolectric;

/**
 * @since 1.0
 */
public class ResourceLocator
{
    public static String[] getStringArray( int id )
    {
        return Robolectric.application
                          .getResources()
                          .getStringArray( id );
    }

    public static Drawable getDrawable( int id )
    {
        return Robolectric.application
                          .getResources()
                          .getDrawable( id );
    }

    public static int getColor(int id)
    {
        return Robolectric.application
                .getResources()
                .getColor(id);
    }
}

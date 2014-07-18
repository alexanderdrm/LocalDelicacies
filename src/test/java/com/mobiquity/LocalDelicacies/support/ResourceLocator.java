package com.mobiquity.LocalDelicacies.support;

import android.graphics.drawable.Drawable;
import org.robolectric.Robolectric;

/**
 * @since 1.0
 */
public class ResourceLocator
{
    public static Drawable getDrawable( int id )
    {
        return Robolectric.application
                          .getResources()
                          .getDrawable( id );
    }
}

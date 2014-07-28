package com.mobiquity.LocalDelicacies.location;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.mobiquity.LocalDelicacies.BaseEvent;

/**
 * Created by jwashington on 7/22/14.
 */
public class LocationClickedEvent extends BaseEvent {

    private Location location;

    public LocationClickedEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

}

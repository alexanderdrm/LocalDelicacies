package com.mobiquity.LocalDelicacies.location;

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

    public void setLocation(Location location) {
        this.location = location;
    }
}

package com.mobiquity.LocalDelicacies;

import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.location.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dalexander on 7/24/14.
 */
public class DataUpdateEvent extends BaseEvent {
    ArrayList<Location> locations;
    ArrayList<Delicacy> delicacies;

    public DataUpdateEvent(ArrayList<Location> locations, ArrayList<Delicacy> delicacies) {
        this.locations = locations;
        this.delicacies = delicacies;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public ArrayList<Delicacy> getDelicacies() {
        return delicacies;
    }

}

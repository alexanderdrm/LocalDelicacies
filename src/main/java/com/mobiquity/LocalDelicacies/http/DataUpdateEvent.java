package com.mobiquity.LocalDelicacies.http;

import com.mobiquity.LocalDelicacies.BaseEvent;
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
    boolean shouldOverwriteLocationData;
    boolean shouldOverwriteDelicacyData;

    public DataUpdateEvent(ArrayList<Location> locations, ArrayList<Delicacy> delicacies,
                           boolean shouldOverwriteLocationData, boolean shouldOverwriteDelicacyData) {
        this.locations = locations;
        this.delicacies = delicacies;
        this.shouldOverwriteLocationData = shouldOverwriteLocationData;
        this.shouldOverwriteDelicacyData = shouldOverwriteDelicacyData;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public ArrayList<Delicacy> getDelicacies() {
        return delicacies;
    }

    public boolean shouldOverwriteLocationData() {
        return shouldOverwriteLocationData;
    }

    public boolean shouldOverwriteDelicacyData() {
        return shouldOverwriteDelicacyData;
    }
}

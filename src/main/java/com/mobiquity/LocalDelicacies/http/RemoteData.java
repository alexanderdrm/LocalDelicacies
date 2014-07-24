package com.mobiquity.LocalDelicacies.http;

/**
 * Created by dalexander on 7/24/14.
 */


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.mobiquity.LocalDelicacies.location.LocationData;


@Generated("org.jsonschema2pojo")
public class RemoteData {

    @Expose
    private List<LocationData> locations = new ArrayList<LocationData>();

    public List<LocationData> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationData> locations) {
        this.locations = locations;
    }

}


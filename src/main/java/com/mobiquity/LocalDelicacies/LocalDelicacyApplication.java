package com.mobiquity.LocalDelicacies;

import android.app.Application;
import android.util.Log;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.mobiquity.LocalDelicacies.location.Location;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/25/14.
 */
public class LocalDelicacyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
       ApplicationBus.getInstance().register(this);
    }

    private static LocalDelicacyApplication instance;
    private ArrayList<Location> locations = new ArrayList<Location>();
    private ArrayList<Delicacy> delicacies = new ArrayList<Delicacy>();

    public static LocalDelicacyApplication getInstance() {
        return instance;
    }

    public ArrayList<Location> getLocations()
    {
        return locations;
    }

    public void setLocations(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<Delicacy> getDelicacies() {
        return delicacies;
    }

    public void setDelicacies(ArrayList<Delicacy> delicacies) {
        this.delicacies = delicacies;
    }

    @Subscribe
    public void onDataUpdated(DataUpdateEvent event)
    {
        if(event.shouldOverwriteLocationData())
        {
            locations = event.getLocations();
        }
        if(event.shouldOverwriteDelicacyData())
        {
            delicacies = event.getDelicacies();
        }
        NotifyFragmentsOfDataEvent notifyEvent = new NotifyFragmentsOfDataEvent();
        ApplicationBus.getInstance().post(notifyEvent);

    }
}

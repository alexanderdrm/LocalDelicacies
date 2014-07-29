package com.mobiquity.LocalDelicacies;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
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

    public static LocalDelicacyApplication getInstance() {
        return instance;
    }

    @Subscribe
    public void onDataUpdated(DataUpdateEvent event)
    {
        ArrayList<Location> locations = event.getLocations();
        ArrayList<Delicacy> delicacies = event.getDelicacies();

        SQLiteDatabase db = DatabaseHelper.getInstance(this).getWritableDatabase();

        if(locations != null) {
            for(Location l : locations) {
                l.saveToDatabase(db);
            }
        }

        if(delicacies != null) {
            for(Delicacy del : delicacies) {
                del.saveToDatabase(db);
            }
        }
        db.close();

        //should save the data here?



        NotifyFragmentsOfDataEvent notifyEvent = new NotifyFragmentsOfDataEvent();
        ApplicationBus.getInstance().post(notifyEvent);

    }
}

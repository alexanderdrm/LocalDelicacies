package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class LocationListActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    LocationListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        adapter = new LocationListAdapter(this, createDummyData());
        ListView locationListView = (ListView) findViewById(R.id.location_list_view);
        locationListView.setAdapter(adapter);
    }

    private ArrayList<Location> createDummyData()
    {
        ArrayList<Location> list = new ArrayList<Location>();
        list.add(new Location("Las Vegas", null));
        list.add(new Location("Nevada", null, true));
        return list;
    }
}

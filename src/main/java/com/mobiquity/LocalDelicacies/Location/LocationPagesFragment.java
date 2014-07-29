package com.mobiquity.LocalDelicacies.location;

import android.app.ActionBar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.*;
import com.mobiquity.LocalDelicacies.filters.Filter;
import com.mobiquity.LocalDelicacies.filters.PermissiveFilter;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/23/14.
 */
public class LocationPagesFragment extends BasePagesFragment {

    ArrayList<Location> locations;
    ArrayList<ActionBar.Tab> tabs;

    ArrayList<LocationListAdapter> adapters = new ArrayList<LocationListAdapter>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_view_pager, container, false);
        locations = new ArrayList<Location>();
        pager = (ViewPager) rootView.findViewById(R.id.pager);
        prepareAdapterList(getActivity());
        asyncLoadLocationFromDatabase();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBar actionBar = getActivity().getActionBar();
        tabs = new ArrayList<ActionBar.Tab>();
        tabs.add(actionBar.newTab()
                .setText(getResources()
                        .getString(R.string.all)));
        tabs.add(actionBar.newTab()
                .setText(getResources()
                        .getString(R.string.bookmarked)));
        configureActionBar(actionBar, tabs);

        int tabIndex = getActivity().getSharedPreferences("delicacyPreferences",Context.MODE_PRIVATE).getInt("currentLocationTab", 0);
        pager.setCurrentItem(tabIndex);
    }

    private void prepareAdapterList(Context context)
    {
        ListView allLocationsListView = new ListView(context);
        LocationListAdapter locationListAdapter = new LocationListAdapter(context, locations, new PermissiveFilter());
        allLocationsListView.setAdapter(locationListAdapter);
        adapters.add(locationListAdapter);

        ListView pinnedLocationsListView =  new ListView(context);
        LocationListAdapter pinnedListAdapter = new LocationListAdapter(context, locations, new Filter() {
            @Override
            public boolean shouldDisplay(Object object) {
                return ((Location)object).isBookmarked();
            }
        });
        pinnedLocationsListView.setAdapter(pinnedListAdapter);

        adapters.add(pinnedListAdapter);

        ArrayList<View> views = new ArrayList<View>();
        views.add(allLocationsListView);
        views.add(pinnedLocationsListView);
        pager.setAdapter( new BasePagesAdapter(views));
    }

    @Subscribe
    public void onDataUpdated(NotifyFragmentsOfDataEvent notifyFragmentsOfDataEvent) {
        asyncLoadLocationFromDatabase();

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getActionBar().removeAllTabs();
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                SQLiteDatabase db = DatabaseHelper.getInstance(getActivity()).getWritableDatabase();
                for(Location l: locations) {
                    l.saveToDatabase(db);
                }
                db.close();
                return null;
            }
        }.execute();

        getActivity().getSharedPreferences("delicacyPreferences",Context.MODE_PRIVATE).edit().putInt("currentLocationTab",pager.getCurrentItem()).commit();

    }

    private void asyncLoadLocationFromDatabase()
    {
        new AsyncTask<Void, Void, ArrayList<Location>>() {
            @Override
            protected ArrayList<Location> doInBackground(Void... voids) {
                return DatabaseHelper.getLocations(getActivity());
            }
            @Override
            protected void onPostExecute(ArrayList<Location> databaseLocations) {
                locations = databaseLocations;
                for(LocationListAdapter lladapter : adapters) {
                    lladapter.updateData(locations);
                }

            }
        }.execute();
    }



}

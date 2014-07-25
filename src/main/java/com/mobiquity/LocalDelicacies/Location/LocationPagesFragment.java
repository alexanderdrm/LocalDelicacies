package com.mobiquity.LocalDelicacies.location;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.*;
import com.mobiquity.LocalDelicacies.filters.Filter;
import com.mobiquity.LocalDelicacies.filters.PermissiveFilter;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/23/14.
 */
public class LocationPagesFragment extends BasePagesFragment {

    volatile ArrayList<Location> allLocations = TestModule.generateTestLocations();
    ArrayList<ActionBar.Tab> tabs;

    ArrayList<LocationListAdapter> adapters = new ArrayList<LocationListAdapter>();
    public static String TAG="LOCAITON_PAGES_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_view_pager, container, false);

        pager = (ViewPager) rootView.findViewById(R.id.pager);
        adapter = generateTestAdapter(inflater.getContext());
        pager.setAdapter(adapter);
        return rootView;
    }

    private BasePagesAdapter generateTestAdapter(Context context)
    {
        ListView all = new ListView(context);
        LocationListAdapter locationListAdapter = new LocationListAdapter(context, allLocations, new PermissiveFilter());
        all.setAdapter(locationListAdapter);

        adapters.add(locationListAdapter);

        ListView pinned =  new ListView(context);
        LocationListAdapter pinnedListAdapter = new LocationListAdapter(context, allLocations, new Filter() {
            @Override
            public boolean shouldDisplay(Object object) {
                return ((Location)object).isBookmarked();
            }
        });
        pinned.setAdapter(pinnedListAdapter);

        adapters.add(pinnedListAdapter);

        ArrayList<ListView> views = new ArrayList<ListView>();
        views.add(all);
        views.add(pinned);
        return new BasePagesAdapter(views);
    }

    @Subscribe
    public void onDataUpdated(DataUpdateEvent due) {
        allLocations = due.getLocations();

        for(LocationListAdapter lladapter : adapters) {
            lladapter.updateData(allLocations);
        }
    }

    @Override
    public void onResume()
    {
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

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getActionBar().removeAllTabs();
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

        for(Location l: allLocations) {
            SQLiteDatabase db = new DatabaseHelper(getActivity()).getWritableDatabase();
            l.saveToDatabase(db);
        }

        getActivity().getSharedPreferences("delicacyPreferences",Context.MODE_PRIVATE).edit().putInt("currentLocationTab",pager.getCurrentItem()).commit();

    }

}

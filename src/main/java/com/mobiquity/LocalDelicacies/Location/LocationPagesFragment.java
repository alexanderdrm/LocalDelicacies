package com.mobiquity.LocalDelicacies.location;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.R;

import java.util.ArrayList;

/**
 * Created by jwashington on 7/23/14.
 */
public class LocationPagesFragment extends Fragment {

    ViewPager pager;
    LocationPagesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layout_view_pager, container, false);

        pager = (ViewPager) rootView.findViewById(R.id.pager);
        adapter = generateTestAdapter(inflater.getContext());
        pager.setAdapter(adapter);


        return rootView;
    }

    private LocationPagesAdapter generateTestAdapter(Context context)
    {
        ListView view1 = new ListView(context);
        ArrayList<Location> testLocations = new ArrayList<Location>();
        testLocations.add(new Location("Test_location", null));
        LocationListAdapter locationListAdapter = new LocationListAdapter(context, testLocations);
        view1.setAdapter(locationListAdapter);

        ListView view2 = new ListView(context);
        ArrayList<Location> testLocations2 = new ArrayList<Location>();
        testLocations2.add(new Location("Test_location2", null));
        LocationListAdapter locationListAdapter2 = new LocationListAdapter(context, testLocations2);
        view2.setAdapter(locationListAdapter2);

        ArrayList<ListView> views = new ArrayList<ListView>();
        views.add(view1);
        views.add(view2);
        return new LocationPagesAdapter(views);
    }

    private class LocationPagesAdapter extends PagerAdapter
    {

        private ArrayList<ListView> pages;

        public LocationPagesAdapter(ArrayList<ListView> pages)
        {
            this.pages = pages;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pages.get(position));
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view.equals(object);
        }
    }
}

package com.mobiquity.LocalDelicacies.delicacies;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
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
import com.mobiquity.LocalDelicacies.location.Location;

import java.util.ArrayList;

/**
 * Created by dalexander on 7/24/14.
 */
public class DelicacyPagesFragment extends BasePagesFragment {

    //volatile ArrayList<Delicacy> allDelicacies = TestModule.generateTestDelicacies();

    ArrayList<DelicacyListAdapter> adapters = new ArrayList<DelicacyListAdapter>();
    ArrayList<ActionBar.Tab> tabs;
    public static String TAG="DELICACY_PAGES_FRAGMENT";

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
        DelicacyListAdapter delicacyListAdapter = new DelicacyListAdapter(context, DelicacyData.getDelicacyData(), new PermissiveFilter());
        all.setAdapter(delicacyListAdapter);

        adapters.add(delicacyListAdapter);

        ListView pinned =  new ListView(context);
        DelicacyListAdapter pinnedListAdapter = new DelicacyListAdapter(context, DelicacyData.getDelicacyData(), new Filter() {
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

    /*@Subscribe
    public void onDataUpdated(DataUpdateEvent due) {
        allDelicacies = due.getDelicacies();

        for(DelicacyListAdapter dladapter : adapters) {
            dladapter.updateData(allDelicacies);
        }
    }*/

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
        tabs.add(actionBar.newTab()
                .setText(getResources()
                        .getString(R.string.eaten)));
        configureActionBar(actionBar, tabs);
    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().getActionBar().removeAllTabs();
        getActivity().getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

    }

}

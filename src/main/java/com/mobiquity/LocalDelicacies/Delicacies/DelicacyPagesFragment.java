package com.mobiquity.LocalDelicacies.delicacies;

import android.app.ActionBar;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.*;
import com.mobiquity.LocalDelicacies.filters.Filter;
import com.mobiquity.LocalDelicacies.filters.PermissiveFilter;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.mobiquity.LocalDelicacies.location.Location;

import com.mobiquity.LocalDelicacies.location.LocationListAdapter;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by dalexander on 7/24/14.
 */
public class DelicacyPagesFragment extends BasePagesFragment {


    ArrayList<Delicacy> delicacies;

    ArrayList<DelicacyListAdapter> adapters = new ArrayList<DelicacyListAdapter>();
    ArrayList<ActionBar.Tab> tabs;
    public static String TAG="DELICACY_PAGES_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_view_pager, container, false);
        pager = (ViewPager) rootView.findViewById(R.id.pager);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        delicacies = DatabaseHelper.getDelicacies(getActivity(), null);//LocalDelicacyApplication.getInstance().getDelicacies();
        prepareAdapterList(getActivity());
    }

    private void prepareAdapterList(Context context)
    {
        ListView allDelicaciesListView = new ListView(context);
        DelicacyListAdapter delicacyListAdapter = new DelicacyListAdapter(context, delicacies, new PermissiveFilter());
        allDelicaciesListView.setAdapter(delicacyListAdapter);
        adapters.add(delicacyListAdapter);

        ListView pinnedDelicaciesListView =  new ListView(context);
        DelicacyListAdapter pinnedListAdapter = new DelicacyListAdapter(context, delicacies, new Filter() {
            @Override
            public boolean shouldDisplay(Object object) {
                return ((Delicacy)object).isBookmarked();
            }
        });
        pinnedDelicaciesListView.setAdapter(pinnedListAdapter);
        adapters.add(pinnedListAdapter);

        ListView eatenDelicaicesListView =  new ListView(context);
        DelicacyListAdapter eatenListAdapter = new DelicacyListAdapter(context, delicacies, new Filter() {
            @Override
            public boolean shouldDisplay(Object object) {
                return ((Delicacy)object).getRatingInHalfStars()>0;
            }
        });
        eatenDelicaicesListView.setAdapter(eatenListAdapter);
        adapters.add(eatenListAdapter);

        ArrayList<View> views = new ArrayList<View>();
        views.add(allDelicaciesListView);
        views.add(pinnedDelicaciesListView);
        views.add(eatenDelicaicesListView);
        pager.setAdapter( new BasePagesAdapter(views));
    }

    @Subscribe

    public void onDataUpdated(NotifyFragmentsOfDataEvent due) {
        delicacies = DatabaseHelper.getDelicacies(getActivity(), null);

        for(DelicacyListAdapter dladapter : adapters) {
            dladapter.updateData(delicacies);
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

        for(Delicacy del: delicacies) {
            SQLiteDatabase db = DatabaseHelper.getInstance(getActivity()).getWritableDatabase();
            del.saveToDatabase(db);
        }

    }

}

package com.mobiquity.LocalDelicacies.delicacies;

import android.app.ActionBar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.content.Loader;
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
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.mobiquity.LocalDelicacies.location.Location;

import com.mobiquity.LocalDelicacies.location.LocationListAdapter;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * Created by dalexander on 7/24/14.
 */
public class DelicacyPagesFragment extends BasePagesFragment implements LoaderManager.LoaderCallbacks<ArrayList<Delicacy>>{


    ArrayList<Delicacy> delicacies;

    ArrayList<DelicacyListAdapter> adapters = new ArrayList<DelicacyListAdapter>();
    ArrayList<ActionBar.Tab> tabs;
    public static String TAG="DELICACY_PAGES_FRAGMENT";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_view_pager, container, false);
        pager = (ViewPager) rootView.findViewById(R.id.pager);
        delicacies = new ArrayList<Delicacy>();
        prepareAdapterList(getActivity());
        getLoaderManager().initLoader(0, null, this).forceLoad();
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
        getLoaderManager().initLoader(0, null, this).forceLoad();
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
                for(Delicacy del: delicacies) {
                    del.saveToDatabase(db);
                }
                db.close();
                return null;
            }
        }.execute();


    }


    static class DelicacyLoaderTask extends AsyncTaskLoader<ArrayList<Delicacy>>
    {
        Context context;
        public DelicacyLoaderTask(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public ArrayList<Delicacy> loadInBackground() {
               return DatabaseHelper.getDelicacies(context, null);
            }
    }


    @Override
    public Loader<ArrayList<Delicacy>> onCreateLoader(int id, Bundle args) {
        //return null;
        return new DelicacyLoaderTask(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Delicacy>> loader, ArrayList<Delicacy> databaseDelicacies) {
        delicacies = databaseDelicacies;
        for(DelicacyListAdapter dladapter : adapters) {
            dladapter.updateData(delicacies);
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<ArrayList<Delicacy>> loader) {

    }

    /*@Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        return new DelicacyLoaderTask(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Delicacy>> arrayListLoader, ArrayList<Delicacy> databaseDelicacies) {
        delicacies = databaseDelicacies;
        for(DelicacyListAdapter dladapter : adapters) {
            dladapter.updateData(delicacies);
        }
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }*/
}

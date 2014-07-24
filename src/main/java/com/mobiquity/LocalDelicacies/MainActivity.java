package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import com.google.gson.Gson;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyClickedEvent;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyDetailActivity;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyListFragment;
import com.mobiquity.LocalDelicacies.location.*;
import com.mobiquity.LocalDelicacies.navdrawer.NavigationDrawerClickEvent;
import com.squareup.otto.Subscribe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity
{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;

    private CharSequence drawerTitle;
    private CharSequence title;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main);
        title = getTitle();
        drawerTitle = getTitle();

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout );
        initDrawer();
        if(savedInstanceState == null)
        {
            title = getString(R.string.locations);
            getActionBar().setTitle(title);
            switchFragment(LocationListFragment.TAG);
        }

        new DataFetchTask(getApplicationContext()).execute();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate( savedInstanceState );
        drawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApplicationBus.getInstance().register( this );
    }

    @Override
    protected void onPause() {
        super.onPause();
        ApplicationBus.getInstance().unregister( this );
    }

    private void initDrawer()
    {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.drawable.ic_navigation_drawer,
                R.string.open_drawer,
                R.string.close_drawer)
        {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActionBar().setTitle(title);
                invalidateOptionsMenu();

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Checks if home icon was selected and lets nav drawer handle
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }



    @Subscribe
    public void onNavigationDrawerItemSelected(NavigationDrawerClickEvent event)
    {
        title = event.getTitle();
        drawerLayout.closeDrawer(Gravity.START);
        switchFragment(event.getFragmentTag());

    }

    @Subscribe
    public void onLocationClicked(LocationClickedEvent event)
    {
        Fragment fragment = new LocationDetailFragment();
        Bundle bundle = Location.createBundleFromLocation(event.getLocation());

        switchFragment(fragment, bundle, event.getLocation().getTitle());
    }

    public void switchFragment(Fragment frag, Bundle b, String fragmentName) {
        frag.setArguments(b);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, frag)
                .addToBackStack(fragmentName)
                .commit();
    }

    @Subscribe
    public void onDelicacyClicked(DelicacyClickedEvent event)
    {
        Intent intent = DelicacyDetailActivity.createIntent(this, event.getDelicacy());
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    private void switchFragment(String fragmentTag)
    {
        Fragment fragment = null;
        if (fragmentTag.equals(LocationListFragment.TAG))
        {
            fragment = new LocationPagesFragment();
        }
        else if(fragmentTag.equals(DelicacyListFragment.TAG))
        {
            fragment = new DelicacyListFragment();
        }
        else if(fragmentTag.equals(LocationDetailFragment.TAG))
        {
            fragment = new LocationDetailFragment();
        }

        FragmentManager fragmentManager = getFragmentManager();
        if(fragment != null)
        {
            fragmentManager.beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        }

    }
}

package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import com.mobiquity.LocalDelicacies.delicacies.Delicacy;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyClickedEvent;
import com.mobiquity.LocalDelicacies.delicacies.DelicacyDetailFragment;
import com.mobiquity.LocalDelicacies.http.DataFetchTask;
import com.mobiquity.LocalDelicacies.http.DataUpdateEvent;
import com.mobiquity.LocalDelicacies.location.Location;
import com.mobiquity.LocalDelicacies.location.LocationClickedEvent;
import com.mobiquity.LocalDelicacies.location.LocationDetailFragment;
import com.mobiquity.LocalDelicacies.location.LocationPagesFragment;
import com.mobiquity.LocalDelicacies.navdrawer.NavigationDrawerClickEvent;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

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
            switchFragment(new LocationPagesFragment(), null, false);
        }

        new DataFetchTask(getApplicationContext()).execute();

        //DelicacyData.touch();

        /*DatabaseHelper dbHelper = new DatabaseHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DataContract.LocationEntry._ID, 0);
        values.put(DataContract.LocationEntry.COLUMN_NAME_NAME, "Orlando");
        values.put(DataContract.LocationEntry.COLUMN_NAME_DESCRIPTION, "Not to be confused with New Orleans");
        values.put(DataContract.LocationEntry.COLUMN_NAME_IMAGE_URL, "http://upload.wikimedia.org/wikipedia/commons/e/e3/Orlando_Skyline.jpg");
        values.put(DataContract.LocationEntry.COLUMN_NAME_BOOKMARKED, 1);

        db.insert(DataContract.LocationEntry.TABLE_NAME, "null", values);*/
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApplicationBus.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        ApplicationBus.getInstance().unregister( this );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Checks if home icon was selected and lets nav drawer handle
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
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

    private void switchFragment(Fragment fragment, Bundle bundle, boolean addToBackStack) {
        if(bundle != null)
            fragment.setArguments(bundle);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        if(addToBackStack)
        {
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }

    //Otto Events -----------------
    @Subscribe
    public void onNavigationDrawerItemSelected(NavigationDrawerClickEvent event)
    {
        title = event.getTitle();
        drawerLayout.closeDrawer(Gravity.START);

        try {
            Fragment fragment = (Fragment) event.getFragmentClass().newInstance();
            switchFragment(fragment, null, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void onLocationClicked(LocationClickedEvent event)
    {
        Fragment fragment = new LocationDetailFragment();
        Bundle bundle = Location.createBundleFromLocation(event.getLocation());
        switchFragment(fragment, bundle, true);
    }

    @Subscribe
    public void onDelicacyClicked(DelicacyClickedEvent event)
    {
        Fragment fragment = new DelicacyDetailFragment();
        Bundle bundle = Delicacy.createBundleFromDelicacy(event.getDelicacy());
        switchFragment(fragment, bundle, true);
    }


}

package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.NavDrawer.NavigationDrawerItemClickListener;
import com.mobiquity.LocalDelicacies.NavDrawer.NavigationBus;
import com.mobiquity.LocalDelicacies.NavDrawer.NavigationDrawerClickEvent;
import com.squareup.otto.Subscribe;

public class LocationListActivity extends Activity
{
    private String[]     navigationListTitles;
    private DrawerLayout drawerLayout;
    private ListView     drawerList;
    private ActionBarDrawerToggle drawerToggle;

    private CharSequence drawerTitle;
    private CharSequence title;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_location_list );
        title = getTitle();
        drawerTitle = getTitle();

        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        navigationListTitles = getResources().getStringArray( R.array.nav_titles );
        drawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawerList = (ListView) findViewById( R.id.navigation_drawer_fragment );

        drawerList.setAdapter( new ArrayAdapter<String>( this,
                                                         R.layout.drawer_list_item,
                                                         navigationListTitles ) );
        drawerList.setOnItemClickListener(new NavigationDrawerItemClickListener(this, navigationListTitles));
        initDrawer();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate( savedInstanceState );
        drawerToggle.syncState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        NavigationBus.getInstance().register( this );
    }

    @Override
    protected void onPause() {
        super.onPause();
        NavigationBus.getInstance().unregister( this );
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
        drawerLayout.closeDrawer( drawerList );
    }
}

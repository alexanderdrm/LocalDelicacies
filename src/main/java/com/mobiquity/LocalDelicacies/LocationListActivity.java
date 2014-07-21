package com.mobiquity.LocalDelicacies;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LocationListActivity extends Activity
{
    private String[]     navigationListTitles;
    private DrawerLayout drawerLayout;
    private ListView     drawerList;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_location_list );

        navigationListTitles = getResources().getStringArray( R.array.nav_titles );
        drawerLayout = (DrawerLayout) findViewById( R.id.drawer_layout );
        drawerList = (ListView) findViewById( R.id.left_drawer );

        // Set the adapter for the list view
        drawerList.setAdapter( new ArrayAdapter<String>( this,
                                                         R.layout.drawer_list_item,
                                                         navigationListTitles ) );
        drawerList.setOnItemClickListener(new DrawerItemClickListener(this, navigationListTitles));
    }
}

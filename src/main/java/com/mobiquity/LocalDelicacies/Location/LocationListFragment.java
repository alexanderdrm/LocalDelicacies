package com.mobiquity.LocalDelicacies.location;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.PermissiveFilter;
import com.mobiquity.LocalDelicacies.R;
import com.mobiquity.LocalDelicacies.TestModule;

import java.util.ArrayList;

/**
 * @since 1.0
 */
public class LocationListFragment extends Fragment
{
    protected View     rootView;
    protected ListView locationListView;

    protected LocationListAdapter adapter;
    public static final String TAG = "LOCATION_LIST_FRAGMENT";

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        rootView = inflater.inflate( R.layout.fragment_location_list,
                                     container,
                                     false );

        locationListView = (ListView) rootView.findViewById( R.id.location_list );
        adapter = createAdapter( inflater.getContext() );
        locationListView.setAdapter(adapter);

        return rootView;
    }

    protected LocationListAdapter createAdapter( Context context )
    {
        return new LocationListAdapter( context, TestModule.generateTestLocations(), new PermissiveFilter());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if ( adapter.isEmpty() )
        {
            locationListView.setVisibility( View.GONE );
        }
    }

}

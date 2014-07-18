package com.mobiquity.LocalDelicacies;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @since 1.0
 */
public class LocationListFragment extends Fragment
{
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        View layout = inflater.inflate( R.layout.location_list,
                                        container,
                                        false );
        return layout;
    }
}

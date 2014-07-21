package com.mobiquity.LocalDelicacies.NavDrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobiquity.LocalDelicacies.R;

/**
 * @since 1.0
 */
public class NavigationDrawerFragment extends Fragment
{
    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        View layout = inflater.inflate( R.layout.navigation_drawer_list, container, false );
        return layout;
    }
}

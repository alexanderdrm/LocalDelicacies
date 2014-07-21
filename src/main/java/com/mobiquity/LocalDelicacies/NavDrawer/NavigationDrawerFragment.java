package com.mobiquity.LocalDelicacies.NavDrawer;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.mobiquity.LocalDelicacies.R;

/**
 * @since 1.0
 */
public class NavigationDrawerFragment extends Fragment
{

    private String[]     navigationListTitles;
    private ListView drawerList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationListTitles = getResources().getStringArray( R.array.nav_titles );
    }

    @Override
    public View onCreateView( LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState )
    {
        View layout = inflater.inflate( R.layout.fragment_navigation_drawer, container, false );
        drawerList = (ListView) layout.findViewById(R.id.drawer_list);
        drawerList.setAdapter( new ArrayAdapter<String>(inflater.getContext(), R.layout.drawer_list_item, navigationListTitles));
        drawerList.setOnItemClickListener(new NavigationDrawerItemClickListener(inflater.getContext(), navigationListTitles));

        return layout;
    }

}
